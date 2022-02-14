package com.baumgart;

//Brent Baumgart
//ITDEV-110
//Assignment 8

public class HighLow {

    public static void main(String[] args) {
        //model object
        Computer computer = new Computer();

        //view object
        View view = new View();

        //controller object
        Controller controller = new Controller(view, computer);

        //start game
        controller.playGame();

    }

}
