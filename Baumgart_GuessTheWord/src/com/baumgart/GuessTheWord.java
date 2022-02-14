package com.baumgart;

//ITDEV-110
//Assignment 10
//Brent Baumgart

import java.util.Scanner;

public class GuessTheWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);

        Controller game = new Controller(view, new WordGenerator());
        game.playGame();
    }
}
