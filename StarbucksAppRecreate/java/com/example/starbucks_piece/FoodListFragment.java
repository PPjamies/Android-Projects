package com.example.starbucks_piece;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.starbucks_piece.CircularImageView;

public class FoodListFragment extends Fragment {
    private RecyclerView mFoodRecyclerView;
    private FoodAdapter mFoodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        mFoodRecyclerView = (RecyclerView)view.findViewById(R.id.food_recycler_view);
        mFoodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

///////Update UI
    private void updateUI(){
        FoodLab foodLab = FoodLab.get(getActivity());
        List<Food> foods = foodLab.getFoods();

        mFoodAdapter = new FoodAdapter(foods);
        mFoodRecyclerView.setAdapter(mFoodAdapter);
    }
///////Recyclerview adapter deals with the communication between recycler view and the viewholder.
    private class FoodAdapter extends RecyclerView.Adapter<FoodHolder>{
        private List<Food> mFoods;

        public FoodAdapter(List<Food> foods){
            mFoods = foods;
        }

        @Override
        public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new FoodHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(FoodHolder holder, int position){
            Food food = mFoods.get(position);
            holder.bind(food);
        }

        @Override
        public int getItemCount(){
            return mFoods.size();
        }
    }

/////////viewholder deals with the view associated with the list item
        private class FoodHolder extends RecyclerView.ViewHolder{
            private Food mFood;
            private TextView mTitleTextView;
            private CircularImageView mImgPathImageView;

            //ViewHolder constructor inflates the food item layout
            public FoodHolder(LayoutInflater inflater, ViewGroup parent){
                super(inflater.inflate(R.layout.list_item_food,parent,false));

                mTitleTextView = (TextView) itemView.findViewById(R.id.food_item_text);
                mImgPathImageView = (CircularImageView) itemView.findViewById(R.id.food_item_img);
            }

            //this method will be called every time a new Crime should be displayed in your crimeholder.
            public void bind(Food food){
                mFood = food;
                mTitleTextView.setText(mFood.getTitle());
                mImgPathImageView.setImageResource(mFood.getImgPath());
            }

        }
}
