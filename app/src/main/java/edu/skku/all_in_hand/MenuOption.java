package edu.skku.all_in_hand;

public class MenuOption {
    public String name;
    public String price;

    public MenuOption(){}

    public MenuOption(String name, String price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
