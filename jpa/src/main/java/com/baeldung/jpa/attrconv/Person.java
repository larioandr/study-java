package com.baeldung.jpa.attrconv;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ATTRCONV_PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Convert(converter = PersonNameConverter.class)
    private PersonName personName;

    @Override
    public String toString() {
        return String.format("<Person: id=%d, name=%s", id, personName.toString());
    }
}
