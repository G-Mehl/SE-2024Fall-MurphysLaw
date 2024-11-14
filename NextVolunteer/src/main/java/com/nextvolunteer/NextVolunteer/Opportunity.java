package com.nextvolunteer.NextVolunteer;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Opportunity {
    private int opportunityID;
    private String title;
    private String description;
    private String location;
    private String duration;
    private String roleId;
    private String interestArea;
    private int organizationID;

    // Database connection variables
    private static final String URL = "jdbc:mysql://localhost:3306/SE_Project";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public Opportunity(int opportunityID, String title, String description, String location, String interestArea, int organizationID) {
        this.opportunityID = opportunityID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.interestArea = interestArea;
        this.organizationID = organizationID;
    }

    // Getters and Setters
    public int getOpportunityID() { return opportunityID; }
    public void setOpportunityID(int opportunityID) { this.opportunityID = opportunityID; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getInterestArea() { return interestArea; }
    public void setAssociatedInterests(String associatedInterests) { this.interestArea = associatedInterests; }
    public String getRoleId() { return roleId; }
    public void setRoleId(String roleId) { this.roleId = roleId; }
    public int getOrganizationID() { return organizationID; }
    public void setOrganizationID(int organizationID) { this.organizationID = organizationID; }

    // Methods
    public static List<Opportunity> getOppByInterest(String interestArea) {
        List<Opportunity> opportunities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM Opportunities WHERE associated_interests = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, interestArea);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Opportunity opp = new Opportunity(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("descr"),
                        rs.getString("location"),
                        rs.getString("associated_interests"),
                        rs.getInt("organization_id")
                );
                opportunities.add(opp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opportunities;
    }


    public static List<Opportunity> getOppByLocation(String location) {
        List<Opportunity> opportunities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM Opportunities WHERE location = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, location);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Opportunity opp = new Opportunity(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("descr"),
                        rs.getString("location"),
                        rs.getString("associated_interests"),
                        rs.getInt("organization_id")
                );
                opportunities.add(opp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opportunities;
    }

    public static List<Opportunity> getOppByOrg(int organizationID) {
        List<Opportunity> opportunities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM Opportunities WHERE organizationID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, organizationID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Opportunity opp = new Opportunity(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("descr"),
                        rs.getString("location"),
                        rs.getString("associated_interests"),
                        organizationID
                );
                opportunities.add(opp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opportunities;
    }

    public static void bindOpp(List<Opportunity> opportunities) {
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO Opportunities (id, title, descr, location, associated_interests, organization_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Opportunity opp : opportunities) {
                stmt.setInt(1, opp.getOpportunityID());
                stmt.setString(2, opp.getTitle());
                stmt.setString(3, opp.getDescription());
                stmt.setString(4, opp.getLocation());
                stmt.setString(5, opp.getInterestArea());
                stmt.setInt(6, opp.getOpportunityID());
                stmt.addBatch();
            }

            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

