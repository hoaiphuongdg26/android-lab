package com.example.lab2_new;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;


import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {
    EditText editId,editName;
    Button btnNhap;
    RadioGroup radgroup;
    ListView lvNhanvien;
    ArrayList<Employee>arrEmployee=new ArrayList<Employee>();
    ArrayAdapter<Employee>adapter=null;
    //Khai báo 1 employee object
    Employee employee=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        editId=(EditText) findViewById(R.id.editmaNV);
        editName=(EditText) findViewById(R.id.editTenNV);
        btnNhap=(Button) findViewById(R.id.btnNhapNV);
        radgroup=(RadioGroup) findViewById(R.id.radiogroud1);
        lvNhanvien=(ListView) findViewById(R.id.lv_person);
        //đưa Data Source là các employee vào Adapter
        adapter=new ArrayAdapter<Employee>(this,
                android.R.layout.simple_list_item_1,
                arrEmployee);
        //đưa adapter vào ListView
        lvNhanvien.setAdapter(adapter);

        btnNhap.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                addNewEmployee();
            }
        });
    }
    //Hàm xử lý thêm một nhân viên mới
    public void addNewEmployee()
    {
        //Lấy ra đúng id của Radio Button được checked
        int radId=radgroup.getCheckedRadioButtonId();
        String id=editId.getText()+"";
        String name=editName.getText()+"";
        if(radId==R.id.radChinhthuc)
        {
            //tạo instance là FullTime
            employee=new EmployeeFullTime();
        }
        else
        {
            //Tạo instance là Partime
            employee=new EmployeePartTime();
        }
        //FullTime hay Partime thì cũng là Employee
        //nên có các hàm này là hiển nhiên
        employee.setId(id);
        employee.setName(name);
        //Đưa employee vào ArrayList
        arrEmployee.add(employee);
        //Cập nhập giao diện
        adapter.notifyDataSetChanged();

    }
}