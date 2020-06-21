package edu.skku.all_in_hand;

public class MenuOption {
    public String name;
    public int price;
    public boolean isSelected;

    public MenuOption(){}

    public MenuOption(String name, int price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

