package com.baumgart;

public class Controller {

    private final View view;
    private final GradeTracker gradeTracker;
    private boolean isOn = true;

    public Controller(View view, GradeTracker gradeTracker) {
        this.view = view;
        this.gradeTracker = gradeTracker;
    }

    public void startApp() {

        view.printIntro();

        while (isOn) {
            //get current grade
            double grade = view.getGrade();

            //if user typed -1, display final average add see if user wants to continue
            if (grade == -1) {
                displayAllGrades();
                displayFinalGradeCountAndAverage();
                checkUserDecision();
                continue;
            }

            //user typed grade not -1, need to add to grade tracker
            gradeTracker.addGrade(grade);

            //increment grade count and get new value
            gradeTracker.incrementCount();
            int count = gradeTracker.getCount();

            //display all the grades the user has entered
            view.printGrades("", gradeTracker.getAllGrades());

            //display how many grades the user has entered
            view.printGradeCount(count);

            //get and display current running average
            double average = gradeTracker.getAverage();
            view.printRunningAverages(average);
        }

    }


    //display back all the grades to the user
    private void displayAllGrades() {
        //don't print anything if user didn't enter anything
        if (gradeTracker.getCount() == 0) {
            return;
        }
        view.printGrades("All grades: ", gradeTracker.getFormattedGrades());
    }

    //display final average
    private void displayFinalGradeCountAndAverage() {
        int count = gradeTracker.getCount();

        //check if user entered 0 values
        if (count == 0) {
            view.printErrorMessage();
            return;
        }

        view.printCountAndAverages(count, gradeTracker.getAverage());
    }


    //check if user wants to continue, if user types exit the app closes
    //else we reset the variables and continue
    private void checkUserDecision() {
        String decision = view.getUserDecision();
        System.out.println();

        if ("exit".equals(decision)) {
            this.isOn = false;
            view.printGoodBye();
        } else {
            gradeTracker.reset();
        }

    }


}
