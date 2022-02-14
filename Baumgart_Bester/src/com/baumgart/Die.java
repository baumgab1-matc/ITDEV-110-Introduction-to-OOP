package com.baumgart;

//ITDEV-110
//BRENT BAUMGART
//ASSIGNMENT 9


import java.util.Random;

public class Die {

    private final Random random;
    private int side;

    public Die() {
        this.random = new Random();
    }

    //get a random number between 1 and 6
    public void roll() {
        this.side = random.nextInt(6) + 1;
    }

    //return what the die rolled
    public int getSide() {
        return this.side;
    }

    public void setSide(int num) {
        this.side = num;
    }


}
