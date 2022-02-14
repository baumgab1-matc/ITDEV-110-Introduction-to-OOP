package com.baumgart;

import java.util.Scanner;

public class View {

    private final Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    //print intro and instructions to user
    public void printIntro() {
        System.out.println("Welcome to the Grade Average App!");
        System.out.println("You'll enter a series of grades and I'll continuously average them.");
        System.out.println("Type -1 at anytime to print final result.\n");
    }

    //get a grade from user
    public double getGrade() {
        System.out.print("Enter grade score: ");
        return Double.parseDouble(scanner.nextLine());
    }

    //prints all the grades back to user
    public void printGrades(String message, String grades) {
        System.out.println(message + grades);
    }

    //prints how many grades the user has entered
    public void printGradeCount(int count) {
        System.out.println("Number of grades entered: " + count);
    }

    //prints current running average
    public void printRunningAverages(double average) {
        System.out.println("Average of all scores: " + average + "\n");
    }

    //prints total number of scores and final average when user stops loop
    public void printCountAndAverages(int numberOfScores, double average) {
        System.out.println("Final Number of scores: " + numberOfScores);
        System.out.println("Final Average: " + average + "\n");
    }

    //print choice to user and get decision to either run app again or exit app
    public String getUserDecision() {
        System.out.print("Select any key to enter another set of scores or 'exit' to exit application. ");
        return scanner.nextLine();
    }

    //print message when app closes
    public void printGoodBye() {
        System.out.println("Thanks for using my app!! Bye!");
    }

    //prints error message is user enters -1 with no data entered
    public void printErrorMessage() {
        System.out.println("No grades were inputted!\n");
    }

}











