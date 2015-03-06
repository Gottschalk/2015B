package com.example.flo.myapplication;

/**
 * Created by Flo_2 on 14.02.2015.
 */
public class Contact {

    private int icon;
    private int id;
    private String name;
    private String number;

    public Contact() {
        super();
    }

    public Contact(int id, int icon, String name, String number) {
        super();
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.number = number;

    }

    public int getIcon(){
        return icon;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ", number=" + number
                + "]";
    }

}
