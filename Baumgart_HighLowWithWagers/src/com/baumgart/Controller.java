package com.baumgart;


//Brent Baumgart
//ITDEV-110
//Assignment 8

public class Controller {

    private final Computer computer;
    private final View view;
    private final Player player;
    private boolean isOn;

    public Controller(View view, Computer computer) {
        this.view = view;
        this.computer = computer;
        this.player = new Player();
        this.isOn = true;
    }

    public void playGame() {
        boolean userQuit = false;
        //print intro about app and give user option to quit
        //intro only prints once since it felt awkward to reprint instructions for each replay
        view.printIntro();
        String decision = view.getPlayerDecision();


        //if user types quit in intro or during setting difficulty need to end game
        if ("quit".equals(decision) || !setDifficulty()) {
            isOn = false;
        }


        while (isOn) {

            //if credits less than 0 need to set new game, else it just carries over from previous game
            if (player.getCredits() <= 0) {
                //if user typed quit when setting credits need to end game
                if (!setPlayerCredits()) {
                    isOn = false;
                    continue;
                }
            }


            computer.generateNumber();
            int randomNumber = computer.getRandomNumber();

            //start looping through user guesses until correct guess
            boolean isGuessing = true;
            int guessCount = 0;

            while (isGuessing) {
                String strWager = view.getWager();

                //check if user wants to quit when entering wager
                if ("quit".equals(strWager)) {
                    isGuessing = false;
                    userQuit = true;
                    continue;
                }

                int wager = Integer.valueOf(strWager);

                //verify proper wager
                if (wager <= 0 || wager > player.getCredits()) {
                    view.printInvalidWager();
                    continue;
                }

                //check if user wants to quit during a guess
                String strGuess = view.getGuess(1, computer.getEnd());

                if ("quit".equals(strGuess)) {
                    isGuessing = false;
                    userQuit = true;
                    continue;
                }

                int guess = Integer.valueOf(strGuess);

                //verify if guess was in range, if not make user start over
                if (guess < 1 || guess > computer.getEnd()) {
                    view.printOutOfRangeMessage();
                    continue;
                }

                guessCount++;

                //check if guess was right
                //if it wasn't tell user it was incorrect and print guess count and credits
                if (guess == randomNumber) {
                    view.printGuessWasRight(guess);
                    //add wager to credits
                    player.setCredits(player.getCredits() + wager);
                    isGuessing = false;
                } else if (guess < randomNumber) {
                    view.printGuessWasLess(guess);
                    player.setCredits(player.getCredits() - wager);
                    view.printCurrentStandings(guessCount, player.getCredits());
                } else {
                    view.printGuessWasMore(guess);
                    player.setCredits(player.getCredits() - wager);
                    view.printCurrentStandings(guessCount, player.getCredits());
                }


                //check if player has zero/negative credits, if so end game
                if (player.getCredits() <= 0) {
                    isGuessing = false;
                }


            }

            //at this point user wants to quit, guessed correctly or is out of credits
            if (userQuit) {
                isOn = false;
                continue;
            } else if (player.getCredits() <= 0) {
                view.printOutOfCredits();
            } else {
                view.printFinalStandings(guessCount, player.getCredits());
            }

            //check if user wants to continue playing
            decision = view.getPlayerDecision();

            if ("quit".equals(decision)) {
                isOn = false;
            }

        }

        //at this point user hit quit, display final message
        view.printOutro(player.getCredits());
    }

    //sets game difficulty
    //returns true if set, returns false if user wants to quit
    private boolean setDifficulty() {
        boolean isSettingDifficulty = true;

        while (isSettingDifficulty) {
            String difficulty = view.getDifficulty();

            //check for quit
            if ("quit".equals(difficulty)) {
                return false;
            }

            //if user entered invalid difficulty, i.e. not easy, medium or hard, make user pick again
            if (!isValidDifficulty(difficulty)) {
                view.printInvalidDifficulty();
                continue;
            }

            //have computer set difficulty
            computer.setDifficulty(difficulty);
            isSettingDifficulty = false;
        }

        return true;
    }

    //user selects how many credits to load at start of new game
    //returns true if set, returns false if user wants to quit
    public boolean setPlayerCredits() {
        boolean isSettingCredits = true;

        while (isSettingCredits) {

            String input = view.getPlayerCredits();

            if ("quit".equals(input)) {
                return false;
            }

            int credits = Integer.valueOf(input);

            //make sure player entered a number less than 0
            if (credits <= 0) {
                view.printInvalidCredits();
                continue;
            }

            player.setCredits(credits);
            isSettingCredits = false;
        }

        return true;
    }

    //helper method to check if difficulty is valid
    private boolean isValidDifficulty(String str) {
        return "easy".equals(str) || "medium".equals(str) || "hard".equals(str);
    }




}

