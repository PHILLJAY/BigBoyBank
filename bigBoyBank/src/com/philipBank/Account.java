package com.philipBank;

import java.time.LocalDateTime;
import java.util.Random;

public class Account {
    private final int ID;
    private double money;
    private final LocalDateTime dateOfCreation;
    private double interest;

    public int getID() {
        return ID;
    }

    public Account(double money, double interest) {
        this.money = money;
        this.interest = interest;
        Random rand = new Random();
        ID = rand.nextInt(0,100);
        this.dateOfCreation = LocalDateTime.now();
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        if (interest>=1 || interest<0)
            throw new IllegalArgumentException("Interest can not be greater than 1 or less than 0");
        this.interest = interest;
    }

    public void addFunds(double addAmount){
        if(addAmount <=0){
            throw new IllegalArgumentException("value must be positive");
        }
        this.money = this.money+addAmount;
    }

    public void removeFunds(double removeAmount){
        if(removeAmount <=0){
            throw new IllegalArgumentException("value must be positive");
        }
        if ((this.money-removeAmount) <= 0){
            throw new RuntimeException("Error: the following action will cause your bank account to be negative");
        }
        this.money = this.money-removeAmount;
    }
    public void calculateInterest(String period){
        switch (period){
            case "day" -> System.out.println("Calculate daily interest");
            case "week" -> System.out.println("Calculate weekly interest");
            case "month" -> System.out.println("calculate monthly interest");
            default -> throw new IllegalStateException("Unexpected value: " + period);
        }
    }



}
