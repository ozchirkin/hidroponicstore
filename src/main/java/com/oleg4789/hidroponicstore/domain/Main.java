package com.oleg4789.hidroponicstore.domain;

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
