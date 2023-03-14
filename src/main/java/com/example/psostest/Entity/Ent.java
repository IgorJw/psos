package com.example.psostest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ent {

    @Id
    private Integer id;

    public Ent(Integer id) {
        this.id = id;
    }
    public Ent()
    {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
