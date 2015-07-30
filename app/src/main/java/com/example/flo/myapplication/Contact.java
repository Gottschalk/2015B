package com.example.flo.myapplication;


public class Contact {


    private int id;
    private String name;
    private String number;
    private String street;
    private String plz;
    private String city;

    public Contact() {
        super();
    }

    public Contact(int id, String name, String number, String street, String plz, String city) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.street = street;
        this.plz = plz;
        this.city = city;
    }

    public Contact(String name, String _phone_number, String street, String plz, String city) {
        this.name = name;
        this.number = _phone_number;
        this.street = street;
        this.plz = plz;
        this.city = city;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getPLZ() {
        return plz;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPLZ(String plz) {
        this.plz = plz;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ", number=" + number + ", street=" + street
                + ", plz=" + plz + ", city=" + city + "]";
    }

}
