package com.baumgart.service;

import com.baumgart.DataLoader;
import com.baumgart.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public class GradeBookService {

    private final List<Student> students;

    public GradeBookService() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Optional<Student> findStudentByName(String name) {
        return students.stream()
                .filter(student -> student.getFullName().toLowerCase().trim().equals(name.toLowerCase().trim()))
                .findFirst();
    }

    public Optional<Student> findStudentById(String id) {
        return students.stream()
                .filter(student -> student.getStudentId().equals(id))
                .findFirst();
    }

    public void addStudentGrades(Student student, int[] assignmentIds, double[] grades) {

        if (assignmentIds.length != grades.length) {
            return;
        }

        for (int i = 0; i < assignmentIds.length; i++) {
            int id = assignmentIds[i];
            double grade = grades[i];
            student.getAssignments().get(id - 1).setPointsEarned(grade);
        }

    }

    public List<Student> getStudents() {
        return students;
    }


    //called when user wants to load mock data
    public void loadStudents() {
        List<Student> mockData = DataLoader.loadStudents();
        students.addAll(mockData);
    }

}
