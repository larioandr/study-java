package com.jpwh.model.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleTestBase {

    protected EntityManagerFactory emf;
    protected EntityManager em;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("jpwhSimplePU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        if (em != null)
            em.close();
        if (emf != null)
            emf.close();
    }

}
