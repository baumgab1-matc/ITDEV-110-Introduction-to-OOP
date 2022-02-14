package com.baumgart.model;

import java.util.*;
import java.io.Serializable;

public class Owner implements Serializable {
    private final long serialVersionUID = 33L;
    private String firstName;
    private String lastName;
    private String number;
    private final List<Dog> dogs;

    public Owner(String firstName, String lastName, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.dogs = new ArrayList<>();
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void addDog(Dog dog) {
        dogs.add(dog);
        //creates relationship for the dog and owner
        dog.addOwner(this);
    }

    public void removeDog(Dog dog) {
        this.dogs.remove(dog);
    }

    //getters/setters/toString below

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Dog> getDogs() {
        return this.dogs;
    }

    public String toString() {
        return String.format("name: %s, phone: %s", getFullName(), number);
    }


}
