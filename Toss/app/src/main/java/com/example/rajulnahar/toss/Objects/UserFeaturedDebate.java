package com.example.rajulnahar.toss.Objects;

/**
 * Created by Rajul Nahar on 07-02-2017.
 */

public class UserFeaturedDebate {

    private int userId;
    private int debateFactsId;
    private String debateVote;
    private boolean isHeadViewed;
    private boolean isTailViewed;
    private boolean isDebateFavourite;

    public int getUserId() {
        return userId;
    }

    public int getDebateFactsId() {
        return debateFactsId;
    }

    public String getDebateVote() {
        return debateVote;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDebateFactsId(int debateFactsId) {
        this.debateFactsId = debateFactsId;
    }

    public void setDebateFavourite(boolean debateFavourite) {
        isDebateFavourite = debateFavourite;
    }

    public void setDebateVote(String debateVote) {
        this.debateVote = debateVote;
    }

    public void setHeadViewed(boolean headViewed) {
        isHeadViewed = headViewed;
    }

    public void setTailViewed(boolean tailViewed) {
        isTailViewed = tailViewed;
    }

    public boolean isDebateFavourite() {
        return isDebateFavourite;
    }

    public boolean isHeadViewed() {
        return isHeadViewed;
    }

    public boolean isTailViewed() {
        return isTailViewed;
    }
}
