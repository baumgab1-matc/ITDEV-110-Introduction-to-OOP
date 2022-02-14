package com.baumgart;

//ITDEV-110
//BRENT BAUMGART
//ASSIGNMENT 9


import java.util.Scanner;

public class View {

    private final Admin admin;
    private final Scanner scanner;

    public View() {
        this.admin = new Admin();
        this.scanner = new Scanner(System.in);
    }

    public void printIntro() {
        this.admin.printInstructions();
    }

    public void printCurrentRound(int round) {
        System.out.println("\n*********************");
        System.out.println("****** ROUND " + round + " ******");
        System.out.println("*********************");
    }

    public void printCurrentScores(int computerScore, int humanScore) {
        System.out.println("Computer Score: " + computerScore);
        System.out.println("Human Score: " + humanScore);
    }

    public void printRolledDie(String playerName, int side) {
        System.out.print(playerName + " rolled a " + side);
    }

    public void printRoundScore(String playerName, int score) {
        System.out.println("\n\n" + playerName + " score: " + score);
    }

    public void printHumanTurn() {
        System.out.print("\nYour turn! Hit enter key to roll! ");
    }

    public String getHumanRoll() {
        return scanner.nextLine();
    }

    public String getPlayOrPass() {
        System.out.print("\nWould you like to play or pass? (type 'pass' to pass or hit anything to play) ");
        return scanner.nextLine();
    }

    public void printPasses(String name) {
        System.out.println("\n" + name + " passes.");
    }

    public String playAnotherRound() {
        System.out.print("\nWould you like to play another round? (hit any key to contiune else type 'quit' to exit) ");

        return scanner.nextLine();
    }

    public void printWinner(int computerScore, int humanScore) {
        System.out.println("\n*********************");
        System.out.println("***** GAME OVER *****");

        if (computerScore == humanScore) {
            System.out.println("***** TIED GAME *****");
        } else if (computerScore > humanScore) {
            System.out.println("*** COMPUTER WINS ***");
            System.out.println("***** " + computerScore + " to " + humanScore + " ******");
        } else {
            System.out.println("***** YOU WIN!! *****");
            System.out.println("***** " + humanScore + " to " + computerScore + " ******");
        }

        System.out.println("*********************");
    }

    public void printOutro() {
        admin.printOutro();
    }
}

