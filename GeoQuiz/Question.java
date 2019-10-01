package com.example.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mUserAnsweredOnce;

    //constructor
    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mUserAnsweredOnce = false;
    }

    //User answered once
    public boolean isUserAnsweredOnce() {
        return mUserAnsweredOnce;
    }

    public void setUserAnsweredOnce(boolean userAnsweredOnce) {
        mUserAnsweredOnce = userAnsweredOnce;
    }

    //Text Resource Id

    public int getTextResId() {

        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    //Answer True
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
