package baumgart_highlow;

//Brent Baumgart
//ITDEV-110
//Assignment 7

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
