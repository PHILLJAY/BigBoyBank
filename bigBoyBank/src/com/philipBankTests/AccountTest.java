package com.philipBankTests;

import com.philipBank.ATM;
import com.philipBank.Account;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testEquals() {
        Account obj1 = new Account(100,0.1);
        Account obj2 = new Account(100,0.1);
        //Asserting overrides work
        assertEquals(obj1, obj2, "Values are the same");

        //Asserting equals works
        obj2.setMoney(20);
        assertNotEquals(obj1, obj2, "Values are not the same");
    }

    @Test
    void transferMoney() {
        Account obj1 = new Account(100);
        Account obj2 = new Account(100);
        //Testing initial transfer works
        // Testing initial transfer works
        obj1.transferMoney(obj2,50);
        assertEquals(50,obj1.getMoney(), "After transferring $50, obj1 should have $50 left");
        assertEquals(150,obj2.getMoney(), "After receiving $50, obj2 should have $150");

        obj1.setMoney(100);
        obj2.setMoney(100);

        // Testing to ensure negative values are not allowed
        assertThrows(IllegalArgumentException.class,() -> obj1.transferMoney(obj2,-100), "Transferring a negative amount should throw an IllegalArgumentException");
        assertEquals(100,obj1.getMoney(), "obj1 should still have $100 after attempting to transfer a negative amount");
        assertEquals(100,obj2.getMoney(), "obj2 should still have $100 after obj1 attempted to transfer a negative amount");

        obj1.setMoney(100);
        obj2.setMoney(100);

        // Testing to ensure that you cannot send an amount that would put you into negatives
        assertThrows(IllegalArgumentException.class,() -> obj1.transferMoney(obj2,250), "Transferring more money than available should throw an IllegalArgumentException");
        assertEquals(100,obj1.getMoney(), "obj1 should still have $100 after attempting to transfer an excessive amount");
        assertEquals(100,obj2.getMoney(), "obj2 should still have $100 after obj1 attempted to transfer an excessive amount");

        // Testing to ensure you cannot send an amount to yourself
        assertThrows(IllegalArgumentException.class,() -> obj1.transferMoney(obj1,1), "Transferring money to oneself should throw an IllegalArgumentException");
        assertEquals(100,obj1.getMoney(), "obj1 should still have $100 after attempting to transfer money to itself");
        assertEquals(100,obj2.getMoney(), "obj2 should still have $100 as no successful transactions occurred");
    }

    @Test
    void testWithDrawMoney() {
        Account account = new Account(100);
        ATM atm = new ATM(true);

        // Test successful withdrawal
        account.withdrawMoney(atm, 50);
        assertEquals(50, account.getMoney(), "After withdrawing $50, the account should have $50 left");

        // Test withdrawal from a non-working ATM
        ATM brokenAtm = new ATM(false);
        assertThrows(RuntimeException.class, () -> account.withdrawMoney(brokenAtm, 50), "Withdrawing from a non-working ATM should throw a RuntimeException");

        // Test withdrawal of an amount greater than the balance
        assertThrows(IllegalArgumentException.class, () -> account.withdrawMoney(atm, 100), "Withdrawing more money than available should throw an IllegalArgumentException");

        // Test withdrawal of a negative amount
        assertThrows(IllegalArgumentException.class, () -> account.withdrawMoney(atm, -50), "Withdrawing a negative amount should throw an IllegalArgumentException");
    }

    @Test
    void testDepositMoney() {
        Account account = new Account(100);
        ATM atm = new ATM(true);

        // Test successful deposit
        account.depositMoney(atm, 50);
        assertEquals(150, account.getMoney(), "After depositing $50, the account should have $150");

        // Test deposit to a non-working ATM
        ATM brokenAtm = new ATM(false);
        assertThrows(RuntimeException.class, () -> account.depositMoney(brokenAtm, 50), "Depositing to a non-working ATM should throw a RuntimeException");

        // Test deposit of a negative amount
        assertThrows(IllegalArgumentException.class, () -> account.depositMoney(atm, -50), "Depositing a negative amount should throw an IllegalArgumentException");
    }
}

