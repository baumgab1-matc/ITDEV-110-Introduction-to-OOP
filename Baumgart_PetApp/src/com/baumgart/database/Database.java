package com.baumgart.database;

import com.baumgart.domain.Owner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private List<Owner> database;

    public Database(List<Owner> database) {
        this.database = database;
    }

    public void addOwner(Owner owner) {
        database.add(owner);
    }

    public List<Owner> getData() {
        return database;
    }


}
