package com.baumgart.services;

import com.baumgart.database.DatabaseOperations;
import com.baumgart.database.Database;
import com.baumgart.model.Dog;
import com.baumgart.model.Owner;
import com.baumgart.model.Walk;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//service that interacts with database
public class DatabaseService {

    private final Database db;

    public DatabaseService(Database database) {
        this.db = database;
    }

    //adds owner to db
    public void addOwner(Owner owner) {
        db.getOwners().add(owner);
        //update database
        updateDatabase();
    }

    //adds walk to db
    public void addWalk(Walk walk) {
        String date = getCurrentDate();

        //check if map contains key for current date, if yes just add the walk
        if (db.getWalks().containsKey(date)) {
            List<Walk> walks = db.getWalks().get(date);
            walks.add(walk);
        }  else { // if does not contain key, need to create a new list that will hold walks and create key
            List<Walk> walks = new ArrayList<>();
            walks.add(walk);
            db.getWalks().put(date, walks);
        }
        //update database
        updateDatabase();
    }

    //get every owner with given name and return list with found owners
    public List<Owner> findOwnerByName(String name) {
        List<Owner> found = new ArrayList<>();

        //just return empty list if user passed null or gave empty input
        if (name == null || name.isEmpty()) {
            return found;
        }

        db.getOwners()
                .forEach(owner -> {
                    String fullName = owner.getFullName().toLowerCase().trim();
                    if (fullName.contains(name.toLowerCase().trim())) {
                        found.add(owner);
                    }
                });

        return found;
    }

    //get every dog with given name and return list with found dogs
    public List<Dog> findDogByName(String name) {
        List<Dog> found = new ArrayList<>();

        //return empty list if user passed null or typed empty input
        if (name == null || name.isEmpty()) {
            return found;
        }


        db.getOwners()
                .forEach(owner -> owner.getDogs()
                        .forEach(dog -> {
                            String dogName = dog.getName().trim().toLowerCase();
                            if (dogName.contains(name.toLowerCase().trim())) {
                                found.add(dog);
                            }
                        }
                ));

        return found;
    }


    //returns walks for the current date
    public List<Walk> getWalks() {
        String date = getCurrentDate();
        return db.getWalks().get(date);
    }

    //returns list of every owner in db
    public List<Owner> getOwners() {
        return db.getOwners();
    }

    //returns a list of every dog in db
    public List<Dog> getDogs() {
        List<Dog> dogs = new ArrayList<>();

        db.getOwners()
                .forEach(owner -> {
                    if (null != owner.getDogs()) {  //check if owner actually has dogs to prevent null pointer
                        dogs.addAll(owner.getDogs());
                    }
                });

        return dogs;
    }

    public void updateDatabase() {
        DatabaseOperations.save(db);
    }


    public void removeOwner(Owner owner) {
        db.getOwners().remove(owner);
        updateDatabase();
    }

    public void removeDog(Dog dog) {
        db.getOwners()
                .forEach(owner -> {
                            if (owner.getDogs().contains(dog)) {
                                owner.removeDog(dog);
                               }
                });

        updateDatabase();
    }

    public void removeWalk(Walk walk) {
        String date = getCurrentDate();
        db.getWalks().get(date).remove(walk);
        DatabaseOperations.save(db);
    }


    //finds the walks for current date for dogs with given name
    public List<Walk> findTodayWalks(String dogName) {
        String date = getCurrentDate();

        //if no walks have been added for the day just return empty list
        if (null == db.getWalks() || !db.getWalks().containsKey(date)) {
            return new ArrayList<>();
        }

        return db.getWalks()
                .get(date)
                .stream()
                .filter(dog -> dog.getDog().getName().toLowerCase().contains(dogName.toLowerCase()))
                .collect(Collectors.toList());
    }

    //returns a list for walks at a given date
    public List<Walk> findWalkByDate(String date) {
        if (db.getWalks().containsKey(date)) {
            return db.getWalks().get(date);
        } else {
            return new ArrayList<>();
        }

    }

    //helper method to return current date formatted as string
    private String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return formatter.format(localDate);
    }
}
