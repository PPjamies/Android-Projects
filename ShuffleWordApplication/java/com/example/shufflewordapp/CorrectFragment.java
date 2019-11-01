package com.example.shufflewordapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class CorrectFragment extends Fragment {
    private Button mButton;
    public MainActivity mMainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_correct, container, false);

        mMainActivity = (MainActivity)getActivity();

        // 1. refresh activity widgets
        // 2. remove fragment from screen by popping fragment off of the fragment back stack
        mButton = (Button) view.findViewById(R.id.i_know_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.refresh_Application();
               getFragmentManager().popBackStack();
            }
        });
        return view;
    }

}
