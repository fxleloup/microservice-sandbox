package net.bakaar.sandbox.person.data.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonEntity {

    @Id
    private long id;
}
