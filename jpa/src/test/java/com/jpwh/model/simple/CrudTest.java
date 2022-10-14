package com.jpwh.model.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@Slf4j
public class CrudTest extends SimpleTestBase {

    @Test
    public void storeAndQueryItems() throws Exception {
        // 1) Create items transaction
        em.getTransaction().begin();

        Item itemOne = new Item();
        itemOne.setName("Item One");
        itemOne.setAuctionEnd(new Date(System.currentTimeMillis() + 1000000));
        em.persist(itemOne);

        Item itemTwo = new Item();
        itemTwo.setName("Item Two");
        itemTwo.setAuctionEnd(new Date(System.currentTimeMillis() + 1000000));
        em.persist(itemTwo);

        em.getTransaction().commit();
        em.clear();

        // 2) Query
        var q = em.createNamedQuery("findItems", Item.class);
        var items = q.getResultList();
        assertEquals(2, items.size());
    }
}
