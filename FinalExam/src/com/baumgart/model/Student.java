package com.baumgart.model;

import java.util.ArrayList;
import java.util.List;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public class Student {

    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final List<Assignment> assignments;
    private Grade grade;


    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = Grade.NOT_SET;
        assignments = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return this.firstName.trim() + " " + this.lastName.trim();
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public String getStudentId() {
        return studentId;
    }

    //returns a string version of final grade
    public String getFinalGrade() {
        grade = Grade.NOT_SET;
        //if student still has assignments due, don't return a letter grade
        if (!isFinishedWithAssignments()) {
            return grade.getGradeLetter();
        }

        double total = getPointsEarned();
        return grade.findGrade(total).getGradeLetter();
    }

    //checks if all assignments have been turned in
    //if a student has a score of zero, I'm saying the assignment
    //still hasn't been turned int
    private boolean isFinishedWithAssignments() {
        for (Assignment assignment : assignments) {
            if (assignment.getPointsEarned() == 0) {
                return false;
            }
        }
        return true;
    }

    //gets total points of all assignments has done
    public double getPointsEarned() {
        return assignments
                .stream()
                .mapToDouble(Assignment::getPointsEarned)
                .sum();
    }

    //gets all of points possible that student could have earned
    public int getPossiblePoints() {
        return assignments
                .stream()
                .mapToInt(Assignment::getTotalPointsPossible)
                .sum();
    }

    //this is used to add 10 default assignments each worth 50 points
    public void addAssignments() {
        for (int i = 0; i < 10; i++) {
            assignments.add(new Assignment(i+1, 50));
        }
    }

    //this method is never used, but I liked the idea of making the program more flexible
    //by being able to have more or less than 10 assignments
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", assignments=" + assignments +
                '}';
    }
}
