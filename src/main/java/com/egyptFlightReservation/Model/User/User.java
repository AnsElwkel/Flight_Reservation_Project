package com.egyptFlightReservation.Model.User;

public abstract class User {
    protected String username;
    protected String name;
    protected String email;
    protected String password;

   public User(String username,String name,String email,String password) {
        this.username=username;
        this.name=name;
        this.email=email;
        this.password=password;
    }
    public String getUsername(){
       return this.username;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString(){
        return username + " " + name + " " + email + " " + password;
    }
}