package com.philipBank;

import java.time.LocalDateTime;

public class User {


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumOfAccounts() {
        return numOfAccounts;
    }

    public void setNumOfAccounts(int numOfAccounts) {
        this.numOfAccounts = numOfAccounts;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }


    String firstName;
    String lastName;
    int numOfAccounts = 0;

    LocalDateTime dateOfCreation;

    public User(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        LocalDateTime tempDate = LocalDateTime.now();
        this.dateOfCreation = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
