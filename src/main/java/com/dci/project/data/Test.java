package com.dci.project.data;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Warehouse one = new Warehouse(1);
        Warehouse two = new Warehouse(2);
        one.addItem(new Item("keyboard", "barnd new", new Date()));
        System.out.println("should result 1 : ");
        System.out.println(one.occupancy());
        System.out.println("should result 0 : ");
        System.out.println(two.occupancy());
    }
}
