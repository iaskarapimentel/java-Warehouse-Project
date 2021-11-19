package com.dci.project;
import com.dci.project.data.Item;
import com.dci.project.data.StockRepository;
import com.dci.project.data.Person;
import com.dci.project.data.PersonnelRepository;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides necessary methods to deal through the Warehouse management actions
 *
 * @author riteshp
 */
public class TheWarehouseManager {
    // =====================================================================================
    // Member Variables
    private static List<String> SESSION_ACTIONS = new ArrayList<String>();
    // =====================================================================================

    // To read inputs from the console/CLI
    private final Scanner reader = new Scanner(System.in);

    private final String[] userOptions = {
            "1. List items by warehouse", "2. Search an item and place an order", "3. Browse by category", "4. Quit"
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

    /** Ask for user's choice of action */
    public int getUsersChoice() {
    // TODO
        System.out.println("What would you like to do? ");
        printingChoices();
        int userChoice = reader.nextInt();
        reader.nextLine();
        return userChoice;
    }

    /** Initiate an action based on given option */
    public void performAction(int userChoice) {
        switch (userChoice) {
            case 1:
                listItemsByWarehouse();
            break;
            case 2:
                searchItemAndPlaceOrder();
                break;
            case 3:
//                Browse by category option
//                browseByCategory();
                break;
            case 4:
                quit();
                break;
            default:
                System.out.println("Sorry, this operation is not valid. ");
//          I must call again the printing choices for the user see the numbers that are valid
        }
    }

    /**
     * Confirm an action
     *
     * @return action
     */
    public boolean confirm(String message) {
        String answer;
        do{
            System.out.printf("%s (y/n)\n",message);
            answer = this.reader.nextLine();
            if(answer.length() > 0){
                answer = answer.trim();
            }
            answer = answer.toLowerCase();

        }while(!answer.startsWith("y") && ! answer.startsWith("n"));
        return answer.startsWith("y");
    }

    /** End the application */
    public void quit() {
        System.out.printf("\nThank you for your visit, %s!\n", this.userName);
        printSessionActions();
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
        System.out.println("Hello " + this.userName + ". Welcome to our Warehouse System");
    }

//    1-List items by warehouse
//    From Collections - the method that take the number of warehouse as parameter is Rep.getWarehouse();
    private void listItemsByWarehouse() {
        Set<Integer> idsWarehouse = StockRepository.getWarehouses();
        int totalListedItems = 0;
        for(int id : idsWarehouse){
            List<Item> itemsByWarehouse = StockRepository.getItemsByWarehouse(id);
            System.out.println("WAREHOUSE" + id);
            listItems(itemsByWarehouse);
            System.out.println("Total items in WAREHOUSE : " +id+  " - " + itemsByWarehouse.size());
            totalListedItems += itemsByWarehouse.size();
        }

        String sentenceForListedItems = "Listed " + totalListedItems + " items.";
        SESSION_ACTIONS.add(sentenceForListedItems);

//        Call this method(getTotalListedItems),
//        get the number and construct appropriate string 'Listed {number} items.'.
//        Then add this string to the static List of String i.e 'SESSION_ACTIONS'.
    }

//    This method is printing
    private void listItems(List<Item> itemsByWarehouse) {
        for (Item item : itemsByWarehouse) {
            System.out.println("- " + item);
        }
    }

    /**
     *
     * @param itemsByWarehouse list of items in the warehouse
     * @param itemName the item that the user is looking for it.
     * @return the list of items that searchItem
     */
    public List<Item> find(List<Item> itemsByWarehouse, String itemName){

        List<Item> listItems = new ArrayList<Item>();
        for (Item item : itemsByWarehouse) {
            if(itemName.toLowerCase().contains(item.toString().toLowerCase())){
                listItems.add(item);
            }
        }
        return listItems;
    }

//    created an empty list numItemsByWarehouse then
//    after adding the listOfSearchItem which is the list containing the search item itself
//    each element [n, n] in the array list numItemsByWarehouse is the quantity of items in one warehouse
    private void searchItemAndPlaceOrder() {
        String itemName = askItemToOrder();

        Set<Integer> idsWarehouse = StockRepository.getWarehouses();
        List<Integer> numItemsByWarehouse = new ArrayList<Integer>();
        for(int id : idsWarehouse) {
            List<Item> itemsByWarehouse = StockRepository.getItemsByWarehouse(id);
            List<Item> listOfSearchItem = find(itemsByWarehouse, itemName);
            for(Item item : listOfSearchItem) {
                System.out.println("Warehouse " + id + " (in stock for " + daysInStock(item) + " days)" );
            }
            numItemsByWarehouse.add(listOfSearchItem.size());
        }

        int availableAmount = checkingAvailability(numItemsByWarehouse.get(0), numItemsByWarehouse.get(1));
        if( availableAmount > 0){
            String message = "Would you like to order this item?";
            if (confirm(message)) {
                askAmountAndConfirmOrder(availableAmount, itemName);
            }
        }

        String appropriateArticle = getAppropriateIndefiniteArticle(itemName);
        String sentenceForListedItems = "Searched " + appropriateArticle + " " + itemName + ".";
        SESSION_ACTIONS.add(sentenceForListedItems);
    }

    /**
     *
     * @param numItemsWarehouse1
     * @param numItemsWarehouse2
     * @return
     */
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
            if (numItemsWarehouse1 > numItemsWarehouse2) {
                System.out.println("Maximum availability: " + numItemsWarehouse1 + " in Warehouse 1");
            } else {
                System.out.println("Maximum availability: " + numItemsWarehouse2 + " in Warehouse 2");
            }
            return (numItemsWarehouse1 + numItemsWarehouse2);
        }
//        in the Collections task we need to specified that a non found item has 0 available.
        System.out.println("Not in stock");
        return 0;
    }

    /*
    https://www.baeldung.com/java-date-difference
    https://www.baeldung.com/java-date-to-localdate-and-localdatetime
     */
    private long daysInStock(Item item){
        LocalDate today = LocalDate.now();
        LocalDate initialDate = item.getDateOfStock().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return ChronoUnit.DAYS.between(initialDate, today);
    }

    /**
     * Ask the user to specify an Item to Order
     *
     * @return String itemName
     */
    private String askItemToOrder() {
        System.out.println("Please enter the item that your are looking for it: ");
        return reader.nextLine();
    }

