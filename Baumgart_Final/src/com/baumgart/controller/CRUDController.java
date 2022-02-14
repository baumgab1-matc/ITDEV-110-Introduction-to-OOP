package com.baumgart.controller;

import com.baumgart.services.DatabaseService;
import com.baumgart.model.Dog;
import com.baumgart.model.Owner;
import com.baumgart.model.Walk;
import com.baumgart.view.CRUDView;
import com.baumgart.view.ErrorView;

import java.util.ArrayList;
import java.util.List;

//holds logic crud operations
public class CRUDController {

    private final CRUDView crudView;
    private final ErrorView errorView;
    private final DatabaseService databaseService;

    public CRUDController(DatabaseService databaseService, CRUDView crudView, ErrorView errorView) {
        this.databaseService = databaseService;
        this.crudView = crudView;
        this.errorView = errorView;
    }

    /***** CREATE *******/

    //lets owner add a new walk for a dog
    public void createWalk() {
        boolean isAdding = true;
        Dog dog = null;
        String timeOfWalk = null;

        //print out instructions for user
        crudView.addWalkInstructions();

        //loop through till user has found a valid dog and added valid walk time
        while (isAdding) {

            dog = findAndSelectDog();

            if (dog == null) {
                crudView.cancelWalkMessage();
                return;
            }

            //loop through and get a valid walk time
            boolean isAddingWalk = true;
            while (isAddingWalk) {
                //get time of walk
                timeOfWalk = getInput("Time of walk (morning, afternoon, evening): ");

                //check if user typed 'cancel' for time of walk
                if (timeOfWalk == null) {
                    crudView.cancelWalkMessage();
                    return;
                }

                //check of empty input
                if (timeOfWalk.isEmpty()) {
                    errorView.emptyInputError();
                    continue;
                }

                //check if input is valid
                if (!timeOfWalk.equals("morning") && !timeOfWalk.equals("afternoon") && !timeOfWalk.equals("evening")) {
                    errorView.walkTimeError();
                    continue;
                }

                //user entered a valid walk time, break loop.
                isAddingWalk = false;
            }


            //at this point user found dog and picked a valid time of walk need to exit main loop
            isAdding = false;
        }

        Walk walk = new Walk(timeOfWalk, dog);
        databaseService.addWalk(walk);
        crudView.walkAdded();
    }

    public void createOwner() {
        //create new owner instructions
        crudView.addNewOwnerDirections();

        //get owner info and check for cancel with each input
        //each bit is wrapped in a while loop to prevent empty input
        String firstName;
        String lastName;
        String number;

        firstName = getInput("Owners first name: ");
        if (firstName == null) {
            crudView.ownerCanceled();
            return;
        }

        lastName = getInput("Last name: ");
        if (lastName == null) {
            crudView.ownerCanceled();
            return;
        }

        number = getInput("Owners number: ");
        if (number == null) {
            crudView.ownerCanceled();
            return;
        }

        //at this point user has entered valid owner input, need to get owners dogs

        //get dogs for owner
        List<Dog> dogs = addDogs();

        //if list of dogs is null, user typed 'cancel'
        if (dogs == null) {
            crudView.ownerCanceled();
            return;
        }

        //just need to create owner and associate dogs to owner
        Owner newOwner = new Owner(firstName, lastName, number);

        for (Dog dog : dogs) {
            newOwner.addDog(dog);
        }

        //add to database
        databaseService.addOwner(newOwner);
        crudView.ownerAdded();
    }

    //lets owner add a new dog(s)
    public void createNewOwnerDogs() {
        boolean isAdding = true;
        Owner owner;

        //prints instructions
        crudView.getDogsOwnerName();

        while (isAdding) {

            owner = findAndSelectOwner();

            //if null, user typed cancel
            if (owner == null) {
                crudView.cancelAddingNewDog();
                return;
            }

            //at this point user found valid owner, need to add new dog to owner
            List<Dog> dogs = addDogs();

            //if dog is null/empty, user typed 'cancel' return back to menu
            if (dogs == null || dogs.isEmpty()) {
                crudView.cancelAddingNewDog();
                return;
            }

            //set new dogs to owner
            for (Dog dog : dogs) {
                owner.addDog(dog);
            }

            isAdding = false;
        }

        //update database
        databaseService.updateDatabase();
        crudView.dogAdded();
    }


