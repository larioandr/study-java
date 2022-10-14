package com.jpwh.model.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Slf4j
public class MappingManyToOneTest extends SimpleTestBase {

    @Test
    public void storeAndLoadBids() throws Exception {
        Item anItem = new Item();
        anItem.setName("Example Item");

        Bid firstBid = new Bid(new BigDecimal("123.00"), anItem);
        Bid secondBid = new Bid(new BigDecimal("456.00"), anItem);

        em.getTransaction().begin();
        em.persist(anItem);
        em.persist(firstBid);
        em.persist(secondBid);
        em.getTransaction().commit();

        Long BID_ID = firstBid.getId();
        log.info("BID_ID =  {}", BID_ID);

        // Load in another persistence context
        Bid someBid = em.find(Bid.class, BID_ID);  // SQL SELECT

        // Initializes the Item proxy because we call getId(), which is
        // not mapped as an identifier property (the field is!)
        assertEquals(anItem.getId(), someBid.getItem().getId());
    }
}
