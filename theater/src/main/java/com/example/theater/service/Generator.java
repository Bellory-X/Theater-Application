package com.example.theater.service;

public class Generator {
    private static int count = 15;

    public static int generateId() {
        count++;
        return count;
    }
}
