package com.nextvolunteer.NextVolunteer;

import java.util.*;

public class User {
    // Variables
    private int userID;
    private String username;
    private String password;
    private String email;
    private List<String> savedInterests;
    private String location;
        // Constructor
        public User(int userID, String username, String password, String email, List<String> savedInterests, String location) {
            this.userID = userID;
            this.username = username;
            this.password = password;
            this.email = email;
            this.savedInterests = savedInterests;
            this.location = location;
        }

        // Getters and Setters
        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public void setEmail(String email) {
            this.email = email;
        }

        public List<String> getSavedInterests() {
            return savedInterests;
        }

        public void setSavedInterests(List<String> savedInterests) {
            this.savedInterests = savedInterests;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        // Methods
        public boolean login() {
            // Logic to be added
            return true;
        }

        public boolean register() {
            // Logic to be added
            return true;
        }

        public void updateProfile() {
            // Logic to be added
        }

        public List<Opportunity> getRecommendedOpp() {
            // Logic to be added
            return null;
        }
    }
