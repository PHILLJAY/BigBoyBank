package com.philipBank;

public class ATM {
    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    boolean working;
    public ATM(boolean x) {
        working = x;
    }

    public void withdraw(double amount) throws Exception{

        if (!working) {
            throw new Exception("The account is not currently working.");
        }

        if (amount < 0) {
            throw new Exception("Cannot withdraw a negative amount.");
        }


        int remainingCents = (int) (amount * 100);
        int dollars = remainingCents / 100;
        remainingCents %= 100;
        int quarters = remainingCents / 25;
        remainingCents %= 25;
        int dimes = remainingCents / 10;
        remainingCents %= 10;
        int nickels = remainingCents / 5;
        remainingCents %= 5;
        int pennies = remainingCents;

        System.out.printf("Withdrawal successful. You received %d dollars, %d quarters, %d dimes, %d nickels, and %d pennies.", dollars, quarters, dimes, nickels, pennies);
    }

    public void deposit(double amount) throws Exception {
        if (!working) {
            throw new Exception("The ATM is not currently working.");
        }

        if (amount < 0) {
            throw new Exception("Cannot deposit a negative amount.");
        }
    }
}


