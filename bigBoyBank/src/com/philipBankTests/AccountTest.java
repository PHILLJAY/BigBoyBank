package com.philipBankTests;

import com.philipBank.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getMoney() {
        Account obj = new Account(100, 0.1);
        assertEquals(100, obj.getMoney(), "The getter should return the value of the object as initialized");
    }

    @Test
    void setMoney() {
        Account obj = new Account(100, 0.1);
        obj.setMoney(200);
        assertEquals(200, obj.getMoney(), "The setter should set the value at 200");
        //
        obj.setMoney(-100);
        assertEquals(-100, obj.getMoney(), "The account can reach negatives");
    }

    @Test
    void getInterest() {
        // testing the interest to ensure it is as assigned
        Account obj = new Account(100, 0.1);
        assertEquals(0.1, obj.getInterestRate(), "The getter should return the value of the object as initialized");

        // testing the interest to ensure that when using a setter, the getter also can retrieve the same value
        obj.setInterestRate(0.5);
        assertEquals(0.5, obj.getInterestRate(), "The getter should return the value of the setter");
    }

    @Test
    void setInterest() {
        // testing the interest to ensure it is as assigned
        Account obj = new Account(100, 0.1);
        assertEquals(0.1, obj.getInterestRate(), "The getter should return the value of the object as initialized");

        // testing the interest to ensure that when using a setter, the getter also can retrieve the same value
        obj.setInterestRate(0.5);
        assertEquals(0.5, obj.getInterestRate(), "The getter should return the value of the setter");

        // testing error reporting if interest is above 1
        assertThrows(IllegalArgumentException.class, () -> obj.setInterestRate(1.1), "Setting an interest below 0 should throw an IllegalArgumentException");

        // testing error reporting if interest is below 0
        assertThrows(IllegalArgumentException.class, () -> obj.setInterestRate(-0.1), "Setting an interest below 0 should throw an IllegalArgumentException");
    }

    @Test
    void addFunds() {
        //test adding funds
        Account obj = new Account(100,0.1);
        obj.addFunds(100);
        assertEquals(200,obj.getMoney());
        //ensure funds cant be negative
        assertThrows(IllegalArgumentException.class, () -> obj.addFunds(-100), "Can not add negative funds");

    }

    @Test
    void removeFunds() {
        //test removing funds
        Account obj = new Account(100,0.1);
        obj.removeFunds(50);
        assertEquals(50,obj.getMoney());
        //ensure funds cant be negative
        assertThrows(IllegalArgumentException.class, () -> obj.removeFunds(-100), "Can not remove negative funds");
    }

    @Test
    void calculateInterest() {
        //Calculating annual interest for 1 year
        Account obj = new Account(100, 0.1);
        assertEquals(110,
                obj.calculateInterest("annual", 1),
                "Annual Interest calculated successfully");

        //Calculating annual interest for 5 years
        assertEquals(161.05,
                obj.calculateInterest("annual", 5),
                "Annual Interest calculated successfully for 5 years");

        //Calculating monthly interest
        obj.setInterestRate(0.12);
        assertEquals(101,
                obj.calculateInterest("monthly",1),
                "Monthly interest calculated successfully");

        //Calculating monthly interest for 5 months
        assertEquals(105.10,
                obj.calculateInterest("monthly",5),
                "Monthly interest calculated successfully for 5 months");

        //Calculating weekly interest
        obj.setInterestRate(0.52);
        assertEquals(101,
                obj.calculateInterest("weekly",1),
                "weekly interest calculated successfully");

        //Calculating weekly interest for 5 weeks
        assertEquals(105.10,
                obj.calculateInterest("weekly",5),
                "weekly interest calculated successfully for 5 weeks");

        //Calculating daily interest
        obj.setInterestRate(0.365);
        assertEquals(100.10,
                obj.calculateInterest("daily",1),
                "Daily interest calculated successfully");

        //Calculating daily interest rate for 5 days
        assertEquals(100.50,
                obj.calculateInterest("daily",5),
                "Daily interest calculated successfully for 5 days");
        //Checking for illegal arguments
        assertThrows(IllegalArgumentException.class, () ->obj.calculateInterest("FART", 4), "Throws illegal argument exception when the wrong stirng is passed");

    }
}