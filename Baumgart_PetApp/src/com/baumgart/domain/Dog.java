package com.baumgart.domain;

public class Dog {

    private String name;
    private String breed;
    private int age;
    private String gender;
    private Owner owner;

    public Dog(String name, String breed, int age, String gender, Owner owner) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.owner = owner;
    }

    public Dog() {
    }

    //getters and setters below
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
