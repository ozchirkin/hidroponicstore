package com.oleg4789.hidroponicstore.domain;

import java.math.BigDecimal;

public class User extends BaseEntity {
    private String firstName;
    private String secondName;
    private Role role;
    private String login;
    private String password;
    private String email;
    private String telephoneNumber;
    private BigDecimal balance;

    public User() {
        this.balance = new BigDecimal(0);
    }

    /**
     * @return идентификатор пользователя
     */


    @Override
    public String toString() {
        return "User{" +
                "userId=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", balance=" + balance +
                '}';
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

    public void setPassword(String password) {
        this.password = password;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
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
