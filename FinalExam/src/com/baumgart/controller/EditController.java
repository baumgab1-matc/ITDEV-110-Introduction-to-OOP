package com.baumgart.controller;

import com.baumgart.model.Assignment;
import com.baumgart.model.Student;
import com.baumgart.service.GradeBookService;
import com.baumgart.view.View;
import java.util.Optional;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public class EditController {

    private final View view;
    private final GradeBookService service;
    private final int size;

    public EditController(View view, GradeBookService service, int size) {
        this.view = view;
        this.service = service;
        this.size = size;
    }


    public void editStudentGrades() {
        view.printEditMenu();
        String choice = view.getChoice();

        switch (choice) {
            case "1":
                editStudentByName();
                break;

            case "2":
                editStudentByID();
                break;

            default:
                view.invalidSelection();
        }
    }

    private void editStudentByName() {
        String fullName = getFullName();
        Optional<Student> toSearch = service.findStudentByName(fullName);

        //if no student was found with given name, just return
        if (toSearch.isEmpty()) {
            view.studentNotFoundWithName(fullName);
            return;
        }

        Student foundStudent = toSearch.get();
        editStudent(foundStudent); //editing the student by name or id is the same, so used a helper method
    }

    private void editStudentByID() {
        String id = view.getId();
        Optional<Student> toSearch = service.findStudentById(id);

        if (toSearch.isEmpty()) {
            view.studentNotFoundWithId(id);
            return;
        }

        Student foundStudent = toSearch.get();
        editStudent(foundStudent);
    }

    private void editStudent(Student student) {
        //add assignments that can be edited to a StringBuilder
        StringBuilder holder = new StringBuilder();
        student.getAssignments().forEach(currentAssignment -> {
                    if (currentAssignment.getPointsEarned() != 0) {
                        int id = currentAssignment.getId();
                        double score = currentAssignment.getPointsEarned();
                        holder.append("ID: ").append(id).append(" -> SCORE: ").append(score).append("\n");
                    }
                }
        );

        //check to see if student has no assignments turned in
        if (holder.toString().isEmpty()) {
            view.noAssignments(student);
            return;
        } else {
            view.studentFoundSuccess(student);
        }

        //print out finished assignments
        view.printAssignmentsToEdit(holder.toString());

        //loop through and edit assignments
        //if user types 0, break loop
        boolean isEditing = true;
        while (isEditing) {
            //get assignment number to edit
            int toEdit = view.getAssignmentIdToEdit();

            if (toEdit == 0) {
                isEditing = false;
                view.printSpace();
                continue;
            }

            if (toEdit < 0 || toEdit > 10) { //todo I don't like how 10 assignments is hard coded in, I may come back to this in the future
                view.invalidSelection();
                continue;
            }

            Assignment assignment = student.getAssignments().get(toEdit - 1);
            if (assignment.getPointsEarned() <= 0) {
                view.noAssignmentToEdit(toEdit);
                continue;
            }

            double newScore = view.getNewAssignmentGrade(toEdit);
            assignment.setPointsEarned(newScore);
            view.assignmentUpdated(toEdit);
        }
    }

    //helper method to get students full name:
    private String getFullName() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        return firstName + " " + lastName;
    }


}
