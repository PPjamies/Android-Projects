package com.example.starbucks_piece;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GiftFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //widgets
    private TextView mStarBalance_TextView;
    private EditText mEmail_EditText;
    private Button mSendButton;
    private Spinner mSpinner;

    //data variables
    private String mFriendEmail; //grab email from edit text
    private String mSendAmount; //grab staramount from spinner
    private String mCurrentBalance; //keeps track of user's current balance

    //constructors
    public GiftFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gift, container, false);

        //Initialize Widgets
        mStarBalance_TextView = view.findViewById(R.id.gift_star_count);
        mEmail_EditText = view.findViewById(R.id.gift_email_edit_text);
        mSendButton = view.findViewById(R.id.gift_send_button);
        mSpinner = view.findViewById(R.id.gift_amount_spinner);

        //initialize current balance
        //TODO: this will be retrieved from the database or datastructure
        mCurrentBalance = "600";

        //setup spinner widget
        // create an ArrayAdapter using the string array and a default mSpinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.gift_amount_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the mSpinner
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        //program buttons
        //upon click grab the email from the edit text view
        //grab item selected from spinner
        //send currentBalance, sendAmount, and FriendEmail to server
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFriendEmail = mEmail_EditText.getText().toString();

                //user can only send amount if amount is specified
                if(!mSendAmount.equals("")) {
                    String message = "$$" + mFriendEmail + "$$" + mCurrentBalance + "$$" + mSendAmount;
                    //MainActivity.sendMessageToServer(message);
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(position)).setTextColor(getResources().getColor(R.color.white));

        //set the send amount to what is selected
        mSendAmount = ((TextView)parent.getChildAt(position)).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }


    //method that sets star balance text view
    //
    public void setStarBalance_TextView(String balance){
        mStarBalance_TextView.setText(balance);
    }

    //resets the widgets in gift fragment once button has been sent
    //reset email edit text
    //reset position of spinner
    public void refresh_GiftFragment(){
        mEmail_EditText.setText("");
        mEmail_EditText.setHint("email");
        mSpinner.setSelection(0);
    }

}