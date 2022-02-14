package com.baumgart.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//main view of the app
public class View {

    private final Scanner scanner;
    private final String date;

    public View(Scanner scanner) {
        this.scanner = scanner;

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        date = formatter.format(localDate);
    }

    //prints main menu options for the app
    public void printMenu() {
        System.out.println("\n*** Milwaukee Barks ***");
        System.out.println("***** " + date + " ******");
        System.out.println("1 – display walk schedule\n" +
                "2 – add\n" +
                "3 – search\n" +
                "4 – update\n" +
                "5 – remove\n" +
                "6 – view\n" +
                "7 - exit\n");
    }

    //prints menu for adding
    public void printAddMenu() {
        System.out.println("\n*** ADD MENU ***");
        System.out.println("Type 'cancel' to cancel operation.");
        System.out.println("1 – add walk\n" +
                "2 – add owner\n" +
                "3 – add dog\n" +
                "4 – return to menu\n");
    }

    //prints menu for searching/viewing owners/pets
    public void printSearchMenu() {
        System.out.println("\n*** SEARCH MENU ***");
        System.out.println("1 - search walks by date\n" +
                "2 – search owner by name\n" +
                "3 – search dog by name\n" +
                "4 – return to menu\n");
    }

    //prints menu for searching/viewing owners/pets
    public void printUpdateMenu() {
        System.out.println("\n*** UPDATE MENU ***");
        System.out.println("Type 'cancel' to cancel operation.");
        System.out.println("1 - update walk\n" +
                "2 – update owner\n" +
                "3 – update dog\n" +
                "4 – return to menu\n");
    }

    public void removeMenu() {
        System.out.println("\n*** REMOVE MENU ***");
        System.out.println("Type 'cancel' to cancel operation.");
        System.out.println("1 – remove walk\n" +
                "2 – remove owner\n" +
                "3 - remove dog\n" +
                "4 – return to menu\n");
    }

    public void viewAllMenu() {
        System.out.println("\n*** VIEW ALL MENU ***");
        System.out.println("1 – view all owners\n" +
                "2 – view all dogs\n" +
                "3 – return to menu\n");
    }

    public String getChoice() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public String getDate() {
        System.out.println("Enter date to search (dd-MM-yyyy): ");
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public String getOwnersNameToSearch() {
        System.out.print("Owners name to find: ");
        return scanner.nextLine().trim();
    }

    public String getDogsNameToSearch() {
        System.out.print("Dogs name to find: ");
        return scanner.nextLine().trim();
    }

    public void println() {
        System.out.println();
    }

    public void printOwnerRow(String format, String name, String number, String dogs) {
        System.out.printf(format, name, number, dogs);
    }

    public void printDogRow(String format, String name, String breed, String gender, String age, String owner) {
        System.out.printf(format, name, breed, gender, age, owner);
    }

    public void printWalkRow(String format, String time, String name, String breed, String owner) {
        System.out.printf(format, time, name, breed, owner);
    }

    public void noWalks() {
        System.out.println("\n**** No Walks Added For Today ****");
    }

    public void emptyDogList() {
        System.out.println("\nNo dogs found.");
    }

    public void emptyOwnerList() {
        System.out.println("\nNo owners have been added yet!");
    }

    public void searchingCanceled() {
        System.out.println("\n***** Searching Cancelled *****");
    }

    public void noWalksFound(String date) {
        System.out.println("\n*** No walks found on " + date + " ***");
    }

}
