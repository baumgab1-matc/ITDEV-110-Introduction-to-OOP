package com.baumgart;

//ITDEV-110
//Assignment 10
//Brent Baumgart

import java.util.*;

public class View {

    private Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printInstructions() {
        System.out.println("Welcome to guess the word game! A random word will be generated");
        System.out.println("and you'll have 10 chances to guess it. If at any time you");
        System.out.println("would like to quit type 'quit'. Have fun!!\n");
    }

    public void printOutro() {
        System.out.println("\nThanks for playing!! Bye Bye!");
    }

    public void printRemainingGuess(int guesses) {
        System.out.println("********************************************");
        System.out.print("Remaining: " + guesses);
    }

    public void printLettersGuessed(List<Character> guessed) {
        System.out.print("\tGuessed: ");

        StringBuilder letterHolder = new StringBuilder();

        for (int i = 0; i < guessed.size(); i++) {
            char current = guessed.get(i);
            if (i == guessed.size() - 1) {
                letterHolder.append(current);
            } else {
                letterHolder.append(current + ", ");
            }
        }

        System.out.print(letterHolder + "\n");
    }

    public void printWord(String word) {
        System.out.println(word + "\n");
    }

    public void wordGenerated(String word) {
        System.out.println("\nRandom word of length " + word.length() + " has been chosen! Let's play!\n");
    }

    public void printCorrectGuess(char guess) {
        System.out.println("\nCORRECT! '" + guess + "' is in the word!\n");
    }

    public void printIncorrectGuess(char guess) {
        System.out.println("\nINCORRECT! '" + guess + "' is not in the word!\n");
    }

    public void printLetterHasBeenGuessed(char letter) {
        System.out.println("\n'" + letter + "' has already been guessed! Try again.\n");
    }

    public String getGuess() {
        System.out.print("Letter to Guess: ");
        return scanner.nextLine();
    }

    public void userWon(String word, int guessCount) {
        System.out.println("CONGRATULATIONS!! YOU WIN!! You guessed the word '" + word +"' in " + guessCount + " guesses");
    }

    public void userLost(String word, int guessCount) {
        System.out.println("Sorry all " + guessCount + " guesses have been used!");
        System.out.println("The correct word was '" + word + "'.");
    }

    public String wantsToPlayAgain() {
        System.out.print("\nHit any key to play again else type 'quit' to quit game: ");
        return scanner.nextLine();
    }


    //warning messages below


    public void emptyInputWarning() {
        System.out.println("\nInput cannot be empty! Try again!\n");
    }

    public void singleCharacterWarning() {
        System.out.println("\nInput must be a single character! Try again!\n");
    }

    public void notALetterWarning(char c) {
        System.out.println("\n" + c + " is not a valid letter! Try again!\n");
    }


}
