package com.example.nut.realmspeedtest.models;

import io.realm.RealmObject;

/**
 * Created by nut on 7/5/16.
 */
public class Person extends RealmObject {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
