package com.example.vibechecker;

import java.util.Date;
import java.util.UUID;

public class VibeCheck {

    private int mScore;
    public UUID mId;
    private Date mDate;

    public VibeCheck(int mScore) {
        mId = UUID.randomUUID();
        mDate = new Date();
        this.mScore = mScore;
    }

    public int getScore() {
        return mScore;
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }
}
