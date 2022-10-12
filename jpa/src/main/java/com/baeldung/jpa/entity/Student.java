package com.baeldung.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "STUDENT")
public class Student {

    public enum Gender {
        MALE,
        FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="student_name", length = 50, nullable = false, unique = false)
    private String name;

    @Transient
    private Integer age;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public String toString() {
        return String.format(
                "(Student id=%d, name='%s', gender=%s, birthDate=%s, age=%d)",
                id, name,
                gender == null ? "NULL" : gender.toString(),
                birthDate == null ? "NULL" : birthDate.toString(),
                age == null ? 0 : age
        );
    }
}
