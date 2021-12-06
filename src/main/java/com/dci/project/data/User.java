package com.dci.project.data;

import java.util.List;
import java.util.Objects;
//Make User an abstract class

public abstract class User {

    protected String name = "Anonymous";

    private boolean isAuthenticated = false;

    public User(){};

    public User(String userName){
        this.name = userName;
    }

    public String getName(){
        return name;
    }
//  Make authenticate method in User class abstract method.
    public abstract boolean authenticate(String password);

    public boolean isNamed(String name){
        return Objects.equals(name, this.name);
    }

//  Make greet and buy methods in User class abstract and migrate these methods as is to Guest class
    public abstract void greet();

    public abstract void bye(List<String> actions);
}
