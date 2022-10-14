package com.jpwh.model.simple;

import com.jpwh.model.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Getter
    protected Long id;

    @Getter
    @Setter
    protected String name;
}
