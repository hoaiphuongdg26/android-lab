package com.example.lab2_new;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    private EditText editText;
    private Button btnAddPerson;
    private ListView lv_person;
    private TextView tv_selected_item;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        editText = findViewById(R.id.editText);
        btnAddPerson = findViewById(R.id.btnAddPerson);
        lv_person = findViewById(R.id.lv_person);
        tv_selected_item = findViewById(R.id.tv_selected_item);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv_person.setAdapter(adapter);

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                data.add(input);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        lv_person.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = data.get(position);
                tv_selected_item.setText("Vị trí: " + position + "; Giá trị: " + selectedItem);
                tv_selected_item.setVisibility(View.VISIBLE);
            }
        });

        lv_person.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
