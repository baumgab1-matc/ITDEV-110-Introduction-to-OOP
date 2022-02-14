package baumgart_highlow;

import java.util.Random;

//Brent Baumgart
//ITDEV-110
//Assignment 7

public class Computer {

    private int randomNumber;
    private final Random random;

    public Computer() {
        this.random = new Random();
    }

    public void generateNumber(int start, int end) {
        this.randomNumber = random.nextInt(end - start) + start;
        System.out.println("Random number has been generated!\n");
    }

    public int getRandomNumber() {
        return this.randomNumber;
    }


}
