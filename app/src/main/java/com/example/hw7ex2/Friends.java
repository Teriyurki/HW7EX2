package com.example.hw7ex2;

public class Friends {

    private int id;
    private String fname;
    private String lname;
    private String email;

    public Friends (int newId, String newFname, String newLname, String newEmail){
        setID(newId);
        setFname(newFname);
        setLname(newLname);
        setEmail(newEmail);
    }


    private void setEmail(String newEmail) {
        email = newEmail;
    }

    private void setLname(String newLname) {
        lname = newLname;
    }

    private void setFname(String newFname) {
        fname = newFname;
    }

    private void setID(int newId) {
        id = newId;
    }
    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {

        return id + ":" + fname + ": " + lname + ": " + email;
    }
}
