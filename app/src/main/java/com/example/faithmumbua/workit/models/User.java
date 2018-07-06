package com.example.faithmumbua.workit.models;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String age;
    private String gender;
    private String prefered_location;
    private String weight;
    private String target_weight;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String prefered_location() {
        return prefered_location;
    }

    public void prefered_location(String prefered_location) {
        this.prefered_location = prefered_location;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(String target_weight) {
        this.target_weight = target_weight;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
