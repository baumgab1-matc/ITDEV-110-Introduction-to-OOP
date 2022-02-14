package baumgart_highlow;

import java.util.Scanner;

//Brent Baumgart
//ITDEV-110
//Assignment 7

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

    public void printOutro() {
        admin.outro();;
    }

    public String getPlayerDecision() {
        System.out.print("Hit 'enter' for new game else 'quit' to quit game: ");
        return scanner.nextLine();
    }

    public int[] getNumberRange() {
        System.out.println("\nFirst things first, give the computer a range of numbers!");
        System.out.print("start at: ");
        int start = Integer.parseInt(scanner.nextLine());

        System.out.print("end at: ");
        int end = Integer.parseInt(scanner.nextLine());

        int[] range = {start, end};

        return range;
    }

    public int getGuess(int start, int end) {
        System.out.print("Guess a number between " + start + " and " + end + ": ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printOutOfRangeMessage() {
        System.out.println("Out of range!! Try again!\n");
    }

    public void printInvalidRange(int start, int end) {
        System.out.println("The range " + start + " to " + end + " is not valid!! Try again!");
    }

    public void printGuessWasRight(int guess) {
        System.out.print("CORRECT!!! " + guess + " was the right number!\n");
    }

    public void printGuessWasLess(int guess) {
        System.out.print("INCORRECT!!! " + guess + " was LESS then the right number!\n");
    }

    public void printGuessWasMore(int guess) {
        System.out.print("INCORRECT!!! " + guess + " was MORE then the right number!\n");
    }

    //prints current guess count
    public void printCurrentGuessCount(int guessCount) {
        System.out.println("Current guess count: " + guessCount + "\n");
    }

    //prints message for final guess count
    public void printFinalGuessCount(int guessCount) {
        System.out.print("You found the right number in " + guessCount + " ");
        //using a ternary operator to make it grammatically correct
        System.out.print(guessCount == 1 ? "guess" : "guesses");
        System.out.println("!!\n");
    }


}
