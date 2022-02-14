package com.baumgart;

//ITDEV-110
//BRENT BAUMGART
//ASSIGNMENT 9


import java.util.*;

public class Player {

    //list to hold 5 die
    private final List<Die> dice;

    //holds the side of each rolled die
    private final List<Integer> sides;

    private int score;

    public Player() {
        dice = new ArrayList<>();
        this.sides = new ArrayList<>();
        init();
    }

    //adds 5 dice for the player to use
    private void init() {
        for (int i = 0; i < 5; i++) {
            dice.add(new Die());
        }
    }

    //goes through the dice list and rolls each die and sets score
    public void rollDice() {
        int total = 0;
        //clear previous rolls to zero
        sides.clear();

        for (Die die : dice) {
            die.roll();
            total += die.getSide();
            //add rolled die to list of sides
            sides.add(die.getSide());
        }

        setScore(total);
    }

    public List<Integer> getSides() {
        return this.sides;
    }

    //resets all the dice to value 0
    public void reset() {
        for (Die die : dice) {
            die.setSide(0);
        }
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    //private b/c nobody should have access to setting scores outside this class
    private void setScore(int score) {
        this.score = score;
    }

}

