package com.example.lab3_animation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lab3_animation.fragments.Fragment1;
import com.example.lab3_animation.fragments.Fragment2;

import java.util.ArrayList;
import java.util.List;

public class Lab3_2 extends AppCompatActivity {

    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab32);
        viewPager2 = findViewById(R.id.viewPager2);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        viewPager2.setAdapter(new FragmentAdapter(this, fragments));
    }
}