    /************* UPDATE METHODS **********/
    //update a walk, can only update walk time
    public void updateWalk() {
        //check if there is no walks for day
        if (databaseService.getWalks() == null || databaseService.getWalks().isEmpty()) {
            crudView.noWalks();
            return;
        }

        //search for a walk
        Walk walk = findAndSelectWalk();

        //if searched walk is null, user typed cancel
        if (walk == null) {
            crudView.cancelUpdate();
            return;
        }

        crudView.updateDirections();

        while (true) {
            String walkTime = crudView.getChoiceWithMessage("New walk time ('morning', 'afternoon', 'evening': ");

            if ("cancel".equals(walkTime)) {
                crudView.cancelUpdate();
                return;
            } else if (walkTime.isEmpty()) {
                crudView.cancelUpdate();
                return;
            } else if (!"morning".equals(walkTime) && !"afternoon".equals(walkTime) && !"evening".equals(walkTime)) {
                errorView.walkTimeError();
            } else {
                walk.setTime(walkTime);
                break;
            }
        }

        databaseService.updateDatabase();
        crudView.updateSuccess();
    }


    //updates an owners name or number
    public void updateOwner() {
        String updatedFirsName;
        String updatedLastName;
        String updateNumber;

        //get owner to update
        Owner owner = findAndSelectOwner();

        //if owner is null, user typed cancel
        if (owner == null) {
            crudView.cancelUpdate();
            return;
        }

        //at this point, owner has been selected
        crudView.updateDirections();

        //get first name and check for cancel
        updatedFirsName = crudView.getOwnerFirstName();
        if ("cancel".equals(updatedFirsName)) {
            crudView.cancelUpdate();
            return;
        }
        //get last name and check for cancel
        updatedLastName = crudView.getOwnerLastName();
        if ("cancel".equals(updatedLastName)) {
            crudView.cancelUpdate();
            return;

        }
        //get number and check for cancel
        updateNumber = crudView.getOwnerNumber();
        if ("cancel".equals(updateNumber)) {
            crudView.cancelUpdate();
            return;
        }

        //flag to see if user actually updated info
        boolean isUpdated = false;

        //update first name, last name and number if not empty
        if (!updatedFirsName.isEmpty()) {
            owner.setFirstName(updatedFirsName);
            isUpdated = true;
        }

        if (!updatedLastName.isEmpty()) {
            owner.setLastName(updatedLastName);
            isUpdated = true;
        }

        if (!updateNumber.isEmpty()) {
            owner.setNumber(updateNumber);
            isUpdated = true;
        }

        //check if user actually updated, if not return
        if (!isUpdated) {
            crudView.cancelUpdate();
            return;
        }


        //update owner in database
        databaseService.updateDatabase();
        crudView.updateSuccess();
    }

    public void updateDog() {
        String updatedName;
        String updatedBreed;
        String updatedGender;
        String updatedAge;

        //get dog to update
        Dog dog = findAndSelectDog();

        if (dog == null) {
            crudView.cancelUpdate();
            return;
        }

        //at this point, dog has been selected
        crudView.updateDirections();

        //get name and check for cancel
        updatedName = crudView.getNameOfDog().trim();
        if ("cancel".equals(updatedName)) {
            crudView.cancelUpdate();
            return;
        }
        //get breed and check for cancel
        updatedBreed = crudView.getBreedOfDog().trim();
        if ("cancel".equals(updatedBreed)) {
            crudView.cancelUpdate();
            return;

        }

        //get gender and check for cancel
        updatedGender = crudView.getGenderOfDog().trim();
        if ("cancel".equals(updatedGender)) {
            crudView.cancelUpdate();
            return;
        }

        //flag to check if user actually updated a field
        boolean isUpdated = false;

        //get age and check for cancel and if proper int
        while (true) {
            updatedAge = crudView.getAgeOfDog().trim();
            if ("cancel".equals(updatedAge)) {
                crudView.cancelUpdate();
                return;
            } else if (updatedAge.isEmpty()) {
                break;
            } else if (!updatedAge.matches("[0-9]+")) {
                errorView.notIntegerError();
            } else {
                break;
            }
        }


        //update dog info if not empty
        if (!updatedName.isEmpty()) {
            dog.setName(updatedName);
            isUpdated = true;
        }

        if (!updatedBreed.isEmpty()) {
            dog.setBreed(updatedBreed);
            isUpdated = true;
        }

        if (!updatedGender.isEmpty()) {
            dog.setGender(updatedGender);
            isUpdated = true;
        }

        if (!updatedAge.isEmpty()) {
            dog.setAge(Integer.parseInt(updatedAge));
            isUpdated = true;
        }

        //if user never updated anything, cancel update
        if (!isUpdated) {
            crudView.cancelUpdate();
            return;
        }


        //update owner in database
        databaseService.updateDatabase();
        crudView.updateSuccess();
    }


