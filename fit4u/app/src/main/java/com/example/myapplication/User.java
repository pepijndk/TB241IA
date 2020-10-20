package com.example.myapplication;

public class User {
    private static int userId;

    public static int getId() {
        if (userId == 0) {
            return 1000;
        }
        else return userId;
    }
    public static void setId(int id) {
        userId = id;
    }




}
