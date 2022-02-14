package com.baumgart;


import java.util.Random;

//Brent Baumgart
//ITDEV-110
//Assignment 8

public class Computer {

    private int randomNumber;
    private final Random random;
    private int end;

    public Computer() {
        this.random = new Random();
    }

    //generates random number based off of difficulty
    public void generateNumber() {
        int start = 1;
        this.randomNumber = random.nextInt(end - start) + start;
        System.out.println("\nRandom number has been generated!\n");
    }

    public int getRandomNumber() {
        return this.randomNumber;
    }

    public void setDifficulty(String level) {
        level = level.toLowerCase().trim();

        //set end based on difficulty
        if ("easy".equals(level)) {
            end = 10;
        } else if ("medium".equals(level)) {
            end = 50;
        } else {
            end = 100;
        }

    }

    public int getEnd() {
        return this.end;
    }

}
