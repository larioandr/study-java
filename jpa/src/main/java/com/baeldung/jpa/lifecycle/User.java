package com.baeldung.jpa.lifecycle;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Slf4j
@Table(name = "LIFECYCLE_USER")
@EntityListeners(AuditTrailListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Transient
    private String fullName;

    public String getFullName() {
        if (firstName != null && lastName != null)
            return firstName + " " + lastName;
        else if (firstName != null)
            return firstName;
        else return Objects.requireNonNullElse(lastName, "");
    }

    @Override
    public String toString() {
        return String.format(
                "<User: id=%d, userName='%s', fullName='%s'>",
                id, userName == null ? "NULL" : userName, getFullName());
    }

    @PrePersist
    public void logNewUserAttempt() {
        log.info("**** Attempting to add new user: " + this);
    }

    @PostPersist
    public void logNewUserAdded() {
        log.info("**** Added user: " + this);
    }

    @PreRemove
    public void logUserRemovalAttempt() {
        log.info("**** Attempting to delete user: " + this);
    }

    @PostRemove
    public void logUserRemoval() {
        log.info("**** Deleted user: " + this);
    }

    @PreUpdate
    public void logUserUpdateAttempt() {
        log.info("**** Attempting to update user: " + this);
    }

    @PostUpdate
    public void logUserUpdated() {
        log.info("**** Updated user: " + this);
    }

    @PostLoad
    public void logUserLoaded() {
        log.info("**** Loaded user: " + this);
    }

}
