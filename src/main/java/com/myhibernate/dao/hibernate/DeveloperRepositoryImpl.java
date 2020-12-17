package com.myhibernate.dao.hibernate;

import com.myhibernate.dao.DeveloperRepository;
import com.myhibernate.model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    private final SessionFactory sessionFactory;

    public DeveloperRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Developer create(Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Developer savedDeveloper = (Developer) session.save(developer);
            session.getTransaction().commit();
            return savedDeveloper;
        }
    }

    @Override
    public Developer read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Developer d LEFT JOIN FETCH d.skills WHERE d.id = :id");
            query.setParameter("id", id);
            return (Developer) query.getSingleResult();
        }
    }

    @Override
    public Developer update(Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
            return developer;
        }
    }

    @Override
    public void delete(Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(developer);
            session.getTransaction().commit();
        }
    }

    @Transactional()
    public List<Developer> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("FROM Developer").getResultList();
        }
    }

}
