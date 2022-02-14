package com.baumgart;

//ITDEV-110
//Assignment 10
//Brent Baumgart

import java.util.*;

public class Controller {

    private final View view;
    private final WordGenerator generator;
    private final int guessCount = 10;

    public Controller(View view, WordGenerator generator) {
        this.view = view;
        this.generator = generator;
    }

    public void playGame() {
        boolean isPlaying = true;
        boolean isGuessing;
        List<Character> guessed = new ArrayList<>();


        //print instructions
        view.printInstructions();

        start:while (isPlaying) {
            //clear out previous guesses
            guessed.clear();
            //holds each rounds guess count
            int guesses = guessCount;
            //get current rounds word
            Word wordObject = generator.getRandomWord();
            //reset isGuessing for next round
            isGuessing = true;

            //tell user word has been generated
            view.wordGenerated(wordObject.getWord());

            //loop through players guesses until guesses correct or runs out of guesses
            while (isGuessing) {
                view.printRemainingGuess(guesses);
                view.printLettersGuessed(guessed);
                view.printWord(wordObject.getEncryptedWord());

                String userInput;
                char letter;

                //loop through until user enters valid input or types 'cancel'
                while (true) {
                    //get user input
                    userInput = view.getGuess();

                    //check if user wants to quit
                    if ("quit".equals(userInput)) {
                        isPlaying = false;
                        continue start;
                    }

                    //if user didn't give valid letter make user try again
                    if (!isValidLetter(userInput)) {
                        continue;
                    }

                    //at this point user input is valid letter, turn into char
                    letter = userInput.charAt(0);

                    //check if letter has been guessed
                    if (guessed.contains(letter)) {
                        view.printLetterHasBeenGuessed(letter);
                        continue;
                    }

                    //at this point letter was valid and not been played, exit loop to see if letter is in word
                    break;
                }

                //check guess against word
                if (wordObject.getWord().contains(String.valueOf(letter))) {
                    view.printCorrectGuess(letter);
                    wordObject.decryptLetter(letter); //update to show correct guess in blanks
                } else {
                    view.printIncorrectGuess(letter);
                }

                //adjust guesses
                guesses--;

                //check if user won/out of guesses
                if (wordObject.isDecrypted()) {
                    int howManyGuessesItTook = guessCount - guesses;
                    view.userWon(wordObject.getWord(), howManyGuessesItTook);
                    isGuessing = false;
                } else if (guesses == 0) {
                    view.userLost(wordObject.getWord(), guessCount);
                    isGuessing = false;
                }

                //add guessed letter to previous guesses
                guessed.add(letter);
            }

            //user either won/lost check if wants to play again
            String choice = view.wantsToPlayAgain();

            if ("quit".equals(choice)) {
                isPlaying = false;
            }

            continue start;
        }


        //main loop has been exited, print goodbye to user
        view.printOutro();


    }

    //checks if user actually entered valid letter
    private boolean isValidLetter(String str) {
        //checks for empty input
        if (str.length() == 0) {
            view.emptyInputWarning();
            return false;
        }

        //check if input is just 1 character length
        if (str.length() > 1) {
            view.singleCharacterWarning();
            return false;
        }

        //at this point input is a character, turn into char
        char letter = str.charAt(0);

        //check if actually have a letter and not special characters
        if (!Character.isLetter(letter)) {
            view.notALetterWarning(letter);
            return false;
        }

        return true;
    }

}
