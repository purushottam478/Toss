package com.example.rajulnahar.toss.Objects;

/**
 * Created by Rajul Nahar on 04-02-2017.
 */

public class DebateClaim {
    private int debateClaimsId;
    private String debateClaimsGuid = "";
    private int debateFactsId = -1;
    private String headsText = "";
    private int headsViewCount = 0;
    private int headsAuthorId = -1;
    private String tailsText = "";
    private int tailsViewCount = 0;
    private int tailsAuthorId = -1;

    public int getDebateClaimsId() {
        return debateClaimsId;
    }

    public int getDebateFactsId() {
        return debateFactsId;
    }

    public int getHeadsAuthorId() {
        return headsAuthorId;
    }

    public int getHeadsViewCount() {
        return headsViewCount;
    }

    public int getTailsAuthorId() {
        return tailsAuthorId;
    }

    public int getTailsViewCount() {
        return tailsViewCount;
    }

    public String getDebateClaimsGuid() {
        return debateClaimsGuid;
    }

    public String getHeadsText() {
        return headsText;
    }

    public String getTailsText() {
        return tailsText;
    }

    public void setDebateClaimsGuid(String debateClaimsGuid) {
        this.debateClaimsGuid = debateClaimsGuid;
    }

    public void setDebateClaimsId(int debateClaimsId) {
        this.debateClaimsId = debateClaimsId;
    }

    public void setDebateFactsId(int debateFactsId) {
        this.debateFactsId = debateFactsId;
    }

    public void setHeadsAuthorId(int headsAuthorId) {
        this.headsAuthorId = headsAuthorId;
    }

    public void setHeadsText(String headsText) {
        this.headsText = headsText;
    }

    public void setHeadsViewCount(int headsViewCount) {
        this.headsViewCount = headsViewCount;
    }

    public void setTailsAuthorId(int tailsAuthorId) {
        this.tailsAuthorId = tailsAuthorId;
    }

    public void setTailsText(String tailsText) {
        this.tailsText = tailsText;
    }

    public void setTailsViewCount(int tailsViewCount) {
        this.tailsViewCount = tailsViewCount;
    }
}
