package com.myhibernate.dao.hibernate;

import com.myhibernate.dao.GenericRepository;
import com.myhibernate.model.Developer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SkillRepositoryImplTest {
    @InjectMocks
    GenericRepository<Developer, Long> developerGenericRepository = mock(DeveloperRepositoryImpl.class);
    @Test
    void readTest() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Developer testDeveloper = new Developer();
            testDeveloper.setName("Pal");
            when(developerGenericRepository.read(2L)).thenReturn(testDeveloper);
            assertEquals("Pal", testDeveloper.getName());
        }
    }

}