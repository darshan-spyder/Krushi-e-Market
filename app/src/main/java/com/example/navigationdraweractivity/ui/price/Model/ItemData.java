package com.example.navigationdraweractivity.ui.price.Model;

public class ItemData {

    public String name, image;
    public String price;

    public ItemData(){

    }

    public ItemData(String name,String price, String image){
        this.name = name;
        this.price = price;
        this.image = image;

    }

    public String getName() {
        return name;

    }
    public String getPrice() {
        return price;

    }

    public void setName(String name, String price) {
        this.name = name;
        this.price = price;

    }

    public String getImage() {
        return image;
    }

//    public void setImage(String image) {
//        this.image = image;
//    }
}
