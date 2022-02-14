package com.baumgart;

import com.baumgart.controller.GradeBookController;
import com.baumgart.service.GradeBookService;
import com.baumgart.view.View;
import java.util.Scanner;

/* ITDEV-110
 * Final EXam
 * Brent Baumgart
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        //I tried to make the code flexible, so in the future you could add more or less than 30 students
        int size = 30;
        //service is responsible for adding/searching logic
        GradeBookService gradeBookService = new GradeBookService();
        GradeBookController controller = new GradeBookController(view, gradeBookService, size);
        controller.powerOn();
    }

}
