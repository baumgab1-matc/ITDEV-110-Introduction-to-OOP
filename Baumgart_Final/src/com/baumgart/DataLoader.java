package com.baumgart;

import com.baumgart.database.Database;
import com.baumgart.database.DatabaseOperations;
import com.baumgart.model.Dog;
import com.baumgart.model.Owner;
import com.baumgart.model.Walk;

import java.util.*;


//used to load mock data
public class DataLoader {


    public static void loadMockData() {
        Owner brent = new Owner("Brent", "Baumgart", "334-554-3344");
        Owner jane = new Owner("Jane", "Doe", "543-554-3344");
        Owner peter = new Owner("Peter", "Wick", "414-223-9898");
        Owner joe = new Owner("Joe", "Smith", "262-776-6654");
        Owner paul1 = new Owner("Paul", "Christopher", "414-414-41414");
        Owner paul2 = new Owner("Paul", "Robertson", "333-333-3333");


        Dog petey = new Dog("Petey", "Sheltie", "M", 4);
        Dog max1 = new Dog("Max", "Golden Retriever", "M", 5);
        Dog max2 = new Dog("Max", "Mix", "M", 8);
        Dog chloe = new Dog("Chloe", "Lab", "F", 7);
        Dog jake = new Dog("Jake", "Bulldog", "M", 9);
        Dog ricky = new Dog("Ricky", "Dachshund", "M", 4);
        Dog millie = new Dog("Millie", "Husky", "F", 2);
        Dog tiny = new Dog("Tiny", "Great Dane", "M", 9);
        Dog lenny = new Dog("Lenny", "Greyhound", "M", 6);
        Dog buddy = new Dog("Buddy", "Pitbull", "M", 6);

        brent.addDog(petey);
        brent.addDog(max1);

        jane.addDog(max2);

        peter.addDog(chloe);
        peter.addDog(jake);

        joe.addDog(ricky);

        paul1.addDog(millie);
        paul1.addDog(tiny);
        paul1.addDog(lenny);

        paul2.addDog(buddy);

        List<Owner> owners = new ArrayList<>();
        owners.add(brent);
        owners.add(jane);
        owners.add(peter);
        owners.add(joe);
        owners.add(paul1);
        owners.add(paul2);

        //walks
        Map<String, List<Walk>> walks = new HashMap<>();

        String date1 = "04-28-2021";
        String date2 = "04-29-2021";
        String date3 = "04-30-2021";
        String date4 = "05-01-2021";

        //walks for date 1
        Walk walk1 = new Walk("morning", petey);
        Walk walk2 = new Walk("morning", max1);
        Walk walk3 = new Walk("morning", max2);
        Walk walk4 = new Walk("afternoon", buddy);
        Walk walk5 = new Walk("afternoon", millie);
        Walk walk6 = new Walk("evening", ricky);

        List<Walk> date1Walks = new ArrayList<>();
        date1Walks.add(walk1);
        date1Walks.add(walk2);
        date1Walks.add(walk3);
        date1Walks.add(walk4);
        date1Walks.add(walk5);
        date1Walks.add(walk6);

        walks.put(date1, date1Walks);

        //walks for date 2
        Walk walk7 = new Walk("morning", petey);
        Walk walk8 = new Walk("morning", buddy);
        Walk walk9 = new Walk("morning", jake);
        Walk walk10 = new Walk("evening", chloe);

        List<Walk> date2Walks = new ArrayList<>();
        date2Walks.add(walk7);
        date2Walks.add(walk8);
        date2Walks.add(walk9);
        date2Walks.add(walk10);

        walks.put(date2, date2Walks);

        //walks for date 3
        Walk walk11 = new Walk("morning", tiny);
        Walk walk12 = new Walk("morning", jake);
        Walk walk13 = new Walk("afternoon", max1);

        List<Walk> date3Walks = new ArrayList<>();
        date3Walks.add(walk11);
        date3Walks.add(walk12);
        date3Walks.add(walk13);

        walks.put(date3, date3Walks);


        //walks for date 4
        Walk walk14 = new Walk("morning", petey);
        Walk walk15 = new Walk("morning", lenny);
        Walk walk16 = new Walk("morning", max2);
        Walk walk17 = new Walk("morning", tiny);

        List<Walk> date4Walks = new ArrayList<>();
        date4Walks.add(walk14);
        date4Walks.add(walk15);
        date4Walks.add(walk16);
        date4Walks.add(walk17);

        walks.put(date4, date4Walks);


        Database db = new Database(owners, walks);

        DatabaseOperations.save(db);
    }



}
