package com.jpwh.model.simple;

import com.jpwh.model.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Getter
    protected Long id;

    @Getter @Setter
    protected String username;

    // The Address is @Embeddable, no annotation needed here
    @Getter @Setter
    protected Address homeAddress;

    @Embedded  // Not necessary...
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET")),  // Nullable!
            @AttributeOverride(name = "zipcode", column = @Column(name = "BILLING_ZIPCODE", length = 5)),
            @AttributeOverride(name = "city", column = @Column(name = "BILLING_CITY"))
    })
    @Getter @Setter
    protected Address billingAddress;

    public BigDecimal calcShippingCosts(Address fromLocation) {
        // Empty implementation of business method
        return null;
    }
}
