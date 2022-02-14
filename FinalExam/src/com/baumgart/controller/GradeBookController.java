package com.baumgart.controller;

import com.baumgart.model.Student;
import com.baumgart.service.GradeBookService;
import com.baumgart.view.View;
import java.util.List;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

//Main controller of the application
//delegates specific logic to other controllers
public class GradeBookController {

    private final View view;
    private final GradeBookService service;
    private final AddController addController;
    private final EditController editController;
    private final SearchController searchController;
    private final int gradeBookSize;

    public GradeBookController(View view, GradeBookService gradeBookService, int gradeBookSize) {
        this.view = view;
        this.service = gradeBookService;
        this.gradeBookSize = gradeBookSize;

        //init helper controllers
        this.addController = new AddController(this.view, this.service, this.gradeBookSize);
        this.editController = new EditController(this.view, this.service, this.gradeBookSize);
        this.searchController = new SearchController(this.view, this.service);
    }

    //main loop of the application
    public void powerOn() {
        boolean isOn = true;
        String choice;

        //print in intro and check if user wants to load mock data
        view.printIntro(gradeBookSize);
        choice = view.loadMockData();

        //if user typed 1 load mock data else start with no data
        if ("1".equals(choice)) {
            service.loadStudents();
        }

        //just adds a space for better formatting
        view.printSpace();

        while (isOn) {

            view.printMainMenu(service.getStudents().size(), gradeBookSize);
            choice = view.getChoice();

            switch (choice) {
                case "1":
                    addController.addStudents();
                    break;

                case "2":
                    addController.addStudentGrades();
                    break;

                case "3":
                    editController.editStudentGrades();
                    break;

                case "4":
                    searchController.searchStudent();
                    break;

                case "5":
                    calculateClassGrades();  //calculating and printing students is so straight forward, didn't make controller for them
                    break;

                case "6":
                    printStudentList();
                    break;

                case "7":
                    isOn = false;
                    view.printCloseMessage();
                    break;

                default:
                    view.invalidSelection();
            }
        }
    }




    //prints all the info about student, including grades
    private void calculateClassGrades() {
        List<Student> students = service.getStudents();

        for (Student student : students) {
            view.printFullStudentInfo(student);
        }

        view.printSpace();
    }

    //just prints students name and id
    private void printStudentList() {
        List<Student> students = service.getStudents();

        for (Student student : students) {
            view.printBriefStudentInfo(student);
        }

        view.printSpace();
    }



}
