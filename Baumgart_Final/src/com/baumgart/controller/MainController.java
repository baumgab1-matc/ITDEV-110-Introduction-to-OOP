package com.baumgart.controller;

import com.baumgart.database.DatabaseOperations;
import com.baumgart.database.Database;
import com.baumgart.services.DatabaseService;
import com.baumgart.view.CRUDView;
import com.baumgart.view.ErrorView;
import com.baumgart.view.View;
import java.util.Scanner;

public class MainController {
        //main view of the application
        private final View view;
        //helper view to print errors from invalid input
        private final ErrorView errorView;
        //helper view for output messages when adding, used by addController
        private final CRUDView CRUDView;
        //scanner object that gets passed around
        private final  Scanner scanner;
        //this the "database" of the app
        private final Database database;
        //service that interacts with the database
        private final DatabaseService databaseService;
        //helper controller to add
        private final CRUDController crudController;
        //helper controller to print searched items
        private final PrintController printController;


    public MainController(Database database, Scanner scanner) {
            this.scanner = scanner;
            this.database = database;
            this.databaseService = new DatabaseService(this.database);

            //init views
            this.view = new View(this.scanner);
            this.errorView = new ErrorView();
            this.CRUDView = new CRUDView(this.scanner);

            //init helper controllers
            this.crudController = new CRUDController(databaseService, CRUDView, errorView);
            this.printController = new PrintController(databaseService, view, errorView);
        }


        //main loop of the app
        public void powerOn() {
            boolean isRunning = true;
            String choice;

            while (isRunning) {
                //display menu
                view.printMenu();

                //get user choice
                choice = view.getChoice();

                switch (choice) {
                    case "1":
                        listWalks();
                        break;

                    case "2":
                        handleAdding();
                        break;

                    case "3":
                        handleSearching();
                        break;

                    case "4":
                        handleUpdates();
                        break;

                    case "5":
                        handleRemoves();
                        break;

                    case "6":
                        handleViewAll();
                        break;

                    case "7":
                        isRunning = false;
                        handleClose();
                        break;

                    default:
                        errorView.selectionError();
                }
            }
        }

        //prints out all the lists added for the day
        private void listWalks() {
            printController.printDailyWalks();
        }

        //this is the adding adding menu
        //the logic to adding new objects in delegated to the CRUDController
        private void handleAdding() {
            boolean isAdding = true;

            while (isAdding) {
                view.printAddMenu();
                String choice = view.getChoice().trim();

                switch (choice) {
                    case "1":
                        crudController.createWalk();
                        break;

                    case "2":
                        crudController.createOwner();
                        break;

                    case "3":
                        crudController.createNewOwnerDogs();

                        break;

                    case "4":
                        isAdding = false;
                        break;

                    default:
                        errorView.selectionError();
                }
            }
        }


    //search menu
    private void handleSearching() {
            boolean isSearching = true;

            while (isSearching) {
                view.printSearchMenu();
                String choice = view.getChoice();

                switch (choice) {
                    case "1":
                        printController.printWalksByDate();
                        break;

                    case "2":
                        printController.printOwnerByName();
                        break;

                    case "3":
                        printController.printDogsByName();
                        break;

                    case "4":
                        isSearching = false;
                        break;

                    default:
                        errorView.selectionError();
                }
            }
        }

        //update menu
        private void handleUpdates() {
            boolean isUpdating = true;

            while (isUpdating) {
                view.printUpdateMenu();
                String choice = view.getChoice();

                switch (choice) {
                    case "1":
                        crudController.updateWalk();
                        break;

                    case "2":
                        crudController.updateOwner();
                        break;

                    case "3":
                        crudController.updateDog();
                        break;

                    case "4":
                        isUpdating = false;
                        break;

                    default:
                        errorView.selectionError();
                }
            }

        }

        //remove menu
        private void handleRemoves() {
            boolean isRemoving = true;

            while (isRemoving) {
                view.removeMenu();
                String choice = view.getChoice();

                switch (choice) {
                    case "1":
                        crudController.deleteWalk();
                        break;

                    case "2":
                        crudController.deleteOwner();
                        break;

                    case "3":
                        crudController.deleteDog();
                        break;

                    case "4":
                        isRemoving = false;
                        break;

                    default:
                        errorView.selectionError();
                }
            }
        }

        //viewing menu
        private void handleViewAll() {
            boolean isViewing = true;

            while (isViewing) {
                view.viewAllMenu();
                String choice = view.getChoice();

                switch (choice) {
                    case "1" :
                        printController.printAllOwners();
                        break;

                    case "2":
                        printController.printAllDogs();
                        break;

                    case "3":
                        isViewing = false;
                        break;

                    default:
                        errorView.selectionError();
                }
            }
        }

        //saves data when app is closed
        private void handleClose() {
            DatabaseOperations.save(database);
        }

}
