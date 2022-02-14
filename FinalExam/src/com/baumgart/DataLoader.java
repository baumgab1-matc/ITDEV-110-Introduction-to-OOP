package com.baumgart;

import com.baumgart.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

//Loads 10 mock students
public class DataLoader {

    public static List<Student> loadStudents() {
        Student brent = new Student("10101", "Brent", "Baumgart");
        brent.addAssignments(); //add assignments by default adds 10 assignments each worth 50 points

        Student joe = new Student("33454", "Joe", "Jameson");
        joe.addAssignments();

        Student alex = new Student("43553", "Alex", "Person");
        alex.addAssignments();

        Student judy = new Student("99903", "Judy", "Bell");
        judy.addAssignments();

        Student alexis = new Student("22223", "Alexis", "Lamps");
        alexis.addAssignments();

        Student karla = new Student("77675", "Karla", "Bender");
        karla.addAssignments();

        Student rick = new Student("99922", "Rick","Smith");
        rick.addAssignments();

        Student peter = new Student("99831", "Peter","O'Brian");
        peter.addAssignments();

        Student carl = new Student("32399", "Carl","Dover");
        carl.addAssignments();

        Student madison = new Student("88723", "Madison","Rover");
        madison.addAssignments();


        Random random = new Random();
        //add random scores to students
        for (int i = 0; i < 10; i++) {
            brent.getAssignments().get(i).setPointsEarned(30 + (50 - 30) * random.nextDouble());
            joe.getAssignments().get(i).setPointsEarned(10 + (40 - 10) * random.nextDouble());
            alex.getAssignments().get(i).setPointsEarned(40 + (50 - 40) * random.nextDouble());
            judy.getAssignments().get(i).setPointsEarned(30 + (50 - 30) * random.nextDouble());
            alexis.getAssignments().get(i).setPointsEarned(25 + (50 - 25) * random.nextDouble());
            karla.getAssignments().get(i).setPointsEarned(48 + (50 - 48) * random.nextDouble());
        }

        peter.getAssignments().get(0).setPointsEarned(45);
        peter.getAssignments().get(1).setPointsEarned(34);

        carl.getAssignments().get(5).setPointsEarned(48);
        carl.getAssignments().get(8).setPointsEarned(44);

        madison.getAssignments().get(0).setPointsEarned(50);
        madison.getAssignments().get(1).setPointsEarned(50);

        List<Student> students = new ArrayList<>();
        students.add(brent);
        students.add(joe);
        students.add(alex);
        students.add(judy);
        students.add(alexis);
        students.add(karla);
        students.add(rick);
        students.add(peter);
        students.add(carl);
        students.add(madison);

        return students;
    }

}
