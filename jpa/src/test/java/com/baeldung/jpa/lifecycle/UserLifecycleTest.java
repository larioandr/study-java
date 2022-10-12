package com.baeldung.jpa.lifecycle;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class UserLifecycleTest {

    @Test
    public void createUpdateReloadDeleteUser() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("basicBaeldungPU");
        EntityManager em = emf.createEntityManager();

        try {
            User mary = new User();
            mary.setUserName("mary");

            // Create entity
            em.getTransaction().begin();
            em.persist(mary);
            em.getTransaction().commit();
            em.clear();

            // Load entity
            mary = getUser(em, "mary");

            // Update Mary
            mary.setFirstName("Mary");
            mary.setLastName("Doe");
            em.getTransaction().begin();
            em.merge(mary);
            em.getTransaction().commit();
            em.clear();

            // Remove Mary
            mary = getUser(em, "mary");
            em.getTransaction().begin();
            em.remove(mary);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }

    private User getUser(EntityManager em, String userName) {
        return em.createQuery("SELECT user FROM User user", User.class).getResultList()
                .stream()
                .filter(u -> u.getUserName().equals(userName))
                .findFirst().orElse(null);
    }
}
