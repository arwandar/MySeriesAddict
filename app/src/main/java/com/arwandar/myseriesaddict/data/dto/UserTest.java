package com.arwandar.myseriesaddict.data.dto;

/**
 * Created by olivi on 02/04/2016.
 */
public class UserTest {

        private long id;
        private String login;
        private boolean in_account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isIn_account() {
        return in_account;
    }

    public void setIn_account(boolean in_account) {
        this.in_account = in_account;
    }
}
