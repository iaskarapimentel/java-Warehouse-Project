package com.dci.project.data;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private String password;
    private List<Employee> headOf = new ArrayList<Employee>();

    public Employee(String userName, String password, List<Employee> headOf) {
        super(userName);
        this.password = password;
        this.headOf = headOf;
    }

    public String getPassword() {
        return this.password;
    }

//    The authenticate method will also be overwritten,
//    because in this case we need to check if the password is valid.
//    The method will return true if the argument password matches the property password of the object.
    public boolean authenticate(String password){
        return (password.equals(this.password));
    }

//    The order method will print the name of the item and amount ordered by the user when they place an order.
    public void order(String item, int amount){
        System.out.println(item + "-" + amount);
    }

    public void greet(){
        System.out.println("Hello, "+ this.name + "!\n" +
                "If you experience a problem with the system,\n" +
                "please contact technical support.");
    }

//    The bye method will also print a thank you message but, additionally,
//    it will print the summary of actions taken during the session.
//    This method should call the parent method to print the message defined
//    there and then print the list of actions. It should not redefine the Thank you for your visit, {name}! message.
    public void bye(List<String> actions){
        super.bye(actions);
        for(String action : actions){
            System.out.println(action);
        }
    }

}
