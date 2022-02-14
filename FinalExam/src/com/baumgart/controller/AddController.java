package com.baumgart.controller;

import com.baumgart.model.Assignment;
import com.baumgart.model.Student;
import com.baumgart.service.GradeBookService;
import com.baumgart.view.View;
import java.util.Arrays;
import java.util.Optional;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

//controller that adds students and student grades
public class AddController {

    private final View view;
    private final GradeBookService service;
    //holds size of grade book
    private final int size;

    public AddController(View view, GradeBookService service, int size) {
        this.view = view;
        this.service = service;
        this.size = size;
    }


    //asks user how many students to add and loops through and adds them
    public void addStudents() {
        //get how many spots are left and tell user
        int roomLeft = size - service.getStudents().size();
        view.addStudentDirections(roomLeft);

        //gets how many students user wants to add
        int howManyToAdd = view.getHowManyStudentsToAdd();

        //check if room is available to add students
        if (howManyToAdd > roomLeft || howManyToAdd < 0) {
            view.tooManyStudents(howManyToAdd);
            return;
        }

        String id;
        String firstName;
        String lastName;
        int idx = 0;

        //loop through and add students
        //if user types '0' for id, stop the loop
        while (idx++ < howManyToAdd) {
            firstName = view.getFirstName();
            lastName = view.getLastName();
            id = view.getId();

            if ("0".equals(id)) {
                view.stopAddingStudents();
                break;
            }

            //I'm adding 10 assignments by default, but if I wanted to come back later and make the program
            //more flexible I could ask how many assignments are due for each student and how much each assignment is worth
            Student newStudent = new Student(id, firstName, lastName);
            //adds 10 assignments each worth 50 points
            newStudent.addAssignments();

            service.addStudent(newStudent);
            view.studentAddedSuccess();
        }
    }

    //prints add menu and lets user add grades to student by either name or id
    public void addStudentGrades() {
        view.printAddMenu();
        String choice = view.getChoice();

        switch (choice) {
            case "1":
                addStudentGradesByName();
                break;

            case "2":
                addStudentGradesById();
                break;

            default:
                view.invalidSelection();
        }
    }

    //adds grades by name
    private void addStudentGradesByName() {
        String fullName = getFullName();

        //streams are still new to me so learning more as I go
        //last project I returned null if something was not found
        //after reading more, Optionals seem to be much more preferred than returning null
        Optional<Student> toSearch = service.findStudentByName(fullName);

        //if nothing was found by the service tell user that
        if (toSearch.isEmpty()) {
            view.studentNotFoundWithName(fullName);
            return;
        }

        //service found a student, can now call .get on Student
        Student found = toSearch.get();

        //the logic of adding grades by name or id is the same, so delegate the adding logic
        //to a helper method that adds the grades
        addGrades(found);
    }

    //same logic as above method, just using ids now instead of name
    private void addStudentGradesById() {
        String id = view.getId();
        Optional<Student> toSearch = service.findStudentById(id);

        if (toSearch.isEmpty()) {
            view.studentNotFoundWithId(id);
            return;
        }

        Student found = toSearch.get();
        addGrades(found);
    }

    //helper method to add grades to student
    private void addGrades(Student student) {
        view.studentFoundSuccess(student);

        //check and see if student actually has assignments due
        boolean isFinishedWithAssignments = true;
        for (Assignment assignment : student.getAssignments()) {
            if (assignment.getPointsEarned() == 0) {
                isFinishedWithAssignments = false;
                break;
            }
        }

        //if student has finished all assignments just return
        if (isFinishedWithAssignments) {
            view.finishedAssignments(student);
            return;
        }

        //print assignments that can be added
        printUnfinishedAssignments(student);


        //find out max assignments that can be turned in
        int finished = 0;
        for (Assignment assignment : student.getAssignments()) {
            if (assignment.getPointsEarned() != 0) {
                finished++;
            }
        }

        int maxToAdd = 10 - finished;

        //get how many assignments the user wants to type in
        int count = view.getAssignmentCount(maxToAdd);

        //check for invalid input
        if (count <= 0 || count > maxToAdd) {
            view.invalidSelection();
            return;
        }

        String[] idHolder;
        int[] idArray;

        //need to check if assignment has already been added with given id
        //if it has, don't let user override grade
        //grades can only be changed in the edit controller
        start:while (true) {
            //get the assignment ids
            //since we want all the ids to be placed on one line, get the ids as a string
            //then spilt the string by space to get a string array
            idHolder = view.getAssignmentIDs(count).split("\\s+");

            //check if user wants to exit loop
            if ("0".equals(idHolder[0])) {
                view.printSpace();
                return;
            }

            //go through string array with string ids and parse them to int ids
            idArray = Arrays
                    .stream(idHolder)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            //check if an assignment has already been done
            for (int id : idArray) {
                if (student.getAssignments().get(id - 1).getPointsEarned() != 0) {
                    view.addingError(id);
                    continue start;
                }
            }
            //user did not try to override past assignment, break loop
            break;
        }

        //same logic as about just with doubles now
        String[] gradeHolder = view.getAssignmentGrades(count).split("\\s+");
        double[] gradeArray = Arrays
                .stream(gradeHolder)
                .mapToDouble(Double::parseDouble)
                .toArray();

        service.addStudentGrades(student, idArray, gradeArray);
        view.assignmentsAddedSuccess();
    }

    private void printUnfinishedAssignments(Student student) {
        StringBuilder holder = new StringBuilder();

        for (Assignment assignment : student.getAssignments()) {
            int id = assignment.getId();
            if (assignment.getPointsEarned() == 0) {
                holder.append( "ID: ").append(id).append("\n");
            }
        }

        //remove last line break
        holder.setLength(holder.length() - 1);

        view.printAssignmentsToAdd(holder.toString());
    }

    //helper method to get students full name:
    private String getFullName() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        return firstName + " " + lastName;
    }
}
