package com.baumgart.model;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public enum Grade {

    NOT_SET("Not Set"),
    A("A"),
    A_MINUS("A-"),
    B_PLUS("B+"),
    B("B"),
    B_MINUS("B-"),
    C_PLUS("C+"),
    C("C"),
    D("D"),
    F("F");

    private final String gradeLetter;

    Grade (String gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    public String getGradeLetter() {
        return this.gradeLetter;
    }


    public Grade findGrade(double totalPoints) {
        Grade grade;

        double gradeScore = totalPoints / 500;

        if (gradeScore < .60) {
            grade = Grade.F;
        } else if (gradeScore <= .64) {
            grade = Grade.D;
        } else if (gradeScore <= .69) {
            grade = Grade.C;
        } else if (gradeScore <= .74) {
            grade = Grade.C_PLUS;
        } else if (gradeScore <= .79) {
            grade = Grade.B_MINUS;
        } else if (gradeScore <= .84) {
            grade = Grade.B;
        } else if (gradeScore <= .89) {
            grade = Grade.B_PLUS;
        } else if (gradeScore <= .94) {
            grade = Grade.A_MINUS;
        } else {
            grade = Grade.A;
        }

        return grade;
    }

}