    /************* START OF DELETE METHODS ******/
    public void deleteOwner() {
        Owner owner = findAndSelectOwner();

        //if owner is null, user typed cancel
        if (owner == null) {
            crudView.removeCanceled();
            return;
        }

        databaseService.removeOwner(owner);
        crudView.removeSuccess();
    }

    public void deleteDog() {
        Dog dog = findAndSelectDog();

        //if dog is null, user typed cancel
        if (dog == null) {
            crudView.removeCanceled();
            return;
        }

        databaseService.removeDog(dog);
        crudView.removeSuccess();
    }

    public void deleteWalk() {
        Walk walk = findAndSelectWalk();

        if (walk == null) {
            crudView.removeCanceled();
            return;
        }

        databaseService.removeWalk(walk);
        crudView.removeSuccess();
    }


    /****ALL METHODS BELOW ARE HELPER METHODS ****/
    //prints objects in list for user to chose from
    private <T> void printSearched(List<T> t) {
        for (int i = 0; i < t.size(); i++) {
            T current = t.get(i);
            crudView.printInfo(i, current);
        }
    }

    //helper method to get owners/dogs/walk info
    public String getInput(String message) {
        String input;
        //loop through check for empty/cancel input
        while (true) {
            input = crudView.getChoiceWithMessage(message).trim();
            if (input.isEmpty()) {
                errorView.emptyInputError();
            } else if ("cancel".equals(input)) {
                return null;
            } else {
                break;
            }
        }

        return input.trim();
    }


    //lets user select choice from list
    //using generics since since looping through a owners/dogs/walks list is
    //essentially the same thing
    private <T> T selectFromList(List<T> list) {
        T t = null;

        //need to loop through and get users choice
        boolean isSelecting = true;

        while (isSelecting) {
            //get user selection
            String strIdx = crudView.getChoice();

            if ("cancel".equals(strIdx)) {
                return null;
            } else if (strIdx.isEmpty()) {
                errorView.emptyInputError();
                continue;
            } else if (!strIdx.matches("\\d+")) {
                errorView.notIntegerError();
                continue;
            }

            //at this point user entered valid string number, parse it to int
            int idx = Integer.parseInt(strIdx);

            //adjust idx to make sense to java
            idx--;

            //check bounds of idx
            if (idx < 0 || idx >= list.size()) {
                errorView.selectionError();
            } else {
                t = list.get(idx);
                isSelecting = false;
            }
        }

        return t;
    }


    public Owner findAndSelectOwner() {
        Owner selectedOwner;
        String ownerName;

        while (true) {
            ownerName = getInput("Owners name: ");

            if (ownerName == null) {
                return null;
            }

            //this point user typed valid owner name
            //get all owners with the same name
            List<Owner> owners = databaseService.findOwnerByName(ownerName);

            //if no owner was found, start over
            if (owners == null || owners.isEmpty()) {
                errorView.noOwnerFound(ownerName);
                continue;
            }

            //print all owners with searched name
            printSearched(owners);

            //select owner from list printed
            selectedOwner = selectFromList(owners);

            if (selectedOwner == null) {
                return null;
            }

            break;
        }

        return selectedOwner;
    }

