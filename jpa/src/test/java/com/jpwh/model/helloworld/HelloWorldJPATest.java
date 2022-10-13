package com.jpwh.model.helloworld;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HelloWorldJPATest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("jpwhHelloWorldPU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testCreateAndStoreMessage() {
        Message msg = new Message();
        msg.setText("Hello World!");
        persist(msg);
        var messages = listMessages();
        assertEquals(1, messages.size());
        assertEquals("Hello World!", messages.get(0).getText());

        // Refresh message to put it into context:
        msg = em.find(Message.class, msg.getId());
        em.getTransaction().begin();
        msg.setText("Hola!");
        em.getTransaction().commit();

        messages = listMessages();
        assertEquals(1, messages.size());
        assertEquals("Hola!", messages.get(0).getText());
    }

    private void persist(Message msg) {
        var tx = em.getTransaction();
        tx.begin();
        em.persist(msg);
        tx.commit();
        em.clear();
    }

    private List<Message> listMessages() {
        return em.createQuery("SELECT m FROM Message m", Message.class).getResultList();
    }
}
