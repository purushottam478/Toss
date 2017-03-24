package com.example.rajulnahar.toss.Objects;

/**
 * Created by Rajul Nahar on 04-02-2017.
 */

public class DebateFact {
    private int factId;
    private String factGuid="";
    private String claimGuid="";
    private String factHeading="";
    private String factBody="";
    private Integer factViewCount=0;
    private byte[] factImage;
    private boolean displayMoodOfHouse=false;
    private Integer headVoteCount=0;
    private Integer tailsVoteCount=0;

    public byte[] getFactImage() {
        return factImage;
    }

    public int getFactId() {
        return factId;
    }

    public Integer getFactViewCount() {
        return factViewCount;
    }

    public Integer getHeadVoteCount() {
        return headVoteCount;
    }

    public Integer getTailsVoteCount() {
        return tailsVoteCount;
    }

    public String getClaimGuid() {
        return claimGuid;
    }

    public String getFactBody() {
        return factBody;
    }

    public String getFactGuid() {
        return factGuid;
    }

    public String getFactHeading() {
        return factHeading;
    }

    public void setClaimGuid(String claimGuid) {
        this.claimGuid = claimGuid;
    }

    public void setDisplayMoodOfHouse(boolean displayMoodOfHouse) {
        this.displayMoodOfHouse = displayMoodOfHouse;
    }

    public void setFactBody(String factBody) {
        this.factBody = factBody;
    }

    public void setFactGuid(String factGuid) {
        this.factGuid = factGuid;
    }

    public void setFactHeading(String factHeading) {
        this.factHeading = factHeading;
    }

    public void setFactId(int factId) {
        this.factId = factId;
    }

    public void setFactImage(byte[] factImage) {
        this.factImage = factImage;
    }

    public void setFactViewCount(Integer factViewCount) {
        this.factViewCount = factViewCount;
    }

    public void setHeadVoteCount(Integer headVoteCount) {
        this.headVoteCount = headVoteCount;
    }

    public void setTailsVoteCount(Integer tailsVoteCount) {
        this.tailsVoteCount = tailsVoteCount;
    }

    public boolean isDisplayMoodOfHouse() {
        return displayMoodOfHouse;
    }
}
