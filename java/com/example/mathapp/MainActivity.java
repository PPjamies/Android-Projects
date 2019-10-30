package com.example.mathapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

/********************************************************************************************************************************************/
    //programmable widgets
    private Button mPlusButton;
    private Button mMinusButton;
    private Button mTimesButton;
    private Button mDivideButton;
    private Button mAnswerButton;
    private TextView mQuestion_TxtView;
    private EditText mAnswer_EditTxt;

    //saves user's answer and question
    private String mAnswer;
    private String mQuestion;

    //indicates answer is true or false
    private Boolean mAnswerIsCorrect;

    //Fragment
    Fragment mFragment;

    // connection to server
    public static Socket socket;
    public String host = "44.224.10.250";
    public final int port =3377;
    public static BufferedReader in= null;
    public static PrintWriter out= null;

    final Context context = this;
    String alertMessage="";
/******************************************MISC METHODS************************************************************************************/

    //Reset Edit Text Bar if user would like to refresh
    //Reset Text View if user would like to refresh
    //This information is collected once the user's answer is proven correct and they hit the "I Know" Button from Correct_Fragment
    public void refresh_Application(){
        //clears user's edit text input
        mAnswer_EditTxt.setText("");
        //set hint back to answer_hint
        mAnswer_EditTxt.setHint(R.string.answer_hint);
        //reset text view back to placeholder
        mQuestion_TxtView.setText(R.string.placeholder);
    }


    //initiate correct popup fragment
    public void popup_Fragment(Boolean answerIsCorrect){
        if(mAnswerIsCorrect == answerIsCorrect) {
            mFragment = new CorrectFragment();
        }else{
            mFragment = new IncorrectFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mFragment).addToBackStack(null);
        transaction.commit();
    }

/********************************************************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        communicate();

        //initialize widgets
        mPlusButton = (Button) findViewById(R.id.plus_button);
        mMinusButton = (Button) findViewById(R.id.minus_button);
        mTimesButton = (Button) findViewById(R.id.times_button);
        mDivideButton = (Button) findViewById(R.id.divide_button);
        mAnswerButton = (Button) findViewById(R.id.answer_button);
        mQuestion_TxtView = (TextView) findViewById(R.id.question_txt_view);
        mAnswer_EditTxt = (EditText) findViewById(R.id.answer_edit_txt);


        //program buttons
            mPlusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendMessageToServer("+");
                }
            });

            mMinusButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    sendMessageToServer("-");
                }
            });

            mTimesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendMessageToServer("*");
                }
            });

            mDivideButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendMessageToServer("/");
                }
            });

         //Program Question Edit Text
        mAnswer_EditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {}
        });


        mAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //append token to user's answer and send it to the server
                mAnswer = "=" + mAnswer_EditTxt.getText().toString();
                sendMessageToServer(mAnswer);
            }
        });

    }

    //send message to server
    public static void sendMessageToServer(final String str) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
                    if (!str.isEmpty()){
                        out.println(str);
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

/**********************************MOBILE TO/FRO SERVER METHODS********************************************************************************************/

    //communicate with server.
    //Create socket or open socket to server
    //on read mode for communication from server
    public void communicate() {

        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    socket = new Socket(host, port);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    Log.d("", "unknown host*");
                } catch (IOException e) {
                    Log.d("", "io exception*");
                    e.printStackTrace();
                }

                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    String msg = null;
                    try {
                        msg = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (msg == null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (msg.equals("#0")) {
                        mAnswerIsCorrect = true; //user answered the question correctly
                        popup_Fragment(true);

                    } else if (msg.equals("#1")) {
                        mAnswerIsCorrect = false;
                        popup_Fragment(true);

                    } else if (msg.startsWith("?")) {
                        mQuestion = msg.substring(1);
                        mQuestion_TxtView.setText(mQuestion); //display question to text view
                    }
                }  // end while

            }  // end run

        }).start();
    }
}

/**********************************************************************************************************************************************/