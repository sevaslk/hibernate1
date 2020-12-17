package com.myhibernate;

import com.myhibernate.dao.GenericRepository;
import com.myhibernate.dao.hibernate.DeveloperRepositoryImpl;
import com.myhibernate.model.Developer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {

            GenericRepository<Developer, Long> developerGenericRepository = new DeveloperRepositoryImpl(sessionFactory);
            System.out.println(developerGenericRepository.findAll());

        }
    }
}
