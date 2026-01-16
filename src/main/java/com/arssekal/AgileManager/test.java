package com.arssekal.AgileManager;

import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.getRole());
    }
}
