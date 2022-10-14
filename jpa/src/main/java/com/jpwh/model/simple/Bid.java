package com.jpwh.model.simple;

import com.jpwh.model.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Bid {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Getter
    protected Long id;

    @NotNull
    @Getter
    @Setter
    protected BigDecimal amount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)  // NOT NULL
    @JoinColumn(name = "ITEM_ID")  // Actually the default name
    @Getter
    @Setter
    protected Item item;

    public Bid() {}

    public Bid(BigDecimal amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this);
    }
}
