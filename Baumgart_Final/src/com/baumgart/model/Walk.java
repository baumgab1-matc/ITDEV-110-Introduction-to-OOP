package com.baumgart.model;

import java.io.Serializable;

public class Walk implements Serializable {

    private final long serialVersionUID = 9993L;
    private String time;
    private Dog dog;

    public Walk (String time, Dog dog) {
        this.time = time;
        this.dog = dog;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String toString() {
        return String.format("Time of walk: %s - Dog info: %s", time, dog.getInfo());
    }

}
