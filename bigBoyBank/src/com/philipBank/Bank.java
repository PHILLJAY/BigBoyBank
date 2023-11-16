package com.philipBank;

import java.util.Random;

/**
 *
 */
public class Bank {
    private int ID;
    public Bank() {
        Random rand= new Random();
        ID = rand.nextInt(0,100);
    }
    @Override
    public String toString() {
        // TODO implement this lol
        return super.toString();
    }

    public int getID() {
        return ID;
    }

    public void addUser(String philip, String jasionowski, int i) {
    }
}
