package com.example.starbucksmobilerecreate;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SignInFragment extends Fragment {

    private String mEmail;
    private String mPassword;
    private boolean mAuthenticate;

    private Button mSignInButton;
    private TextView mForgotPW;
    private TextView mJoinNow;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        //program sign in button
        mSignInButton = (Button)view.findViewById(R.id.signin_button);
        mSignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Send data to database for confirmation

                /*if database approves of user and password(){
                    login in successful, launch new activity
                }
                if database approves of user but not password(){
                    error("wrong password")
                    option to change password arises
                }
                if database cannot find user(){
                    error("no user found")
                }
                */
            }
        });

        //program forgot password textview
        mForgotPW = (TextView)view.findViewById(R.id.forgot_password_text_view);
        mForgotPW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //launch new activity
            }
        });

        //TODO: Program TextView to launch registration fragment
        mJoinNow = (TextView)view.findViewById(R.id.joinnow_text_view);
        mJoinNow.setRotation(90);
        return view;
    }

}
