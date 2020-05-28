package com.example.beanleaf;

public class requestObj {

    private int Id;
    private String name;
    private String address;
    private String timestamp;
    private byte[] image;
    private String username;

    public requestObj(int Id, String username, String name, String address, String timestamp, byte[] image) {
        this.Id = Id;
        this.name = name;
        this.address = address;
        this.timestamp = timestamp;
        this.image = image;
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

}
