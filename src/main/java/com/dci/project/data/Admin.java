package com.dci.project.data;

public class Admin extends Employee {

    public Admin(){}

    public Admin(String userName, String password){
        super(userName, password, null);
    }

    public void greet(){
        System.out.println("Hello, "+ this.name + "!\n" +
                "Welcome to the Admin Panel.\n" +
                "With higher authority comes higher responsibility.");
    }

}
