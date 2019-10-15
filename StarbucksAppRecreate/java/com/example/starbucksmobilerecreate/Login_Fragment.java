package com.example.starbucksmobilerecreate;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Login_Fragment extends Fragment {

    private String mEmail;
    private String mPassword;
    private boolean mAuthenticate;

    private Button mSignInButton;
    private TextView mForgotPW;
    private TextView mJoinNow;


    public Login_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //program sign in button
        mSignInButton = (Button)view.findViewById(R.id.login_button);
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
        mForgotPW = (TextView)view.findViewById(R.id.login_forgotpw_text_view);
        mForgotPW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              Intent intent = new Intent(getActivity(), Forgot_Password_Activity.class);
              getActivity().startActivity(intent);
            }
        });

        //TODO: Program TextView to launch registration fragment

        return view;
    }

}
