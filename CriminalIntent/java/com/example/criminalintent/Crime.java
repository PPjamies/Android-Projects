package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    //getters
    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public Date getDate() {
        return mDate;
    }
    public boolean isSolved() {
        return mSolved;
    }

    //setters
    public void setTitle(String title) {
        mTitle = title;
    }
    public void setId(UUID id) {
        mId = id;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}