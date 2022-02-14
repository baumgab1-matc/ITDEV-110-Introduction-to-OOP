package com.baumgart;

//Brent Baumgart
//IT-DEV110
//Mid-Term

public class Main {

    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();
        View view = new View();
        Controller controller = new Controller(view, tracker);

        controller.startApp();
    }
}
