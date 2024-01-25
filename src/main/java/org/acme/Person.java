package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends PanacheEntity {
    public Person() {
        super();
        this.name = "";
        this.password = "";
    }
    public String name;
    public String password;
}
