package com.dci.project;
import com.dci.project.data.Item;
import com.dci.project.data.Repository;

//import static resources.data.json;
//import static dci.java.intro.Repository.WAREHOUSE1;
//import static dci.java.intro.Repository.WAREHOUSE2;

import java.sql.Array;
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
        reader.nextLine();
        return userChoice;
    }

    /** Initiate an action based on given option */
//    Do um switch case
    public void performAction(int userChoice) {
        switch (userChoice) {
            case 1:
                listItemsByWarehouse();
//                System.out.println("Would you like to perform another action? ");
            break;
            case 2:
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
        System.out.println("Hello " + this.userName + ". Welcome to our Warehouse System. How can I help you?");
    }

//    1-List items by warehouse
//    the method that take the number of warehouse as parameter is Rep.getWarehouse();
    private void listItemsByWarehouse() {
        Set<Integer> idsWarehouse = Repository.getWarehouses();
        for(int id : idsWarehouse){
            List<Item> itemsByWarehouse = Repository.getItemsByWarehouse(id);
            System.out.println("WAREHOUSE" + id);
            listItems(itemsByWarehouse);
            System.out.println("Total items in WAREHOUSE : " +id+  ":" + itemsByWarehouse.size());
        }
    }

//    This method is printing
    private void listItems(List<Item> itemsByWarehouse) {
        for (Item item : itemsByWarehouse) {
            System.out.println(item);
        }
    }

    /**
     *
     * @param itemsByWarehouse list of items in the warehouse
     * @param itemName the item that the user is looking for it.
     * @return the number of items that searchItem
     */

    public int searchItemInWarehouse(List<Item> itemsByWarehouse, String itemName){

        int numOfItems = 0;
        for (Item item : itemsByWarehouse) {
            if(itemName.toLowerCase().contains(item.toString().toLowerCase())){
                numOfItems++;
            }
        }
        return numOfItems;
    }

//    Ask the user to input an item name and search the warehouses
//    if has item - ask user if he wants to order the item
//    if the user choose y - proceed with order method (independent one)
//    Open a space for the client type his answer y or n

//    numItemsByWarehouse each element in the array list is the quantity of items in one warehouse
    private void searchItemAndPlaceOrder() {
        String itemName = askItemToOrder();

        Set<Integer> idsWarehouse = Repository.getWarehouses();
        List<Integer> numItemsByWarehouse = new ArrayList<Integer>();
        for(int id : idsWarehouse) {
            List<Item> itemsByWarehouse = Repository.getItemsByWarehouse(id);
            numItemsByWarehouse.add(searchItemInWarehouse(itemsByWarehouse, itemName));
        }

        int itemsAvailableInAllWarehouse = checkingAvailability(numItemsByWarehouse.get(0), numItemsByWarehouse.get(1));
        if( itemsAvailableInAllWarehouse > 0){
            System.out.println("Would you like to order this item?(y/n)");
                if (Objects.equals(userAnswerToOrder(reader.nextLine()), "y")) {
//                    create the method for order and invoke here
                    System.out.println("He answer yes");
            }
        }
    }
//take the user answer if y place order if n
    public String userAnswerToOrder(String answer) {
        return answer;
    }

//    Search item
//    total of items - location
//    Change this method to getAvailableAmount to return the number of items instead only printing
//    You need this numbers to place order.
    private int checkingAvailability(int numItemsWarehouse1, int numItemsWarehouse2){

        if(numItemsWarehouse1 > 0 && numItemsWarehouse2 == 0 ){
            System.out.println("Amount available: " + numItemsWarehouse1 + "\nLocation: Warehouse 1.");
            return numItemsWarehouse1;
        }

        if(numItemsWarehouse1 == 0 && numItemsWarehouse2 > 0){
            System.out.println("Amount available: " + numItemsWarehouse2 + "\nLocation: Warehouse 2.");
            return numItemsWarehouse2;
        }

        if(numItemsWarehouse1 > 0 && numItemsWarehouse2 > 0){
            System.out.println("Amount available: " + (numItemsWarehouse1 + numItemsWarehouse2));
            System.out.println("Location: Both warehouses");
            if (numItemsWarehouse1 > numItemsWarehouse2) {
                System.out.println("Maximum availability: " + numItemsWarehouse1 + " in Warehouse 1");
            } else {
                System.out.println("Maximum availability: " + numItemsWarehouse2 + " in Warehouse 2");
            }
            return (numItemsWarehouse1 + numItemsWarehouse2);
        }
        System.out.println("Not in stock");
        return 0;
    }

    /**
     * Ask the user to specify an Item to Order
     *
     * @return String itemName
     */
    private String askItemToOrder() {
        // TODO
        System.out.println("Please enter the item that your are looking for it: ");
        String itemName = reader.nextLine();
        return itemName;
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
