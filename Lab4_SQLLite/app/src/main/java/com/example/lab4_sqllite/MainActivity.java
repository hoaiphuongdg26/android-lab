package com.example.lab4_sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.ContactAdapter;
import adapter.DbAdapter;
import handler.DatabaseHandler;
import handler.StudentAdapter;
import handler.StudentHelper;
import model.Contact;
import model.Student;

public class MainActivity extends AppCompatActivity {
    //References
    private ListView lvUsers;
    private DbAdapter dbAdapter;
    private Cursor cursor;
    private List<String> users;

    //Contact
    private ListView lvContacts;
    private DatabaseHandler databaseHandler;
    private ContactAdapter contactAdapter;

    //Student
    ArrayList<Student> arrayStudent;
    StudentAdapter adapter;
    RecyclerView recyclerView;
    StudentHelper helper;
    Button btn_addStudent, btn_updateStudent, btn_cancelUpdateStudent;
    EditText et_add_studentName, et_add_studentId;
    RadioGroup rd_group;
    RadioButton rd_male, rd_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.references);

//        lvUsers = findViewById(R.id.lv_user);
//        references();

//        setContentView(R.layout.contact);
//        lvContacts = findViewById(R.id.lv_contact);
//        contact();
//
        setContentView(R.layout.activity_main);
        mapping();
        student();
    }

    private void student() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        arrayStudent = new ArrayList<>();
        adapter = new StudentAdapter(arrayStudent, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new StudentAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_profile);

                TextView studentIdProfile = dialog.findViewById(R.id.tv_studentId);
                TextView studentNameProfile = dialog.findViewById(R.id.tv_studentName);
                ImageView img_male = dialog.findViewById(R.id.img_male);
                ImageView img_female = dialog.findViewById(R.id.img_female);

                String studentId_profile = arrayStudent.get(position).getStudentId();
                String name_profile = arrayStudent.get(position).getFullName();

                studentIdProfile.setText(studentId_profile);
                studentNameProfile.setText(name_profile);

                if (img == 0) {
                    img_male.setVisibility(View.VISIBLE);
                    img_female.setVisibility(View.GONE);
                } else {
                    img_male.setVisibility(View.GONE);
                    img_female.setVisibility(View.VISIBLE);
                }
                dialog.show();
            }
        });

        // Empty database
        helper = new StudentHelper(MainActivity.this, "sinhvien.sqlite", null, 1);
        // Create Employee table
        helper.QueryData("CREATE TABLE IF NOT EXISTS SINHVIEN(Id INTEGER PRIMARY KEY AUTOINCREMENT, MSSV VARCHAR(8), NAME NVARCHAR(100))");

        btn_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentID = et_add_studentId.getText().toString();
                String studentName = et_add_studentName.getText().toString();

                if (TextUtils.isEmpty(studentID) || TextUtils.isEmpty(studentName)) {
                    Toast.makeText(MainActivity.this, "Please fill in the blank field", Toast.LENGTH_SHORT).show();
                    return;
                }
                helper.QueryData("INSERT INTO SINHVIEN VALUES(null, '" + studentID + "', '" + studentName + "')");
                ActionGetData();
            }
        });

        ActionGetData();
    }

    public void ActionGetData() {
        Cursor dataStudent = helper.GetData("SELECT * FROM SINHVIEN");
        arrayStudent.clear();
        while (dataStudent.moveToNext())
        {
            int id = dataStudent.getInt(0);
            String studentId = dataStudent.getString(1);
            String studentName = dataStudent.getString(2);
            arrayStudent.add(new Student(id,studentId, studentName));
        }
        adapter.notifyDataSetChanged();
    }

    private void contact() {
        databaseHandler = new DatabaseHandler(MainActivity.this);
        databaseHandler.addContact(new Contact(1, "Ravi", "9100000000"));
        databaseHandler.addContact(new Contact(2, "Srinivas", "9199999999"));
        databaseHandler.addContact(new Contact(3, "Tommy", "9522222222"));
        databaseHandler.addContact(new Contact(4, "Karthik", "9533333333"));

        contactAdapter = new ContactAdapter(MainActivity.this, databaseHandler.getAllContacts());
        lvContacts.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();

        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = databaseHandler.getContact(i + 1);
                databaseHandler.deleteContact(contact);
                contactAdapter = new ContactAdapter(MainActivity.this, databaseHandler.getAllContacts());
                lvContacts.setAdapter(contactAdapter);
                contactAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void references() {
        dbAdapter = new DbAdapter(MainActivity.this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        for (int i = 0; i < 10; i++) {
            dbAdapter.createUser("Nguyễn Văn An " + i);
        }

        users = getData();
        showData();
        dbAdapter.close();
    }

    @SuppressLint("Range")
    private List<String> getData() {
        users = new ArrayList<>();

        cursor = dbAdapter.getAllUsers();
        while (cursor.moveToNext()) {
            users.add(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NAME)));
        }
        return users;
    }

    private void showData() {
        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.item_user, users);
        lvUsers.setAdapter(userAdapter);
    }

    private void mapping() {
        btn_addStudent = findViewById(R.id.btn_addStudent);
        et_add_studentId = findViewById(R.id.et_addStudentID);
        et_add_studentName = findViewById(R.id.addStudentName);
        rd_group = findViewById(R.id.rd_group);
        rd_male = findViewById(R.id.rd_male);
        rd_female = findViewById(R.id.rd_female);
    }

    public void DialogUpdate(String name, int id)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);

        //   EditText et_updateMSSV = dialog.findViewById(R.id.et_updateMSSV);
        EditText et_updateTen = dialog.findViewById(R.id.et_updateName);
        Button btn_updateSV = dialog.findViewById(R.id.btn_updateStudent);
        Button btn_huyUpdate = dialog.findViewById(R.id.btn_cancelUpdateStudent);

        et_updateTen.setText(name);

        dialog.show();
        btn_huyUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                return;
            }
        });

        btn_updateSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenSV = et_updateTen.getText().toString();
                helper.QueryData("UPDATE SINHVIEN SET NAME = '"+tenSV+"' WHERE Id = '"+id+"' ");
                dialog.dismiss();
                ActionGetData();
            }
        });
    }

    public void DialogDelete(String MSSV, String name, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you accept to delete " + name + " that has ID " + MSSV + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                helper.QueryData("DELETE FROM SINHVIEN WHERE Id = '"+id+"'");
                ActionGetData();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    int img;

    public void Clicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        if (view.getId() == com.example.lab4_sqllite.R.id.rd_male) {
            if (checked)
                img = 0;
        } else if (view.getId() == com.example.lab4_sqllite.R.id.rd_female) {
            if (checked)
                img = 1;
        }
    }

}