package com.bezkoder.spring.jwt.mongodb.models;

public class CustomUser extends User{
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomUser(String id) {
        this.id = id;
    }

    public CustomUser(String username, String email, String password, String id) {
        super(username, email, password);
        this.id = id;
        setId(id);
    }
    
    
}