//** Ask order amount and confirm order*/
    private void askAmountAndConfirmOrder(int availableAmount, String item) {
     System.out.println("How many of this item do you want?");
     int desiredAmount = Integer.parseInt(reader.nextLine());

     if(desiredAmount <= availableAmount) {
         System.out.println("The order has been placed: " + item + " - " + desiredAmount);
     } else {
         System.out.println("The desired amount is higher than available. ");
         if(confirm("Do you want to order the maximum available amount?")){
             System.out.println("The order has been placed: " + item + " - " + availableAmount);
         }
     }
    }

    /**
     * Get amount of order
     * ask the user how many do they want = Looks he receives a parameter maybe you should take care about the statements here
     * Take care if the user type 0
     *
     * @param
     * @return
     */
//    private int getOrderAmount(int availableAmount) {
//        System.out.println("How many of this item do you want?");
//        int desiredAmount = reader.nextInt();
//        reader.nextLine();
//
//        if(desiredAmount <= availableAmount) {
//            System.out.println("The order has been placed: " + item + " - " + desiredAmount);
//        } else {
//            System.out.println("The desired amount is higher than available. ");
//            System.out.println("Do you want to order the maximum available amount? y/n");
//            String answer = reader.nextLine();
//            if(Objects.equals(answer, "y")){
//                System.out.println("The order has been placed: " + item + " - " + availableAmount);
//            }
//        }

//    }

//    New menu option (browse by category)
//    I need the amountAvailable for each category
    private void browseByCategory(String category){
        System.out.println(menuOfCategory());
//        int availableAmount = checkingAvailability(numItemsByWarehouse.get(0), numItemsByWarehouse.get(1));
//        numItemsByWarehouse


    }
/*
       This menuOfCategory must have a number for each category
       Example:
        1. Keyboard (34)
        2. Smartphone (39)
        for this reason the Map is a good choice.
        after this I have to bind the amountAvailable  between ();
 */
    private Map<Integer, String> menuOfCategory() {
        Map<Integer, String> menuOptionsOfCategories = new HashMap<Integer, String>();
        int option = 1;
        for (String category : StockRepository.getCategories()) {
            menuOptionsOfCategories.put(option++, category);
        }
        return menuOptionsOfCategories;
    }

//    SESSION_ACTIONS - methods related
//    returns an integer value that is the number of the total items in the list.
    private int getTotalListedItems(int totalListedItems){
        return totalListedItems;
    }

    private String getAppropriateIndefiniteArticle(String itemName){
//        I can establish a patter a,e,i,o,u if the itemName start with this patter the article will be "an" else a
        String appropriateArticle;
        String vowel = "[aeiou]";
        Pattern patter = Pattern.compile(vowel);
        Matcher matcher = patter.matcher(itemName);

        if(itemName.startsWith(patter.toString())){
            appropriateArticle = "an";
        } else {
            appropriateArticle = "a";
        }
       return appropriateArticle;
    }

    private void printSessionActions(){
        int count = 1;
        for(String action : SESSION_ACTIONS){
            System.out.println(count + ". " + action);
            count++;
        }
    }

}
