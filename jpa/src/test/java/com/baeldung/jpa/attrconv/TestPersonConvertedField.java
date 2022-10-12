package com.baeldung.jpa.attrconv;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;


@Slf4j
public class TestPersonConvertedField {

    @Test
    public void givenPersonName_WhenSaving_ThenNameAndSurnameConcat() {
        var emf = Persistence.createEntityManagerFactory("basicBaeldungPU");
        var em = emf.createEntityManager();

        try {
            final String name = "John";
            final String surname = "Snow";

            PersonName personName = new PersonName();
            personName.setName(name);
            personName.setSurname(surname);

            Person person = new Person();
            person.setPersonName(personName);

            log.info("");
            log.info("--------------------");
            log.info("Saving person to DB: " + person);
            log.info("--------------------");
            log.info("");
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

            em.refresh(person);

            log.info("");
            log.info("-------------------------");
            log.info("Refreshed person from DB: " + person);
            log.info("-------------------------");
            log.info("");

            String dbPersonName = (String) em.createNativeQuery(
                    "SELECT p.personName FROM ATTRCONV_PERSON AS p WHERE p.id = :id")
                    .setParameter("id", person.getId())
                    .getSingleResult();

            log.info("");
            log.info("------------------------------");
            log.info("Person.personName value in DB: '{}'", dbPersonName);
            log.info("------------------------------");
            log.info("");

            assertEquals(surname + ", " + name, dbPersonName);
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }
}
