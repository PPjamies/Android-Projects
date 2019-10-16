package com.example.criminalintent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //grabs xml layout file
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        //grabs the widget view from xml file by id
        //set layout manager to manage the view for recycler view
        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        //create crimelab object
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        //get the list of crimes saved in the crimelab object
        List<Crime> crimes = crimeLab.getCrimes();

        //create a new crime adapter and pass through the list of crimes
        mAdapter = new CrimeAdapter(crimes);
        //connect the recyclerview to the adapter so that recyclerview can ask for new viewholders or swap them out
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    //viewholder deals with the view associated with the list item
    private class CrimeHolder extends RecyclerView.ViewHolder{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }

        //this method will be called everytime a new Crime should be displayed in your crimeholder.
        public void bind(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }
    }

    //Recyclerview adapter deals with the communication between recycler view and the viewholder.
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        //returns when recyclerview needs a new viewholder to display an item with
        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,parent);
        }
        //calls on bind(method) each time the recyclerview requests that a given crimeholder be bount to a particular crime
        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }
        //return the number of crimes
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
