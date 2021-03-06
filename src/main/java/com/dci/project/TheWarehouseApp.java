package com.dci.project;
/**
 *
 * @author riteshp
 */
public class TheWarehouseApp {
    /**
     * Execute the <i>TheWarehouseApp</i>
     *
     * @param args
     */
    static  boolean IS_EMPLOYEE;

    public static void main(String[] args) {
        TheWarehouseManager theManager = new TheWarehouseManager();

        // Welcome User
        theManager.welcomeUser();

//         Get the user's choice of action and perform action
        do {
            int choice = theManager.getUsersChoice();
            theManager.performAction(choice);

//             confirm to do more
            if (!theManager.confirm("Do you want to perform another action?")) {
                theManager.quit();
            }

        } while (true);
    }
}