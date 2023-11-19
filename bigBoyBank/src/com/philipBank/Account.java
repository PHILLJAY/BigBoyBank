package com.philipBank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class Account {
    private final int ID;
    private double money;
    private final LocalDateTime dateOfCreation;
    private double interestRate;

    @Override
    public String toString() {
        return "Account{" +
                "ID=" + ID +
                ", money=" + money +
                ", dateOfCreation=" + dateOfCreation +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(money, account.money) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

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

    public Account(double money) {
        this.money = money;
        this.interestRate = 0;
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
    public double calculateInterest(String period, int numOfPeriods){
        //returns interest for a given account based on period.
        //For reference, interest is given by A = P(1+r/n)^(nt)
        if(numOfPeriods <=0){
            throw new IllegalArgumentException("value must be a positive integer");
        }

        double finalOutput;

        switch (period){
            case "daily" -> finalOutput = this.money*Math.pow((1+(this.interestRate/365)),numOfPeriods);
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

    public void transferMoney(Account account, double transfer){
        if(transfer <=0){
            throw new IllegalArgumentException("value must be positive integer");
        }
        if (this==account){
            throw new IllegalArgumentException("Source and destination cannot be the same account");
        }
        //trying to remove funds first
        if (transfer>this.getMoney()){
            throw new IllegalArgumentException("Error, can not transfer more money than is in the account");
        }
        try{
            this.removeFunds(transfer);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error:" + e);
        }

        try{
            account.addFunds(transfer);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error:" + e);
        }
    }

    public void withdrawMoney(ATM obj, double withdraw){
        if (!obj.isWorking()){
            throw new RuntimeException("ATM is not working");
        }
        if (withdraw>this.getMoney()){
            throw new IllegalArgumentException("Insufficient funds to withdraw");
        }
        try{
            this.removeFunds(withdraw);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error" + e);
        }
        try{
            obj.withdraw(withdraw);
        }catch (Exception e){
            throw new IllegalArgumentException("Error" + e);
        }

    }

    public void depositMoney(ATM obj, double deposit) {
        if (!obj.isWorking()){
            throw new RuntimeException("ATM is not working");
        }
        if (deposit < 0){
            throw new IllegalArgumentException("Cannot deposit a negative amount");
        }
        this.addFunds(deposit);
    }
}

