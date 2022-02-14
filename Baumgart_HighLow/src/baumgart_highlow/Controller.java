package baumgart_highlow;

//Brent Baumgart
//ITDEV-110
//Assignment 7

public class Controller {

    private final Computer computer;
    private final View view;
    private boolean isOn;

    public Controller(View view, Computer computer) {
        this.view = view;
        this.computer = computer;
        this.isOn = true;
    }

    public void playGame() {
        //print intro about app and give user option to quit
        //intro only prints once since it felt awkward to reprint instructions for each replay
        view.printIntro();
        String decision = view.getPlayerDecision();

        if ("quit".equals(decision)) {
            isOn = false;
        }

        while (isOn) {
            //get number range to guess from
            int[] range = view.getNumberRange();
            int start = range[0];
            int end = range[1];

            //if user entered invalid bounds, i.e. end <= start, make user pick again
            if (end <= start) {
                view.printInvalidRange(start, end);
                continue;
            }

            //have computer generate random number
            computer.generateNumber(start, end);
            int randomNumber = computer.getRandomNumber();

            //start looping through user guesses until correct guess
            boolean isGuessing = true;
            int guessCount = 0;

            while (isGuessing) {
                int guess = view.getGuess(start, end);

                //verify if guess was in range, if not make user guess again
                if (guess < start || guess > end) {
                    view.printOutOfRangeMessage();
                    continue;
                }

                guessCount++;

                //check if guess was right
                //if it wasn't tell user it was incorrect and print guess count
                if (guess == randomNumber) {
                    view.printGuessWasRight(guess);
                    isGuessing = false;
                } else if (guess < randomNumber) {
                    view.printGuessWasLess(guess);
                    view.printCurrentGuessCount(guessCount);
                } else {
                    view.printGuessWasMore(guess);
                    view.printCurrentGuessCount(guessCount);
                }

            }

            //at this point user guessed correctly
            //print how many guess were taken
            view.printFinalGuessCount(guessCount);

            //check if user wants to continue playing
            decision = view.getPlayerDecision();

            if ("quit".equals(decision)) {
                isOn = false;
            }

        }

        //at this point user hit quit, display final message
        view.printOutro();
    }

}
