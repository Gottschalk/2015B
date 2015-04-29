package com.example.flo.myapplication;

/**
 * Created by Flo_2 on 14.02.2015.
 */
public class Contact {


    private int id;
    private String name;
    private String number;

    public Contact() {
        super();
    }

    // constructor
    public Contact(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    // constructor
    public Contact(String name, String _phone_number) {
        this.name = name;
        this.number = _phone_number;
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
