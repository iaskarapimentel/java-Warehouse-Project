package com.dci.project.data;

import java.util.List;
import java.util.Objects;

public class User {

    protected String name = "Anonymous";
//    The isAuthenticated property will be set to false and it will not change for
//    the standard users, but it will still be defined as a property to make the main
//    code simpler (here, you will check isAuthenticated without having to check first if it is an employee or a guest).
    private boolean isAuthenticated = false;

    public User(){};

    public User(String userName){
        this.name = userName;
    }

    public String getName(){
        return name;
    }

    public boolean authenticate(String password){
        return false;
    }

    public boolean isNamed(String name){
        return Objects.equals(name, this.name);
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
