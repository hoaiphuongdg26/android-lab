package com.example.lab2_new;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        ListView lvPerson = (ListView) findViewById(R.id.lv_person);
        final String[] arr = {"Teo", "Ty", "Bin", "Bo"};
        // Tạo ArrayAdapter để hiển thị dữ liệu trong ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);

        // Gán adapter cho ListView
        lvPerson.setAdapter(adapter);

        // Đăng ký sự kiện khi một mục được chọn
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // Đối số arg2 là vị trí phần tử trong mảng
                TextView tvSelection = (TextView) findViewById(R.id.tv_selected_item);
                tvSelection.setText("position: " + arg2 + "; value: " + arr[arg2]);
                tvSelection.setVisibility(View.VISIBLE);
            }
        });

    }
}
