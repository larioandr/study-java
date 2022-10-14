package com.jpwh.model.simple;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Instead of <code>@{@link javax.persistence.Entity}</code>,
 * this component POJO is marked with <code>@{@link Embeddable}</code>.
 * It has no identifier property.
 */
@Embeddable
@NoArgsConstructor
public class Address {

    @NotNull
    @Column(nullable = false)
    @Getter @Setter
    protected String street;

    @NotNull
    @Column(nullable = false, length = 5)  // Override VARCHAR(255)
    @Getter @Setter
    protected String zipcode;

    @NotNull
    @Column(nullable = false)
    @Getter @Setter
    protected String city;
}
