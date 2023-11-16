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
    }

    @Test
    void getInterest() {
        // testing the interest to ensure it is as assigned
        Account obj = new Account(100, 0.1);
        assertEquals(0.1, obj.getInterest(), "The getter should return the value of the object as initialized");

        // testing the interest to ensure that when using a setter, the getter also can retrieve the same value
        obj.setInterest(0.5);
        assertEquals(0.5, obj.getInterest(), "The getter should return the value of the setter");
    }

    @Test
    void setInterest() {
        // testing the interest to ensure it is as assigned
        Account obj = new Account(100, 0.1);
        assertEquals(0.1, obj.getInterest(), "The getter should return the value of the object as initialized");

        // testing the interest to ensure that when using a setter, the getter also can retrieve the same value
        obj.setInterest(0.5);
        assertEquals(0.5, obj.getInterest(), "The getter should return the value of the setter");

        // testing error reporting if interest is above 1
        assertThrows(IllegalArgumentException.class, () -> obj.setInterest(1.1), "Setting an interest below 0 should throw an IllegalArgumentException");

        // testing error reporting if interest is below 0
        assertThrows(IllegalArgumentException.class, () -> obj.setInterest(-0.1), "Setting an interest below 0 should throw an IllegalArgumentException");
    }

    @Test
    void addFunds() {
    }

    @Test
    void removeFunds() {
    }

    @Test
    void calculateInterest() {
    }
}