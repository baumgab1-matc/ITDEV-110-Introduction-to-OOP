package com.baumgart.view;

//handles outputting error messages to user
public class ErrorView {

    //prints if user selects invalid option from a menu
    public void selectionError() {
        System.out.println("Error: That is not a valid selection choice! Try again!");
    }

    //prints if no dogs in database are found with given name
    public void noDogsFoundError(String name) {
        System.out.println("\nError: No dog found with name '" + name + "'.");
    }

    //prints for when no owners in database are found with given name
    public void noOwnerFound(String name) {
        System.out.println("\nError: No owner found with name '" + name + "'.");
    }

    //prints if user entered empty input
    public void emptyInputError() {
        System.out.println("Error: Input cannot be empty! Try again!");
    }

    //prints if user is suppose to enter number but enters string/number < 0
    public void notIntegerError() {
        System.out.println("Error: Input was not positive number.");
    }

    //prints if something besides M/F is given as dogs gender
    public void genderError() {
        System.out.println("Error: Dogs gender must be 'M' or 'F'.");
    }

    //prints if user entered a string instead of int for dogs age
    public void ageError() {
        System.out.println("Error: Dogs age must be a number.");
    }

    //prints if walk is not morning, afternoon or evening
    public void walkTimeError() {
        System.out.println("Error: Time must be 'morning', 'afternoon' or 'evening'.");
    }

    //prints if something is not formatted as dd-MM-yyyy
    public void dateError() {
        System.out.println("Error: Input was not valid date. Must be 'dd-MM-yyyy'.");
    }

    public void noWalkFound(String dogName) {
        System.out.println("Error: No walks found for " + dogName);
    }
}
