package com.baumgart.database;

import java.io.*;

//saves and loads database
public class DatabaseOperations {

    public static void save(Database database) {

        //after reading about try-catch I feel that try-with-resources is the best way to handle this
        //as I don't have to worry about closing streams
        try(FileOutputStream fos = new FileOutputStream("database");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(database);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    //I chose to delegate the responsibility of catching errors to the caller
    //I did this because if an error does occur, it's because no file was found
    //so it becomes the callers responsibility to handle error and make a new db
    public static Database loadData() throws IOException, ClassNotFoundException {
        Database database;

        FileInputStream fis = new FileInputStream("database");
        ObjectInputStream ois = new ObjectInputStream(fis);

        database = (Database) ois.readObject();

        return database;
    }


}