    public Dog findAndSelectDog() {
        Dog dog;
        String dogName;

        while (true) {
            dogName = getInput("Dogs name: ");

            //check if user typed cancel when giving dogs name
            if (dogName == null) {
                return null;
            }

            //this point user typed valid dog name
            //get all dogs with the same name
            List<Dog> dogs = databaseService.findDogByName(dogName);

            //if no owner was found, start over
            if (dogs == null || dogs.isEmpty()) {
                errorView.noDogsFoundError(dogName);
                continue;
            }

            //print all dogs with searched name
            printSearched(dogs);

            //select owner from list printed
            dog = selectFromList(dogs);

            //if dog is null, user typed cancel
            if (dog == null) {
                return null;
            }

            break;
        }

        return dog;
    }

    //select a walk from current day
    public Walk findAndSelectWalk() {
        Walk walk;
        String dogName;

        while (true) {
            dogName = getInput("Dogs name to walk: ");

            if (dogName == null) {
                return null;
            }


            List<Walk> walks = databaseService.findTodayWalks(dogName);

            //if no walks found, start over
            if (walks == null || walks.isEmpty()) {
                errorView.noWalkFound(dogName);
                continue;
            }

            //print all dogs with searched name that have a walk
            printSearched(walks);

            //select dog with walk from list printed
            walk = selectFromList(walks);

            break;
        }

        return walk;
    }


    //helper method to add dogs to owner
    private List<Dog> addDogs() {
        boolean isAddingDogs = true;
        List<Dog> dogs = new ArrayList<>();
        String dogsName;
        String dogsBreed;
        String dogsGender;
        int dogsAge;

        start:
        while (isAddingDogs) {
            String choice = crudView.addNewDogDirections().trim();

            if ("back".equals(choice)) {
                continue;
            } else if ("cancel".equals(choice)) {
                return null;
            } else if ("done".equals(choice)) {
                isAddingDogs = false;
                continue;
            }

            //get dogs name
            while (true) {
                dogsName = crudView.getNameOfDog();
                if ("back".equals(dogsName)) {
                    continue start;
                } else if ("cancel".equals(dogsName)) {
                    return null;
                } else if (dogsName.isEmpty()) {
                    errorView.emptyInputError();
                } else {
                    break;
                }
            }

            //get dogs breed
            while (true) {
                dogsBreed = crudView.getBreedOfDog();
                if ("back".equals(dogsBreed)) {
                    continue start;
                } else if ("cancel".equals(dogsBreed)) {
                    crudView.ownerCanceled();
                    return null;
                } else if (dogsBreed.isEmpty()) {
                    errorView.emptyInputError();
                } else {
                    break;
                }
            }

            //get dogs gender
            while (true) {
                dogsGender = crudView.getGenderOfDog().toLowerCase();
                if ("back".equals(dogsGender)) {
                    continue start;
                } else if ("cancel".equals(dogsGender)) {
                    crudView.ownerCanceled();
                    return null;
                } else if (dogsGender.isEmpty()) {
                    errorView.emptyInputError();
                } else if (!dogsGender.equals("m") && !dogsGender.equals("f")) {
                    errorView.genderError();
                } else {
                    break;
                }
            }

            //get dogs age
            //this one requires a little extra work
            //gets age from user as string to check for cancel
            //if not cancel, need to check if string is actually an int
            while (true) {
                String strAge = crudView.getAgeOfDog();
                if ("back".equals(strAge)) {
                    continue start;
                } else if ("cancel".equals(strAge)) {
                    crudView.ownerCanceled();
                    return null;
                } else if (strAge.isEmpty()) {
                    errorView.emptyInputError();
                } else if (!strAge.matches("\\d+")) {
                    errorView.ageError();
                } else {
                    dogsAge = Integer.parseInt(strAge);
                    break;
                }
            }

            Dog newDog = new Dog(dogsName, dogsBreed, dogsGender.toUpperCase(), dogsAge);
            dogs.add(newDog);

        }
        return dogs;
    }

}
