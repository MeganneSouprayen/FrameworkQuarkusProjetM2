package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade extends PanacheEntity {
    public Long idPerson;
    public int coursValue;
    public float note;
    public Grade() {
        super();
        this.idPerson = (long) 1;
        this.coursValue = 0;
        this.note = 0;
    }
}
