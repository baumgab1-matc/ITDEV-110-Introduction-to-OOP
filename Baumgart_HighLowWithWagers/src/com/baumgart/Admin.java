package com.baumgart;

//Brent Baumgart
//ITDEV-110
//Assignment 8

public class Admin {

    public void intro() {
        System.out.println("Welcome to the game HighLow with wagers! A computer will generate a random number");
        System.out.println("and you will place a guess with a wager to try and guess the number! If you guess correct");
        System.out.println("you can keep your credits and play again. If at anytime you run out of");
        System.out.print("credits the game is over! Type 'quit' to stop at anytime.\n");
    }

    public void outro(int credits) {
        System.out.println("\nThanks for playing HighLow with wagers! Your final credits were " + credits);
    }
}
