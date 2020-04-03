package com.oleg4789.hidroponicstore.domain;

import com.oleg4789.hidroponicstore.test.Car;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User myPerson = new User.Builder()
                .withUserId(2).withFirstName("Doe")
                .withPassword("qwerty")
                .withEmail("BadBrad@gmail.com")
                .withRole(Role.ADMIN)// как обозначается обьект енума
                .build();
    }


}
