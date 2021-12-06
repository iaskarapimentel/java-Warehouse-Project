package com.dci.project.data;

import java.util.List;

public class Guest extends User{

    public Guest(String userName) {
        super(userName);
    }

    @Override
    public boolean authenticate(String password) {
        return false;
    }

    public void greet(){
        System.out.println("Hello, " + this.name + "\n" +
                "Welcome to our Warehouse Database.\n" +
                "If you don't find what you are looking for,\n" +
                "please ask one of our staff members to assist you.");
    }

    public void bye(List<String> actions){
        System.out.printf("\nThank you for your visit, %s!\n", this.name);
    }
}
