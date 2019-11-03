package com.guidesmiths.martianrobots.model.hibernate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_preferences")
@Entity
public @Data class UserPreference {
    @Id
    @Column(name = "id")
    private String key;
    @Column(name="value")
    private String value;

    public UserPreference(){

    }

    public UserPreference(String key, String value){
        this.key = key;
        this.value = value;
    }
}
