package com.dci.project.data;

import java.util.List;

public class Employee extends User {

    public Employee(String userName) {
        super(userName);
    }

//    SESSION_ACTIONS
//    The bye method will print a thank you message.
//    To minimize speculations on how the system handles the log data,
//    it has been decided that the guest user will not be shown the summary of its actions.
    public void bye(List<String> actions){
        super.bye(actions);
    }
}
