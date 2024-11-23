package com.nextvolunteer.NextVolunteer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    // Variables
    private int userID;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private List<String> savedInterests;
    private String location;

    // Database Connection Variables
    private static final String URL = "jdbc:mysql://localhost:3306/SE_Project";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

        // Constructor
        public User(int userID, String username, String password, String email, List<String> savedInterests, String location) {
            this.userID = userID;
            this.username = username;
            setPassword(password);
            this.email = email;
            this.savedInterests = savedInterests;
            this.location = location;
        }

        // Constructor for login
        public User(String username, String password) {
            this.username = username;
            setPassword(password);
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
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        // Methods
        public boolean checkPassword(String plainPassword, String hashedPassword) {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        }

        public boolean login(String enteredPassword) {
            try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
                String sql = "SELECT pass FROM Users WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, getUsername());
                ResultSet loginResult = stmt.executeQuery();

                if (loginResult.next()) {
                    String hashedPassword = loginResult.getString("pass");
                    return checkPassword(enteredPassword, hashedPassword);
                }
                return false;
            } catch (SQLException e) {
                System.out.println("Login Failed!");
                e.printStackTrace();
                return false;
            }
        }

        public boolean register() {
            try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
                // Check email
                String checkEmailSQL = "SELECT * FROM Users WHERE email = ?";
                try (PreparedStatement emailStmt = conn.prepareStatement(checkEmailSQL)) {
                    emailStmt.setString(1, getEmail());
                    if (emailStmt.executeQuery().next()) {
                        System.out.println("Email already exists, please use another one.");
                        return false;
                    }
                }
            
                // Check username
                String checkUsernameSQL = "SELECT * FROM Users WHERE username = ?";
                try (PreparedStatement usernameStmt = conn.prepareStatement(checkUsernameSQL)) {
                    usernameStmt.setString(1, getUsername());
                    if (usernameStmt.executeQuery().next()) {
                        System.out.println("Username already exists, please enter a different one.");
                        return false;
                    }
                }
            
                // Insert new user
                String sql = "INSERT INTO Users (username, pass, email, full_name) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(sql)) {
                    insertStmt.setString(1, getUsername());
                    insertStmt.setString(2, this.password); // Using hashed password
                    insertStmt.setString(3, getEmail());
                    insertStmt.setString(4, getFullName());
                    insertStmt.executeUpdate();
                }

                return true;
            } catch (SQLException e) {
                System.out.println("Registration failed!");
                e.printStackTrace();
                return false;
            }
        }

        public void updateProfile(String newPassword) {
            try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
                String updateSql = "UPDATE Users SET username = ?, pass = ?, email = ?, full_name = ?, interests = ? WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, getUsername());
    
                // Update password only if a new one is provided
                if (newPassword != null && !newPassword.isEmpty()) {
                    updateStmt.setString(2, BCrypt.hashpw(newPassword, BCrypt.gensalt())); // Hash new password
                } else {
                    updateStmt.setString(2, this.password); // Keep existing password
                }
            
                updateStmt.setString(3, getEmail());
                updateStmt.setString(4, String.join(",", getSavedInterests()));
                updateStmt.setString(5, getUsername()); //update based on username
            
                updateStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Opportunity> getRecommendedOpp() {
            List<Opportunity> recommendedOpportunities = new ArrayList<>();

            try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
                // SQL query to find matching opportunities by interest or location
                String sql = "SELECT * FROM Opportunities WHERE associated_interests IN (?) OR location = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                String interests = String.join(",", getSavedInterests());
                stmt.setString(1, interests);
                stmt.setString(2, getLocation());

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Opportunity opportunity = new Opportunity(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("descr"),
                            rs.getString("location"),
                            rs.getString("associated_interests")
                    );
                    opportunity.setDuration(rs.getString("duration"));
                    recommendedOpportunities.add(opportunity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return recommendedOpportunities;
        }

        // Method to determine if the password needs to be updated
        private boolean passwordNeedsUpdate() {
            return true; // Placeholder
        }

    }
