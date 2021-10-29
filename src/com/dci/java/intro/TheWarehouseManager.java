package com.dci.java.intro;
import static com.dci.java.intro.Repository.WAREHOUSE1;
import static com.dci.java.intro.Repository.WAREHOUSE2;

import java.util.Scanner;
import java.util.*;

/**
 * Provides necessary methods to deal through the Warehouse management actions
 *
 * @author riteshp
 */
public class TheWarehouseManager {
    // =====================================================================================
    // Member Variables
    // =====================================================================================

    // To read inputs from the console/CLI
    private final Scanner reader = new Scanner(System.in);

    private final String[] userOptions = {
            "1. List items by warehouse", "2. Search an item and place an order", "3. Quit"
    };
    // To refer the user provided name.
    private String userName;

    // =====================================================================================
    // Public Member Methods
    // =====================================================================================

    /** Welcome User */
    public void welcomeUser() {
        this.seekUserName();
        this.greetUser();
    }

    public void printingChoices(){
        for(String option : this.userOptions){
            System.out.println(option);
        }
    }

//    /** Ask for user's choice of action */
    public int getUsersChoice() {
    // TODO
        System.out.println("Please choose one option: ");
        printingChoices();
        int userChoice = reader.nextInt();
        return userChoice;
    }

    /** Initiate an action based on given option */
//    Do um switch case
    public void performAction(int userChoice) {
        // TODO
        switch (userChoice) {
            case 1:
                listItemsByWarehouse();
                System.out.println("Would you like to perform another action? ");
            break;
            case 2:
                askItemToOrder();
                searchItemAndPlaceOrder();
                break;
            case 3:
                quit();
                break;
            default:
                System.out.println("Sorry, this operation is not valid. ");
        }
    }

    /**
     * Confirm an action
     *
     * @return action
     */
    public boolean confirm(String message) {
        // TODO
        return false;
    }

    /** End the application */
    public void quit() {
        System.out.printf("\nThank you for your visit, %s!\n", this.userName);
        System.exit(0);
    }

    // =====================================================================================
    // Private Methods
    // =====================================================================================

    /** Get user's name via CLI */
    private void seekUserName() {
        System.out.println("Please enter your name: ");
        this.userName = reader.nextLine();
    }

    /** Print a welcome message with the given user's name */
    private void greetUser() {
        // TODO
        System.out.println("Hello " + this.userName + " .Welcome to our Warehouse System. How can I help you?");
    }

//    1-List items by warehouse
    private void listItemsByWarehouse() {
        // TODO
        System.out.println("WAREHOUSE1");
        listItems(WAREHOUSE1);
        System.out.println("WAREHOUSE2");
        listItems(WAREHOUSE2);
    }

    private void listItems(String[] warehouse) {
        // TODO
        for (String listItems : warehouse) {
            System.out.println(listItems);
        }
    }
    public int searchItemInWarehouse(String[] warehouse, String searchItem){
        int numOfItems = 0;
        for (String item : warehouse) {
            if(searchItem.contains(item)){
                numOfItems++;
            }
        }
        return numOfItems;
    }
//  Ask the user to input an item name and search the warehouses
    private void searchItemAndPlaceOrder() {
        // TODO
        askItemToOrder();
        searchItemInWarehouse(WAREHOUSE1, askItemToOrder());
        searchItemInWarehouse(WAREHOUSE2, askItemToOrder());
    }

    /**
     * Ask the user to specify an Item to Order
     *
     * @return String itemName
     */
    private String askItemToOrder() {
        // TODO
        System.out.println("Please enter the item that your are looking for it: ");
        String searchItem = reader.nextLine();
        return searchItem;
    }

    /**
     * Calculate total availability of the given item
     *
     * @param itemName itemName
     * @return integer availableCount
     */
//    private int getAvailableAmount(String itemName) {
//        // TODO
//    }

    /**
     * Find the count of an item in a given warehouse
     *
     * @param item the item
     * @param warehouse the warehouse
     * @return count
     */
//    private int find(String item, String[] warehouse) {
//        // TODO
//    }

    /** Ask order amount and confirm order */
    private void askAmountAndConfirmOrder(int availableAmount, String item) {
        // TODO
    }

    /**
     * Get amount of order
     *
     * @param availableAmount
     * @return
     */
//    private int getOrderAmount(int availableAmount) {
//        // TODO
//    }
}
