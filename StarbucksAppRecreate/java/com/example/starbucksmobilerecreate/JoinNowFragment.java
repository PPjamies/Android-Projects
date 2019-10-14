package com.example.starbucksmobilerecreate;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class JoinNowFragment extends Fragment {
    private Button mSignUpButton;

    private EditText mFname_EditText;
    private EditText mLname_EditText;
    private EditText mEmail_EditText;
    private EditText mPassword_EditText;
    private CheckBox mCheckBox_Terms;
    private CheckBox mCheckBox_Emails;
    private TextView mTermOfService_TextView;

    private String mFname;
    private String mLname;
    private String mEmail;
    private String mPassword;
    private boolean mAgreedToTerms;
    private boolean mCanSendEmailNotifications;

    private int[] mNullChecks = {0,0,0,0};
    private EditText[] mEditText_Members = {mFname_EditText, mLname_EditText, mEmail_EditText, mPassword_EditText};
    private boolean mNullCheckPassed;


    public JoinNowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_joinnow, container, false);

       //initialize widgets in fragment
        mSignUpButton = (Button)view.findViewById(R.id.joinnow_button);
        mFname_EditText = (EditText)view.findViewById(R.id.joinnow_first_name_edit_text);
        mLname_EditText = (EditText)view.findViewById(R.id.joinnow_last_name_edit_text);
        mEmail_EditText = (EditText)view.findViewById(R.id.joinnow_email_edit_text);
        mPassword_EditText = (EditText)view.findViewById(R.id.joinnow_password_edit_text);
        mCheckBox_Terms = (CheckBox)view.findViewById(R.id.joinnow_checkbox1);
        mCheckBox_Emails = (CheckBox)view.findViewById(R.id.joinnow_checkbox2);

        //program sign up button to collect information from all the text fields
        mSignUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check to see if edit text is empty
                //sets mNullCheckPassed boolean variable to true if no nulls are found
                discoverNulls();

                //check with database to see if email is already used
                //sets mNotAnExistingUser boolean variable to true if email is not in use
                //discoverExistingUsers();

                if(mNullCheckPassed /*&& mNotAnExistingUser*/){
                    //store data from edit_text fields
                    mFname = mFname_EditText.getText().toString();
                    mLname = mLname_EditText.getText().toString();
                    mEmail = mEmail_EditText.getText().toString();
                    mPassword = mPassword_EditText.getText().toString();

                    //check checkboxes:
                    //terms of agreement must be checked for user to register
                    if(mCheckBox_Terms.isChecked()){
                        //starbucks notification emails is a preference; optional feature
                        if(mCheckBox_Emails.isChecked()){
                            mCanSendEmailNotifications = true;
                        }
                        mCanSendEmailNotifications = false;

                        //Send data to database


                        //throw success toast
                    }else{

                    }
                }else{
                    if(!mNullCheckPassed) {
                        //throw edit_text null errors
                        showNullErrors();
                    }else {
                        //throw existing user error
                    }

                }
            }
        });
        //program
        return view;
    }

    //discover if any of the edit_text fields are null
    //set the nullCheck to false if nulls are found
    private void discoverNulls(){
        if(edText_isEmpty(mFname_EditText)){
            mNullChecks[0] = 1;
            mNullCheckPassed = false;
        }
        if(edText_isEmpty(mLname_EditText)){
            mNullChecks[1] = 1;
            mNullCheckPassed = false;
        }
        if(edText_isEmpty(mEmail_EditText)){
            mNullChecks[2] = 1;
            mNullCheckPassed = false;
        }
        if(edText_isEmpty(mPassword_EditText)){
            mNullChecks[3] = 1;
            mNullCheckPassed = false;
        }
    }

    //Check if edit text is empty
    private boolean edText_isEmpty(EditText edText) {
        String result = edText.getText().toString();
        return TextUtils.isEmpty(result);
    }

    //throw appropriate erros
    private void showNullErrors(){
        for(int i=0; i<mNullChecks.length;i++){
            if(mNullChecks[i] == 1){
                mEditText_Members[i].setError("This field cannot be left blank");
            }
        }
    }
}
