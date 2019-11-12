package com.example.starbucksmobilerecreate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new Login_ViewPagerAdapter(getSupportFragmentManager()));
    }
}
