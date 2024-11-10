package com.nextvolunteer.NextVolunteer;

import java.util.*;
import java.sql.*;

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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        // Methods
        public boolean login() {
            try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
                String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, getUsername());
                stmt.setString(2, getPassword());
                ResultSet loginResult = stmt.executeQuery();
                return loginResult.next();
            } catch (SQLException e) {
                System.out.println("Login Failed!");
                e.printStackTrace();
                return false;
            }
        }

        public boolean register() {
            try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
                String checkEmailSQL = "SELECT * FROM Users WHERE email = ?";
                PreparedStatement emailStmt = conn.prepareStatement(checkEmailSQL);
                emailStmt.setString(1, getEmail());
                ResultSet emailResult = emailStmt.executeQuery();

                if (emailResult.next()) {
                    System.out.println("Email already exists, please use another one");
                    return false;
                }

                // Check if username already exists
                String checkUsernameSQL = "SELECT * FROM Users WHERE username = ?";
                PreparedStatement usernameStmt = conn.prepareStatement(checkUsernameSQL);
                usernameStmt.setString(1, getUsername());
                ResultSet usernameResult = usernameStmt.executeQuery();

                if (usernameResult.next()) {
                    System.out.println("Username already exists, please enter a different one");
                    return false;
                }

                // Insert new user
                String sql = "INSERT INTO Users (id, username, password, email, full_name) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(sql);
                insertStmt.setInt(1, getUserID());
                insertStmt.setString(2, getUsername());
                insertStmt.setString(3, getPassword());
                insertStmt.setString(4, getEmail());
                insertStmt.setString(5, getFullName());
                insertStmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Registration failed!");
                e.printStackTrace();
                return false;
            }
        }

        public void updateProfile() {
            try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
                String updateSql = "UPDATE Users SET username = ?, password = ?, email = ?, full_name = ?, interests = ? WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, getUsername());
                updateStmt.setString(2, getPassword());
                updateStmt.setString(3, getEmail());
                updateStmt.setString(4, getFullName());
                updateStmt.setString(5, String.join(",", getSavedInterests()));
                updateStmt.setInt(6, getUserID());

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
                            rs.getString("associated_interests"),
                            rs.getInt("organization_id")
                    );
                    opportunity.setDuration(rs.getString("duration"));
                    recommendedOpportunities.add(opportunity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return recommendedOpportunities;
        }

    }
