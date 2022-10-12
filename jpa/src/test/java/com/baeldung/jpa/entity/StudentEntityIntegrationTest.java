package com.baeldung.jpa.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StudentEntityIntegrationTest {

    private final Logger log = LoggerFactory.getLogger(StudentEntityIntegrationTest.class);

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("entityPU");
        em = emf.createEntityManager();
    }

    @After
    public void destroy() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void persistStudentThenRetrieveTheDetails() {
        var student = createStudentWithRelevantDetails();
        persist(student);
        clearThePersistenceContext();
        var studentsList = getStudentsFromTable();

        log.info("");
        log.info("Loaded students:");
        log.info("----------------");
        studentsList.forEach(st -> log.info(st.toString()));
        log.info("");

        checkAssertionsWith(studentsList);
    }

    private Student createStudentWithRelevantDetails() {
        Student student = new Student();
        student.setName("John");
        student.setAge(20);  // transient, won't be saved
        student.setBirthDate(getDate());
        student.setGender(Student.Gender.MALE);
        return student;
    }

    private void persist(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    private List<Student> getStudentsFromTable() {
        String queryString = "SELECT student FROM Student student";
        TypedQuery<Student> query = em.createQuery(queryString, Student.class);
        return query.getResultList();
    }

    private void checkAssertionsWith(List<Student> students) {
        assertEquals(1, students.size());
        Student john = students.get(0);
        assertEquals(1L, john.getId().longValue());
        assertEquals("John", john.getName());
        assertNull(john.getAge());
    }

    private void clearThePersistenceContext() {
        em.clear();
    }

    private Date getDate() {
        LocalDate localDate = LocalDate.of(2008, 7, 20);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
