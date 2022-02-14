package com.baumgart.controller;

import com.baumgart.model.Student;
import com.baumgart.service.GradeBookService;
import com.baumgart.view.View;
import java.util.Optional;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public class SearchController {

    private final View view;
    private final GradeBookService service;

    public SearchController(View view, GradeBookService service) {
        this.view = view;
        this.service = service;
    }

    public void searchStudent() {
        view.printSearchMenu();
        String choice = view.getChoice();

        switch (choice) {
            case "1":
                searchStudentByName();
                break;

            case "2":
                addStudentGradesById();
                break;

            default:
                view.invalidSelection();
        }
    }

    private void searchStudentByName() {
        String fullName = getFullName();
        Optional<Student> toSearch = service.findStudentByName(fullName);

        if (toSearch.isEmpty()) {
            view.studentNotFoundWithName(fullName);
            return;
        }

        view.printFullStudentInfo(toSearch.get());
        view.printSpace();
    }

    private void addStudentGradesById() {
        String id = view.getId();
        Optional<Student> toSearch = service.findStudentById(id);

        if (toSearch.isEmpty()) {
            view.studentNotFoundWithId(id);
            return;
        }

        view.printFullStudentInfo(toSearch.get());
        view.printSpace();
    }

    //helper method to get students full name:
    private String getFullName() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        return firstName + " " + lastName;
    }


}
