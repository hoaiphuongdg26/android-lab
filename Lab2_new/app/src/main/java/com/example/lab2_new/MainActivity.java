package com.example.lab2_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ex1 = findViewById(R.id.btn_ex1);
        btn_ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến giao diện 1
                Intent intent = new Intent(MainActivity.this, Activity1.class);
                startActivity(intent);
            }
        });

        Button btn_ex2 = findViewById(R.id.btn_ex2);
        btn_ex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến giao diện 2
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
        Button btn_ex3 = findViewById(R.id.btn_ex3);
        btn_ex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến giao diện 3
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivity(intent);
            }
        });
        Button btn_ex4 = findViewById(R.id.btn_ex4);
        btn_ex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến giao diện 4
                Intent intent = new Intent(MainActivity.this, Activity4.class);
                startActivity(intent);
            }
        });
        Button btn_ex5 = findViewById(R.id.btn_ex5);
        btn_ex5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến giao diện 5
                Intent intent = new Intent(MainActivity.this, Activity5.class);
                startActivity(intent);
            }
        });
        Button btn_ex6 = findViewById(R.id.btn_ex6);
        btn_ex6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến giao diện 6
                Intent intent = new Intent(MainActivity.this, Activity6.class);
                startActivity(intent);
            }
        });
    }
}