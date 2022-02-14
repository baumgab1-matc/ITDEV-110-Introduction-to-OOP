package com.baumgart.model;

import java.io.Serializable;
import java.util.Objects;


public class Dog implements Serializable {

    private final long serialVersionUID = 234L;
    private String name;
    private String breed;
    private String gender;
    private int age;
    private Owner owner;

    public Dog(String name, String breed, String gender, int age) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
    }

    public void addOwner(Owner owner) {
        this.owner = owner;
    }

    public String getInfo() {
        return String.format("name: %s, breed: %s, gender: %s, age: %d, Owner: %s", name, breed, gender, age, owner.getFullName());
    }

    //getters/setters/toString below
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (age != dog.age) return false;
        if (!Objects.equals(name, dog.name)) return false;
        if (!Objects.equals(breed, dog.breed)) return false;
        if (!Objects.equals(gender, dog.gender)) return false;
        return Objects.equals(owner, dog.owner);
    }

    @Override
    public int hashCode() {
        int result = (int) (serialVersionUID ^ (serialVersionUID >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (breed != null ? breed.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }


    public String toString() {
        return String.format("name: %s, breed: %s, gender: %s, age: %d, Owner: %s", name, breed, gender, age, owner.getFullName());
    }


}
