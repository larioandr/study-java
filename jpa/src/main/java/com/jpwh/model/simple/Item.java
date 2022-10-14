package com.jpwh.model.simple;

import com.jpwh.model.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "Item_findItems", query = "SELECT i FROM Item i"),
        @org.hibernate.annotations.NamedQuery(
                name = "Item_findItemsWithHints",
                query = "SELECT i FROM Item i",
                comment = "My Comment",
                fetchSize = 50,
                readOnly = true,
                timeout = 60
        )
})
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Getter
    protected Long id;

    @Version
    protected long version;

    @NotNull
    @Size(min = 2, max = 255, message = "Name is required, maximum 255 characters")
    @Getter @Setter
    protected String name;

    @Future
    @Getter @Setter
    protected Date auctionEnd;

    @Getter @Setter
    protected BigDecimal buyNowPrice;

    @Transient
    @Getter @Setter
    protected Set<Bid> bids = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    protected Category category;

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

    public Bid placeBid(Bid currentHighestBid, BigDecimal bidAmount) {
        if (currentHighestBid == null || bidAmount.compareTo(currentHighestBid.getAmount()) > 0) {
            return new Bid(bidAmount, this);
        }
        return null;
    }
}
