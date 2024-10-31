package com.nextvolunteer.NextVolunteer;

import java.util.ArrayList;
import java.util.List;

public class Opportunity {
    private int opportunityID;
    private String title;
    private String description;
    private String location;
    private String interestArea;
    private int organizationID;

    public Opportunity(int opportunityID, String title, String description, String location, String interestArea, int organizationID) {
        this.opportunityID = opportunityID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.interestArea = interestArea;
        this.organizationID = organizationID;
    }

    // Getters and Setters
    public int getOpportunityID() {
        return opportunityID;
    }

    public void setOpportunityID(int opportunityID) {
        this.opportunityID = opportunityID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterestArea() {
        return interestArea;
    }

    public void setInterestArea(String interestArea) {
        this.interestArea = interestArea;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    // Methods
    public static List<Opportunity> getOppByInterest(String interestArea) {
        List<Opportunity> opportunities = new ArrayList<>();
        // Logic to be added
        return opportunities;
    }

    public static List<Opportunity> getOppByLocation(String location) {
        List<Opportunity> opportunities = new ArrayList<>();
        // Logic to be added
        return opportunities;
    }

    public static List<Opportunity> getOppByOrg(int organizationID) {
        List<Opportunity> opportunities = new ArrayList<>();
        // Logic to be added
        return opportunities;
    }

    public static void bindOpp(List<Opportunity> opportunities) {
        // Logic to be added
    }
}
