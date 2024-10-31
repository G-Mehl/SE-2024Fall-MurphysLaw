package com.nextvolunteer.NextVolunteer;

import java.util.List;

public class Organization {
    // Variables
    private int organizationID;
    private String name;
    private String contactInfo;

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
        // Logic to be added
    }

    public void updateOrgDetails(String newName, String newContactInfo) {
        this.name = newName;
        this.contactInfo = newContactInfo;
        // Logic to be added

    }

    public List<Opportunity> getOpportunities() {
        // Logic to be added

        return null; // Replace with logic
    }
}

