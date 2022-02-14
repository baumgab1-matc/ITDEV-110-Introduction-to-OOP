package com.baumgart;

import com.baumgart.controller.MainController;
import com.baumgart.database.DatabaseOperations;
import com.baumgart.database.Database;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        load mock data, if file database does not exist, uncomment for mock data
//        DataLoader.loadMockData();

        Database database = loadDatabase();

        Scanner scanner = new Scanner(System.in);
        MainController application = new MainController(database, scanner);
        application.powerOn();

    }

    //looks for existing db, if can't find one just makes a new one
    private static Database loadDatabase() {
        Database database;

        //looks for data, if it finds one it loads it
        //else creates a new database
        try {
            database = DatabaseOperations.loadData();
        } catch (Exception e) {
            database = new Database();
        }

        return database;
    }

}
