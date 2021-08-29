package com.homework.pizza_app.model.request;

/**
 *
 * @author dritan
 */
public class UserLoginRequestModel {

    //private String email;
    private String userName;
    private String password;

    public UserLoginRequestModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
