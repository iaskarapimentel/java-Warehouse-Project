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
//    Imprimir e pegar a opcao que o usuario escolheu
//    pegar e retornar o numero dado no input
    public int getUsersChoice() {
    // TODO
        System.out.println("Please choose one option: ");
        printingChoices();
        int userChoice = reader.nextInt();
        return userChoice;
    }

    /** Initiate an action based on given option */
//    Fazer um switch case
    public void performAction(int option) {
        // TODO
    }

    /**
     * Confirm an action
     *
     * @return action
     */
//    public boolean confirm(String message) {
//        // TODO
//    }

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
        System.out.println("Hello " + this.userName + " welcome to our Warehouse System. How can I help you?");
    }

    private void listItemsByWarehouse() {
        // TODO
    }

    private void listItems(String[] warehouse) {
        // TODO
    }

    private void searchItemAndPlaceOrder() {
        // TODO
    }

    /**
     * Ask the user to specify an Item to Order
     *
     * @return String itemName
     */
//    private String askItemToOrder() {
//        // TODO
//    }

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
