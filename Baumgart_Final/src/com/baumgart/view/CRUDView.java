package com.baumgart.view;

import java.util.Scanner;

//View that goes with CRUDController
public class CRUDView {

    private final Scanner scanner;

    public CRUDView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getChoice() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public String getChoiceWithMessage(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }


    public <T> void printInfo(int i, T current) {
        System.out.println(i + 1 + " - " + current);
    }


//    START OF ADDING NEW DOW OUTPUTS
    public String addNewDogDirections() {
        System.out.print("\nAdd dogs for owner. Type ‘done’ to stop adding, 'back' to come back, 'enter' to add: ");
        return scanner.nextLine().trim();
    }

    public void getDogsOwnerName() {
        System.out.println("Give name of owner: ");
    }

    public String getNameOfDog() {
        System.out.print("Name of dog: ");
        return scanner.nextLine().trim();
    }

    public String getBreedOfDog() {
        System.out.print("Breed of dog: ");
        return scanner.nextLine().trim();
    }

    public String getGenderOfDog() {
        System.out.print("Gender of dog (M/F): ");
        return scanner.nextLine().trim();
    }

    public String getAgeOfDog() {
        System.out.print("Age of dog: ");
        return scanner.nextLine().trim();
    }

    public void dogAdded() {
        System.out.println("\n*** Dogs Added! ****");
    }

    public void cancelAddingNewDog() {
        System.out.println("\n*** Adding Dogs Canceled ***");
    }
//    END OF ADDING NEW DOG OUTPUTS


//    START OF ADDING NEW OWNER OUTPUTS
    public void addNewOwnerDirections() {
        System.out.println("\nFill out information below.");
    }

    public String getOwnerFirstName() {
        System.out.print("Owners first name: ");
        return scanner.nextLine().trim();
    }

    public String getOwnerLastName() {
        System.out.print("Owners last name: ");
        return scanner.nextLine().trim();
    }

    public String getOwnerNumber() {
        System.out.print("Owners phone number: ");
        return scanner.nextLine().trim();
    }

    public void ownerAdded() {
        System.out.println("\n*** Owner Was Added! ***");
    }

    public void ownerCanceled() {
        System.out.println("\n**** Owner Not Added ****");
    }
//    END OF ADDING NEW OWNER OUTPUTS

//    START OF ADDING NEW WALK OUTPUTS
    public void addWalkInstructions() {
        System.out.println("Give name of dog for walk.");
    }

    public void noWalks() {
        System.out.println("\n**** No Walks Added For Today ****");
    }

    public void walkAdded() {
        System.out.println("\n***** Walk Added! *****");
    }

    public void cancelWalkMessage() {
        System.out.println("\n**** Walk Canceled ****");
    }
//    END OF ADDING NEW WALK OUTPUTS

//   START OF UPDATING OUTPUT
    public void updateDirections() {
        System.out.println("Type new info. Hit 'enter' to skip field");
    }

    public void updateSuccess() {
        System.out.println("\n****** Updated! *****");
    }

    public void cancelUpdate() {
        System.out.println("\n****** Update Canceled *****");
    }

    //   END OF UPDATING OUTPUT


    //   START OF REMOVING OUTPUT
    public void removeCanceled() {
        System.out.println("\n****** Removing Canceled *****");
    }

    public void removeSuccess() {
        System.out.println("\n****** Removed! ******");
    }


}
