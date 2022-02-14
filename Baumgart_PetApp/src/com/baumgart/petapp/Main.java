package com.baumgart.petapp;

import com.baumgart.database.Database;
import com.baumgart.domain.Owner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Owner owner = new Owner("Brent", "Baumgart", "345-554-3456");
//        Owner owner1 = new Owner("Brent", "Baumgart", "345-554-3456");
//        Owner owner2 = new Owner("Brent", "Baumgart", "345-554-3456");
//        Owner owner3 = new Owner("Brent", "Baumgart", "345-554-3456");
//        Owner owner4 = new Owner("Brent", "Baumgart", "345-554-3456");
//
//        Database database = new Database(new ArrayList<>());
//
//        database.addOwner(owner);
//        database.addOwner(owner1);
//        database.addOwner(owner2);
//        database.addOwner(owner3);
//        database.addOwner(owner4);
//
//        save(database);

        open();

    }

    public static void save(Database database) {

        try {
            FileOutputStream file = new FileOutputStream("data.db");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(database);
            out.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Object has been serialized");
    }

    public static void open() {
        Database db;

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("data.db");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            db = (Database) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            List<Owner> data = db.getData();

            for (Owner owner : data) {
                System.out.println(owner);
            }

        }

        catch(IOException ex) {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

}
