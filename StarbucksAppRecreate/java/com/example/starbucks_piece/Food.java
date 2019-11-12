package com.example.starbucks_piece;

import java.util.UUID;

public class Food {
    private String mTitle;
    private int mImgPath;
    private UUID mId;

    public Food(){}

    public Food(String title, int imgPath){
        mTitle = title;
        mImgPath = imgPath;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getImgPath() {
        return mImgPath;
    }

    public void setImgPath(int imgPath) {
        mImgPath = imgPath;
    }
}
