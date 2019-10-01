package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity"; //for creating log messages
    private static final String KEY_INDEX = "index";
    private static final String SCORE_INDEX = "score";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;
    private int mScoreIndex = 0;

    private Question[] mQuestions = new Question[]{
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };

    //updating the view
    private void updateQuestion(){
        int question = mQuestions[mCurrentIndex].getTextResId(); //grab the first question (in array) and find its ID
        mQuestionTextView.setText(question); //update the view with the question
    }

    //disable buttons
    private void disableButtons(){
        mTrueButton.setEnabled(false);//disable button
        mFalseButton.setEnabled(false);
    }

    //enable buttons
    private void enableButtons(){
        mTrueButton.setEnabled(true);
        mFalseButton.setEnabled(true);
    }

    //check answers
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) { //check the answers
            messageResId = R.string.correct_toast;
            mScoreIndex++;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        //show the correct toast
        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
        toast.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called"); //create log message
        setContentView(R.layout.activity_main); //grabs xml layout for page

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view); //grab question text view

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean question_AnsweredOnce = mQuestions[mCurrentIndex].isUserAnsweredOnce();
                if(question_AnsweredOnce == false) { //user is answering this question for the first time
                    enableButtons();
                    checkAnswer(true); //check the answer, update the score, and update the toast
                    mQuestions[mCurrentIndex].setUserAnsweredOnce(true); //user has now answered once
                }else{
                    disableButtons();
                    Toast.makeText(MainActivity.this,R.string.answered_question,Toast.LENGTH_SHORT).show();
                }
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean question_AnsweredOnce = mQuestions[mCurrentIndex].isUserAnsweredOnce();
                if(question_AnsweredOnce == false) { //user is answering this question for the first time
                    enableButtons();
                    checkAnswer(false); //check the answer, update the score, and update the toast
                    mQuestions[mCurrentIndex].setUserAnsweredOnce(true); //user has now answered once
                }else{
                    disableButtons();
                    Toast.makeText(MainActivity.this,R.string.answered_question,Toast.LENGTH_SHORT).show();
                }
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                enableButtons();
                mCurrentIndex = (mCurrentIndex+1)% mQuestions.length;
                updateQuestion();
            }
        });

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                enableButtons();
                mCurrentIndex = (mCurrentIndex-1)%mQuestions.length;
                updateQuestion();
            }
        });

        updateQuestion();

        //checks if there was a previous saved instance state, if not relaunch a clean activity
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
    }

    @Override
    public void onStart(){
        super.onStart(); //grabs super class's onStart()
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override //overriding the instance state to be the current index
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState"); //creating a log
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex); //saving additional information
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
