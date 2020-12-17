package com.myhibernate.dao.hibernate;

import com.myhibernate.dao.SkillRepository;
import com.myhibernate.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {
    private final SessionFactory sessionFactory;

    public SkillRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Skill create(Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Skill createdSkill = (Skill) session.save(skill);
            session.getTransaction().commit();
            return createdSkill;
        }
    }

    @Override
    public Skill read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Skill.class, id);
        }
    }

    @Override
    public Skill update(Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
            return skill;
        }
    }

    @Override
    public void delete(Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(skill);
            session.getTransaction().commit();
        }
    }

    public List<Skill> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("FROM Skill").getResultList();
        }
    }

}
