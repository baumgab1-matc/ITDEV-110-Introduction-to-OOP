package com.baumgart;

//model class
public class GradeTracker {

    //keeps track of how many times user entered a grade
    private int count;
    //keeps track of sum of all the grades entered
    private double totalScore;
    //holds all the grades the user has entered
    private StringBuilder holder = new StringBuilder();

    public void addGrade(double grade) {
        this.totalScore += grade;

        if (count == 0) {
            holder.append(grade);
        } else {
            holder.append(" + " + grade);
        }

    }

    public int getCount() {
        return this.count;
    }

    public void incrementCount() {
        this.count++;
    }

    public double getAverage() {
        return this.totalScore / this.count;
    }

    public void reset() {
        this.count = 0;
        this.totalScore = 0;
        //set length to 0 instead of creating a new object each time
        this.holder.setLength(0);
    }

    public String getAllGrades() {
        return holder.toString();
    }

    //returns version of grades with "," instead of "+"
    public String getFormattedGrades() {
        return holder.toString().replaceAll(" \\+ ", ", ");
    }


}
