package com.baeldung.jpa;

import com.baeldung.jpa.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entityPU");
        EntityManager em = emf.createEntityManager();

        try {
            Student student = new Student();
            student.setName("Mary");
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();

            var query = em.createQuery("SELECT student FROM Student student");
            var students = query.getResultList();
            System.out.println("Students list:");
            System.out.println("--------------");
            students.forEach(obj -> System.out.println(obj.toString()));
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }
}
