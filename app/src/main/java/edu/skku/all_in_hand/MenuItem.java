package edu.skku.all_in_hand;

public class MenuItem {
    public String name;
    public int price;
    public String desc;

    public MenuItem(){}

    public MenuItem(String name, int price, String desc){
        this.name=name;
        this.price=price;
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
