package com.jpwh.model.simple;

import java.util.HashSet;
import java.util.Set;

public class Item {

    protected Set<Bid> bids = new HashSet<>();

    public Set<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        if (bid == null) {
            throw new NullPointerException("Can't add null Bid");
        }
        if (bid.getItem() != null) {
            throw new IllegalStateException("Bid is already assigned to an item");
        }
        getBids().add(bid);
        bid.setItem(this);
    }
}
