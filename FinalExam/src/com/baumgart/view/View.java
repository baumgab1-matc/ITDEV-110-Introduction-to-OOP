package com.baumgart.view;

import com.baumgart.model.Assignment;
import com.baumgart.model.Student;
import java.util.List;
import java.util.Scanner;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public class View {

    private final Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    //just prints a space, used for formatting
    public void printSpace() {
        System.out.println();
    }

    //prints intro that user sees when app starts
    public void printIntro(int size) {
        System.out.println("Welcome to a grade book application. You can enter up to " + size + " students to");
        System.out.println("keep track of. Each student has 10 assignments that need to be scored.");
        System.out.println("Once all assignments have been scored, the student will receive a final grade.");
        System.out.println();
    }

    //asks user if they want mock data to be loaded
    public String loadMockData() {
        System.out.println("Would you like do load 10 mock students?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        System.out.print("> ");
        return scanner.nextLine();
    }

    //main menu of the program
    public void printMainMenu(int loaded, int possible) {
        System.out.println("----------------------------");
        System.out.println("-------- GRADE BOOK --------" );
        System.out.println("-- " + loaded + " / " + possible + " students added ---");
        System.out.println("----------------------------");
        System.out.println("1 - Add Students");
        System.out.println("2 - Add Student Grades");
        System.out.println("3 - Edit Student Grades");
        System.out.println("4 - Search Students");
        System.out.println("5 - Calculate Class Grades");
        System.out.println("6 - Print Student List");
        System.out.println("7 - Exit");
        System.out.println("----------------------------");
    }

    //menu for adding grades to a student
    public void printAddMenu() {
        System.out.println("\n----------------------------");
        System.out.println("Select Student By");
        System.out.println("1 - Name");
        System.out.println("2 - ID");
        System.out.println("----------------------------");
    }

    //menu for editing grades to a student
    public void printEditMenu() {
        System.out.println("\n----------------------------");
        System.out.println("Edit Student By");
        System.out.println("1 - Name");
        System.out.println("2 - ID");
        System.out.println("----------------------------");
    }

    //menu for searching students
    public void printSearchMenu() {
        System.out.println("\n----------------------------");
        System.out.println("Search Student By");
        System.out.println("1 - Name");
        System.out.println("2 - ID");
        System.out.println("----------------------------");
    }



    //gets choice from user
    public String getChoice() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    //used when you just want to print students name
    public void printBriefStudentInfo(Student student) {
        System.out.println("\n--------------------------------------------");
        System.out.println("Student name: " + student.getLastName() + ", " + student.getFirstName());
        System.out.println("Student id: " + student.getStudentId());
        System.out.println("--------------------------------------------");
    }

    //used when you calculate class grades, it prints everything about the student
    public void printFullStudentInfo(Student student) {
        List<Assignment> assignments = student.getAssignments();
        String padding = "%-30s";

        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.println("Student name: " + student.getLastName() + ", " + student.getFirstName());
        System.out.println("Student id: " + student.getStudentId());
        System.out.println("Student points: " + String.format("%.2f", student.getPointsEarned()) + " / " + student.getPossiblePoints());
        System.out.println("Student grade: " + student.getFinalGrade());

        System.out.print("\nIndividual Assignments:");
        for (int i = 0; i < student.getAssignments().size(); i++) {

            //line assignments per line
            if (i % 3 == 0) {
                System.out.println();
            }

            Assignment current = assignments.get(i);
            System.out.printf(padding, current);
        }

        System.out.println("\n---------------------------------------------------------------------------------");
    }

    public int getHowManyStudentsToAdd() {
        System.out.print("How many students would you like to enter? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void addStudentDirections(int count) {
        System.out.println("\nCan add up to " + count + " more students. Type '0' for students id to stop at any time.");
    }

    public String getFirstName() {
        System.out.print("Enter student's first name: ");
        return scanner.nextLine().trim();
    }

    public String getLastName() {
        System.out.print("Enter student's last name: ");
        return scanner.nextLine().trim();
    }

    public String getId() {
        System.out.print("Enter student's ID: ");
        return scanner.nextLine().trim();
    }

    public void studentAddedSuccess() {
        System.out.println("\nStudent was added!\n");
    }

    public void studentFoundSuccess(Student student) {
        System.out.println("Student has been found!! " + student.getLastName() + ", " + student.getFirstName() + " " + student.getStudentId());
    }

    public void invalidSelection() {
        System.out.println("\nNot a valid selection.\n");
    }

    public void tooManyStudents(int count) {
        System.out.println("\nCannot add " + count + " students!\n");
    }

    public void studentNotFoundWithId(String id) {
        System.out.println("No student found with id '" + id + "'.\n");
    }

    public void studentNotFoundWithName(String name) {
        System.out.println("No student found with name '" + name + "'.\n");
    }

    public int getAssignmentCount(int max) {
        System.out.print("\nHow many assignments would you like to enter? (can add up to " + max + " assignments): ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String getAssignmentIDs(int count) {
        if (count == 1) {
            System.out.print("Enter the " + count + " assignment ID (between 1-10) ");
        } else {
            System.out.print("Enter the " + count + " assignment IDs on the same line (between 1-10) ");
        }

        System.out.print("Type '0' to stop: ");

        return scanner.nextLine();
    }

    public String getAssignmentGrades(int count) {
        if (count == 1) {
            System.out.print("Enter the " + count + " assignment grade (between 1- 50): ");
        } else {
            System.out.print("Enter the " + count + " assignment grades on same line. One for every assignment (between 1- 50): ");
        }

        return scanner.nextLine();
    }

    public void assignmentsAddedSuccess() {
        System.out.println("\nAssignments added!\n");
    }

    public int getAssignmentIdToEdit() {
        System.out.print("Enter the assignment id to edit (1 - 10). Type '0' to stop:  ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void noAssignmentToEdit(int id) {
        System.out.println("Assignment with id '" +  id + "' has not been submitted. Cannot edit!\n");
    }

    public void noAssignments(Student student) {
        System.out.println("No assignments have been entered for " + student.getFullName() + "! Cannot edit!\n");
    }

    public double getNewAssignmentGrade(int id) {
        System.out.print("New grade for assignment " + id + ": ");
        return Double.parseDouble(scanner.nextLine());
    }

    public void assignmentUpdated(int id) {
        System.out.println("\nAssignment " + id + " has been updated!\n");
    }

    public void printAssignmentsToEdit(String assignments) {
        System.out.println("\nAssignment ids that can be edited: \n" + assignments);
    }

    public void stopAddingStudents() {
        System.out.println("\nStopped Adding Students!\n");
    }

    public void addingError(int id) {
        System.out.println("\nStudent Assignment " + id + " has already been added! To change grade you must go to edit.\n");
    }

    public void finishedAssignments(Student student) {
        System.out.println("\n" + student.getFullName() + " has finished all assignments!\n");
    }

    public void printCloseMessage() {
        System.out.println("\nThanks for using the grade book program!!");
    }

    public void printAssignmentsToAdd(String holder) {
        System.out.println("Assignments that can be added:\n" + holder);
    }
}
