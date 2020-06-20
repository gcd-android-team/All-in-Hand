package edu.skku.all_in_hand;

public class MenuItem {
    public String name;
    public String price;
    public String desc;

    public MenuItem(){}

    public MenuItem(String name, String price, String desc){
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

    public String getPrice() {
        return price;
    }
}
