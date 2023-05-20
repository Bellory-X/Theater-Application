package com.example.theater.service;

public class Generator {
    private static int count = 55;

    public static int generateId() {
        count++;
        return count;
    }
}
