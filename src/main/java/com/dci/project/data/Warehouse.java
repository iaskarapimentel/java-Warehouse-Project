package com.dci.project.data;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private int id;
    private List<Item> stock = new ArrayList<Item>();

    public Warehouse(int id){
        this.id = id;
    }

    public int getId(){return this.id;}

    public List<Item> getStock(){return stock;}

    public int occupancy() {
        return stock.size();
    }

    public void addItem(Item item) {
        stock.add(item);
    }

    public List<Item> search(String searchTerm){
        List<Item> foundItems = new ArrayList<Item>();
        for(Item item : stock){
            if(item.toString().contains(searchTerm)){
                foundItems.add(item);
            }
        }
        return foundItems;
    }
}
