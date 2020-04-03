package com.oleg4789.hidroponicstore.domain;

import java.math.BigDecimal;

public class User {
    private int userId;
    private String firstName;
    private String secondName;
    private Role role;
    private String login;
    private String password;
    private String email;
    private String telephoneNumber;
    private BigDecimal balance;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pasword) {
        this.password = pasword;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder {
        private User newUser;

        public Builder() {
            System.out.println("this is constructor");
            newUser = new User(); // чем отличается это от записи : User newUser = new User();
        }

        public Builder withUserId(int userId) {
            newUser.userId = userId;
            return this;// return this это тоже самое что и return userId ? или что тут вообше возврашается
        }

        public Builder withFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public Builder withSecondName(String secondName) {
            newUser.secondName = secondName;
            return this;
        }

        public Builder withRole(Role role) {
            newUser.role = role;
            return this;
        }

        public Builder withLogin(String login) {
            newUser.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder withTelephoneNumber(String telephoneNumber) {
            newUser.telephoneNumber = telephoneNumber;
            return this;
        }

        public Builder withBalance(BigDecimal balance) {
            newUser.balance = balance;
            return this;
        }

        public User build() {
            return newUser;
        }


    }


}