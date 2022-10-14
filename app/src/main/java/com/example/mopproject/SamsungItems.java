package com.example.mopproject;

public class SamsungItems {
    int image;
    String name;
    String price;

    public SamsungItems(int image, String name, String price){
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }

    public void setImage(int image){
        this.image = image;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(String price){
        this.price = price;
    }
}


