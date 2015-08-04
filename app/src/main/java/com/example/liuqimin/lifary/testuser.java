package com.example.liuqimin.lifary;

/**
 * Created by liuqi on 2015-08-04.
 */

public class testuser {

    private int id;
    private String username;
    private String password;

    public testuser(){}

    public testuser(int id1, String username1, String password1){
        id = id1;
        username = username1;
        password = password1;
    }

    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password1){
        password = password1;
    }

}

