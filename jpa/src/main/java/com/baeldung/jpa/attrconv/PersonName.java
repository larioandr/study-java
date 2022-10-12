package com.baeldung.jpa.attrconv;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonName implements Serializable {
    private String name;
    private String surname;
}
