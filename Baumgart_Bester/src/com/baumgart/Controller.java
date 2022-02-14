package com.baumgart;

//ITDEV-110
//BRENT BAUMGART
//ASSIGNMENT 9


import java.util.List;

public class Controller {

    private final View view;
    private final Player human;
    private final Player computer;

    public Controller() {
        this.view = new View();
        this.human = new Player();
        this.computer = new Player();
    }

    public void playGame() {
        //main boolean for keeping game on
        boolean isOn = true;
        //boolean for playing game
        boolean isPlaying = true;

        //print instructions
        view.printIntro();

        startLoop:while (isOn) {

            //reset round to 1 and scores to 0
            int round = 1;
            human.reset();
            computer.reset();
            isPlaying = true;

            //game loop runs for six rounds
            while (isPlaying) {
                //print current round
                view.printCurrentRound(round);

                //print current scores
                view.printCurrentScores(computer.getScore(), human.getScore());

                //if round is 1, both computer and human have to play
                if (round == 1) {
                    computerRoll();

                    //have human roll and check if wants to quit
                    if (!humanRoll()) {
                        isOn = false;
                        continue startLoop;
                    }

                    round++;
                    continue;
                }

                //computer turn
                //if computer score is 30, or more than human, it always passes
                //if computer score is less/equal to human it always plays
                int computerScore = computer.getScore();
                int humanScore = human.getScore();

                if (computerScore == 30 || computerScore > humanScore) {
                    view.printPasses("Computer");
                } else {
                    computerRoll();
                }

                //human turn
                //need to handle if wants to play, pass or quit
                String choice = view.getPlayOrPass();

                if ("quit".equals(choice)) {
                    isOn = false;
                    continue startLoop;
                } else if ("pass".equals(choice)) {
                    view.printPasses("Human");
                } else {
                    //have human roll and check if wants to quit
                    if (!humanRoll()) {
                        isOn = false;
                        continue startLoop;
                    }
                }

                //check if the 5 rounds have been played
                if (++round >= 6) {
                    isPlaying = false;
                }

            }

            //six rounds have been played, print winner
            view.printWinner(computer.getScore(), human.getScore());

            //see if user wants to play again
            String decision = view.playAnotherRound();

            if ("quit".equals(decision)) {
                isOn = false;
            }

        }

        //main loop has been exited, print outro
        view.printOutro();
    }


    private void computerRoll() {
        //roll dice for computer
        computer.rollDice();

        //get values of rolled dice
        List<Integer> sides = computer.getSides();

        //print out what was rolled
        for (int side : sides) {
            view.printRolledDie("\nComputer", side);
        }

        //print out what score was
        view.printRoundScore("Computer", computer.getScore());
    }

    //returns true if user rolled all die otherwise false if user typed quit
    private boolean humanRoll() {
        //roll dice for human
        human.rollDice();

        //get values of rolled dice
        List<Integer> sides = human.getSides();

        //tell player it's their turn
        view.printHumanTurn();

        //print out what was rolled
        for (int side : sides) {
            String input = view.getHumanRoll();

            if ("quit".equals(input)) return false;

            view.printRolledDie("You", side);
        }

        //print out what score was
        view.printRoundScore("Your", human.getScore());
        return true;
    }


}
