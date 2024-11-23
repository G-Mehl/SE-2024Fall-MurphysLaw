package com.nextvolunteer.NextVolunteer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Organization {
    // Variables
    private int organizationID;
    private String name;
    private String contactInfo;


    // Database connection variables
    private static final String URL = "jdbc:mysql://localhost:3306/SE_Project";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    //Constructor
    public Organization(int organizationID, String name, String contactInfo) {
        this.organizationID = organizationID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Methods
    public void addOpp(Opportunity opportunity) {
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO Opportunities (title, descr, location, duration, associated_interests, organization_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, opportunity.getTitle());
            stmt.setString(2, opportunity.getDescription());
            stmt.setString(3, opportunity.getLocation());
            stmt.setString(4, opportunity.getDuration());
            stmt.setString(5, opportunity.getInterestArea());

            stmt.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrgDetails(String newName, String newContactInfo) {
        this.name = newName;
        this.contactInfo = newContactInfo;

        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
            String updateOrgSql = "UPDATE Oppurtunities SET name = ?, contactInfo = ? WHERE organization_id = ?";
            PreparedStatement stmt = conn.prepareStatement(updateOrgSql);
            stmt.setString(1, newName);
            stmt.setString(2, newContactInfo);
            stmt.setInt(3, getOrganizationID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Opportunity> getOpportunities() {
        List<Opportunity> opportunities = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
            String getOppsSql = "SELECT * FROM Opportunities WHERE organizationID = ?";
            PreparedStatement stmt = conn.prepareStatement(getOppsSql);
            stmt.setInt(1, getOrganizationID());

            ResultSet rs = stmt.executeQuery();

            // Process each row in the ResultSet
            while (rs.next()) {
                Opportunity opportunity = new Opportunity(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("descr"),
                        rs.getString("location"),
                        rs.getString("associated_interests")
                );
                opportunity.setDuration(rs.getString("duration")); //Retrieving duration from DB
                opportunities.add(opportunity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return opportunities;
    }
}

