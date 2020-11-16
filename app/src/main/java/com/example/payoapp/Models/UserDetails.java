package com.example.payoapp.Models;

import java.io.Serializable;

public class UserDetails implements Serializable {
    public int ID;
    public String FirstName;
    public String LastName;
    public String Address;
    public String Country;
    public String Email;
    public String Password;
    public String Mobile;

    public UserDetails() {
    }

    public UserDetails(int Id, String fname, String lname, String address, String email, String password, String mobile) //Constructor with all parameters
    {
        ID = Id;
        FirstName = fname;
        LastName = lname;
        Address = address;
        Email = email;
        Password = password;
        Mobile = mobile;
    }
    public UserDetails(String fname, String lname, String address, String email, String password, String mobile) //Constructor with all parameters
    {

        FirstName = fname;
        LastName = lname;
        Address = address;
        Email = email;
        Password = password;
        Mobile = mobile;
    }

    public UserDetails(String Password) //Constructor with one parameter
    {
        this.Password = Password;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}