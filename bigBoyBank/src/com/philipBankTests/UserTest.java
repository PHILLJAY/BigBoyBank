package com.philipBankTests;

import com.philipBank.Account;
import com.philipBank.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testUser() {
        User user = new User("John", "Doe");

        // Test firstName and lastName
        assertEquals("John", user.getFirstName(), "The first name should be 'John'");
        assertEquals("Doe", user.getLastName(), "The last name should be 'Doe'");

        // Test dateOfCreation
        assertNotNull(user.getDateOfCreation(), "The date of creation should not be null");

        // Test numOfAccounts
        assertEquals(0, user.getNumOfAccounts(), "The number of accounts should be 0");

        // Test addAccount
        Account account = new Account(100);
        user.addAccount(account);
        assertEquals(1, user.getNumOfAccounts(), "The number of accounts should be 1 after adding an account");
    }
}