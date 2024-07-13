package com.example.lab2_new;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class Activity6 extends AppCompatActivity {
    EditText edtName;
    CheckBox chbxManager;
    Button btnAdd;
    RecyclerView rcv_Employee;
    ArrayList<Employee6> employees;
    EmployeeAdapter6 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6);

        edtName = (EditText) findViewById(R.id.edtName);
        chbxManager = (CheckBox) findViewById(R.id.chbxManager);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        rcv_Employee = (RecyclerView) findViewById(R.id.rcv_Employee);
        employees = new ArrayList<Employee6>();

        adapter = new EmployeeAdapter6(this, R.layout.item_employee6,employees);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_Employee.setLayoutManager(linearLayoutManager);
        rcv_Employee.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                Employee6 employee = new Employee6();
                if (chbxManager.isChecked())
                {
                    employee.setManager(true);
                }
                else
                {
                    employee.setManager(false);
                }
                employee.setFullName(name);
                //Đưa employee vào ArrayList
                employees.add(employee);
                //Cập nhập giao diện
                adapter.notifyDataSetChanged();
            }
        });
    }
}