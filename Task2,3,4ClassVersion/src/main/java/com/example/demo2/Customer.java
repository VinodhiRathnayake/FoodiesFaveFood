package com.example.demo2;

public class Customer {

    //initializing data
    private String firstName;
    private String secondName;
    private int requiredBurgers;

    //Creates a new Customer object with the specified first name, second name, and required number of burgers.
    public Customer(String firstName, String secondName,int requiredBurgers) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.requiredBurgers = requiredBurgers;
    }

    // Getter method for the first name of the customer
    public String getFirstName() {
        return firstName;
    }

    // Getter method for the second name of the customer
    public String getSecondName() {
        return firstName;
    }

    // Method to get the full name of the customer
    public String getFullName() {
        return firstName + " " + secondName;
    }

    // Getter method for the number of required burgers by the customer
    public int getRequiredBurgers() {
        return requiredBurgers;
    }

}
