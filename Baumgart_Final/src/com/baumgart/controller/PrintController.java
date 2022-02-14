package com.baumgart.controller;

import com.baumgart.model.Dog;
import com.baumgart.model.Owner;
import com.baumgart.model.Walk;
import com.baumgart.services.DatabaseService;
import com.baumgart.view.ErrorView;
import com.baumgart.view.View;

import java.util.List;

//helps to print searched for data
public class PrintController {

    private final DatabaseService databaseService;
    private final View view;
    private final ErrorView errorView;

    public PrintController(DatabaseService databaseService, View view, ErrorView errorView) {
        this.databaseService = databaseService;
        this.view = view;
        this.errorView = errorView;
    }

    public void printOwners(List<Owner> owners) {

        if (owners == null || owners.isEmpty()) {
            view.emptyOwnerList();
            return;
        }

        String format = "%-25s%-22s%-22s\n";
        view.println();
        view.printOwnerRow(format, "Owner's Name", "Owner's Number", "Owner's Dogs");

        for (Owner owner : owners) {

            //check if owner has dogs
            if (!owner.getDogs().isEmpty()) {
                StringBuilder holder = new StringBuilder();

                for (Dog dog : owner.getDogs()) {
                    holder.append(dog.getName()).append(", ");
                }

                //remove last ,
                String dogs = holder.substring(0, holder.length() - 2);

                view.printOwnerRow(format, owner.getFullName(), owner.getNumber(), dogs);
            } else {
                view.printOwnerRow(format, owner.getFullName(), owner.getNumber(), "No dogs added");
            }

        }
    }

    public void printOwnerByName() {
        String name = view.getOwnersNameToSearch();

        List<Owner> foundOwners = databaseService.findOwnerByName(name);

        if (foundOwners == null || foundOwners.isEmpty()) {
            errorView.noOwnerFound(name);
            return;
        }

        printOwners(foundOwners);
    }

    public void printDogs(List<Dog> dogs) {

        if (dogs == null || dogs.isEmpty()) {
            view.emptyDogList();
            return;
        }

        String format = "%-18s%-23s%-18s%-18s%-18s\n";
        view.println();
        view.printDogRow(format, "Dog's Name", "Dog's Breed", "Dog's Gender", "Dog's Age", "Dog's Owner");

        for (Dog dog : dogs) {
            view.printDogRow(format, dog.getName(), dog.getBreed(), dog.getGender(), "" + dog.getAge(), dog.getOwner().getFullName());

        }

    }

    public void printDogsByName() {
        String name = view.getDogsNameToSearch();

        List<Dog> foundDogs = databaseService.findDogByName(name);

        if (foundDogs == null || foundDogs.isEmpty()) {
            errorView.noDogsFoundError(name);
            return;
        }

        printDogs(foundDogs);
    }

    public void printDailyWalks() {
        List<Walk> walks = databaseService.getWalks();

        if (walks == null || walks.isEmpty()) {
            view.noWalks();
            return;
        }

        printWalks(walks);
    }

    private void printWalks(List<Walk> walks) {
        String format = "%-20s%-20s%-20s%-20s\n";
        view.println();
        view.printWalkRow(format, "Time", "Name", "Breed", "Owner");

        for (Walk walk : walks) {
            String time = walk.getTime();
            String name = walk.getDog().getName();
            String breed = walk.getDog().getBreed();
            String owner = walk.getDog().getOwner().getFullName();
            view.printWalkRow(format, time, name, breed, owner);
        }

    }


    public void printWalksByDate() {
        String date = getDate();

        //if null, user typed canceled
        if (null == date) {
            view.searchingCanceled();
            return;
        }

        List<Walk> walks = databaseService.findWalkByDate(date);

        if (walks.isEmpty()) {
            view.noWalksFound(date);
        } else {
             printWalks(walks);
        }

    }

    public void printAllDogs() {
        List<Dog> allDogs = databaseService.getDogs();

        if (allDogs == null || allDogs.isEmpty()) {
            view.emptyDogList();
            return;
        }

        printDogs(allDogs);
    }

    public void printAllOwners() {
        List<Owner> allOwners = databaseService.getOwners();

        if (allOwners == null || allOwners.isEmpty()) {
            view.emptyOwnerList();
            return;
        }

        printOwners(allOwners);
    }

    //helper method to get date
    private String getDate() {
        String date;

        while (true) {
            date = view.getDate();

            //check for cancel
            if ("cancel".equals(date)) {
                return null;
            }

            if (date.isEmpty()) {
                errorView.emptyInputError();
                continue;
            }

            //checks for format dd-MM-yyyy
            if (!date.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
                errorView.dateError();
                continue;
            }

            //input is valid date at this point, break
            break;
        }
        return date;
    }

}
