package com.baumgart.model;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public class Assignment {

    private final int id;
    private double pointsEarned;
    //the way the instructions are, each assignment is always 50 points but I wanted to add
    //the ability to make assignments more or less than 50
    private final int totalPointsPossible;

    public Assignment(int id, int totalPointsPossible) {
        this.id = id;
        this.totalPointsPossible = totalPointsPossible;
    }

    public int getId() {
        return id;
    }

    public double getPointsEarned() {
        String strPoints = String.format("%.2f", pointsEarned);
        return Double.parseDouble(strPoints);
    }

    public void setPointsEarned(double pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public int getTotalPointsPossible() {
        return totalPointsPossible;
    }

    @Override
    public String toString() {
        return "Assignment " + id + " = " + getPointsEarned();
    }
}
