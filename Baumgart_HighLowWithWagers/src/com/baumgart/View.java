package com.baumgart;

import java.util.Scanner;

//Brent Baumgart
//ITDEV-110
//Assignment 8

public class View {

    private final Admin admin;
    private final Scanner scanner;

    public View() {
        this.admin = new Admin();
        this.scanner = new Scanner(System.in);
    }

    public void printIntro() {
        admin.intro();
    }

    public void printOutro(int credits) {
        admin.outro(credits);
    }

    public String getPlayerDecision() {
        System.out.print("\nHit 'enter' for new game else 'quit' to quit game: ");
        return scanner.nextLine();
    }

    public String getDifficulty() {
        System.out.println("\nGive the computer a difficulty level.");
        System.out.println("Type 'easy' for range 1-10.");
        System.out.println("Type 'medium' for range 1-50.");
        System.out.println("Type 'hard' for range 1-100.");

        return scanner.nextLine().toLowerCase().trim();
    }

    public void printInvalidDifficulty() {
        System.out.println("Difficulty chosen must be 'easy', 'medium' or 'hard'! Try again!");
    }

    public String getPlayerCredits() {
        System.out.println("\nHow many credits would you like to load?");
        return scanner.nextLine();
    }

    public void printInvalidCredits() {
        System.out.println("Credits loaded must be a positive number! Try again!");
    }

    public String getGuess(int start, int end) {
        System.out.print("Guess a number between " + start + " and " + end + ": ");
        return scanner.nextLine();
    }

    public String getWager() {
        System.out.print("Enter a wager to bet: ");
        return scanner.nextLine();
    }

    public void printInvalidWager() {
        System.out.println("Wager cannot be negative or greater than total credits! Try again!");
    }

    public void printOutOfRangeMessage() {
        System.out.println("Out of range!! Try again!\n");
    }

    public void printGuessWasRight(int guess) {
        System.out.print("\nCORRECT!!! " + guess + " was the right number!\n");
    }

    public void printGuessWasLess(int guess) {
        System.out.print("\nINCORRECT!!! " + guess + " was LESS then the right number!\n");
    }

    public void printGuessWasMore(int guess) {
        System.out.print("\nINCORRECT!!! " + guess + " was MORE then the right number!\n");
    }

    public void printCurrentStandings(int guessCount, int credits) {
        System.out.println("Current guess count: " + guessCount);
        System.out.println("Current credits: " + credits + "\n");
    }

    public void printOutOfCredits() {
        System.out.println("GAME OVER! You are out of credits!");
    }

    //prints message for final guess count
    public void printFinalStandings(int guessCount, int credits) {
        System.out.print("You found the right number in " + guessCount + " ");
        //using a ternary operator to make it grammatically correct
        System.out.print((guessCount == 1 ? "guess" : "guesses") + "!!\n");

        System.out.println("Your final credits is: " + credits);
    }


}
