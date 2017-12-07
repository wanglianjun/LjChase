package com.acwlj.demo.model.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author dong on 16/1/7.
 */
public class Dog extends RealmObject {
    @PrimaryKey
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
