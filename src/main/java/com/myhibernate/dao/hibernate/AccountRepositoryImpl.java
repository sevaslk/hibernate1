package com.myhibernate.dao.hibernate;

import com.myhibernate.dao.AccountRepository;
import com.myhibernate.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private final SessionFactory sessionFactory;

    public AccountRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account create(Account account) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Account createdAccount = (Account) session.save(account);
            session.getTransaction().commit();
            return createdAccount;
        }
    }

    @Override
    public Account read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Account.class, id);
        }
    }

    @Override
    public Account update(Account account) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
            return account;
        }
    }

    @Override
    public void delete(Account account) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(account);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Account> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("FROM Account").getResultList();
        }
    }

}
