package com.example.theater.service;

public class Generator {
    private static int count = 155;

    public static int generateId() {
        count++;
        return count;
    }
}
