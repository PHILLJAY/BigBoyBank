package com.philipBank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;

public class Account {
    private final int ID;
    private double money;
    private final LocalDateTime dateOfCreation;
    private double interestRate;

    public int getID() {
        return ID;
    }

    public Account(double money, double interestRate) {
        this.money = money;
        this.interestRate = interestRate;
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

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate >=1 || interestRate <0)
            throw new IllegalArgumentException("Interest can not be greater than 1 or less than 0");
        this.interestRate = interestRate;
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
        this.money = this.money-removeAmount;
    }
    public double calculateInterest(@org.jetbrains.annotations.NotNull String period, int numOfPeriods){
        //returns interest for a given account based on period.
        //For reference, interest is given by A = P(1+r/n)^(nt)


        double finalOutput = 0.0;

        switch (period){
            case "daily" -> {
                finalOutput = this.money*Math.pow((1+(this.interestRate/365)),numOfPeriods);
            }
            case "weekly" -> {
                finalOutput = this.money*Math.pow((1+(this.interestRate/52)),numOfPeriods);
            }
            case "monthly" -> {
                finalOutput = this.money*Math.pow((1+(this.interestRate/12)),numOfPeriods);
            }
            case "annual" -> {
                finalOutput =  this.money*Math.pow(1+this.interestRate,numOfPeriods);
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + period);
        }
        BigDecimal bd = new BigDecimal(Double.toString(finalOutput));
        bd = bd.setScale(2, RoundingMode.HALF_EVEN);

        return bd.doubleValue();
    }



}
