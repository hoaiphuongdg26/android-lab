package com.example.lab2_new;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Activity4 extends AppCompatActivity {
    EditText editId, editName;
    Button btnNhap;
    RadioButton radbtn;
    ListView lvNhanvien;
    ArrayList<Activity4_EmployeeIsManager> arrEmployee = new ArrayList<Activity4_EmployeeIsManager>();
    ArrayAdapter<Activity4_EmployeeIsManager> adapter = null;
    Activity4_EmployeeIsManager employee = new Activity4_EmployeeIsManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);

        editId = findViewById(R.id.editmaNV);
        editName = findViewById(R.id.editTenNV);
        btnNhap = findViewById(R.id.btnNhapNV);
        radbtn = findViewById(R.id.radIsManager);
        lvNhanvien = findViewById(R.id.lv_person);

        adapter = new Activity4_EmployeeAdapter(this, R.layout.activity4_item_employee, arrEmployee);

        // Đặt adapter cho ListView
        lvNhanvien.setAdapter(adapter);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewEmployee();
                // Xóa dữ liệu trong EditText
                editId.setText("");
                editName.setText("");

                // Đặt trạng thái checked của RadioButton về lại mặc định (nếu muốn)
                radbtn.setChecked(false);
            }
        });
    }

    // Hàm xử lý thêm một nhân viên mới
    public void addNewEmployee() {
        String id = editId.getText().toString();
        String name = editName.getText().toString();
        boolean isManager = radbtn.isChecked();

        employee = new Activity4_EmployeeIsManager();
        employee.setId(id);
        employee.setName(name);
        employee.setManager(isManager);

        // Đưa employee vào ArrayList
        arrEmployee.add(employee);

        // Cập nhập giao diện
        adapter.notifyDataSetChanged();
    }
}
