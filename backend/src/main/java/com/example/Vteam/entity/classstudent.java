package com.example.Vteam.entity;

import com.example.Vteam.embed.classstudent_key;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "classstudent")
public class classstudent {

    @EmbeddedId
    classstudent_key id;

    public classstudent() {
    }

    public classstudent(classstudent_key id) {
        this.id = id;
    }

    public classstudent_key getId() {
        return id;
    }

    public void setId(classstudent_key id) {
        this.id = id;
    }

}
