package com.philipBank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    String firstName;
    String lastName;
    int numOfAccounts = 0;
    private List<Account> accounts;
    LocalDateTime dateOfCreation;


    public User(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        this.dateOfCreation = LocalDateTime.now();
        this.accounts = new ArrayList<>();
    }

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

    public void addAccount(Account account) {
        this.accounts.add(account);
        this.numOfAccounts++;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
