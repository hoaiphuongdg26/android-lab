package com.example.lab2_new;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Activity4_EmployeeAdapter extends ArrayAdapter<Activity4_EmployeeIsManager> {
    private Activity context;

    public Activity4_EmployeeAdapter(Activity context, int layoutID, ArrayList<Activity4_EmployeeIsManager> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity4_item_employee, parent, false);
        }

        // Get item
        Activity4_EmployeeIsManager employee = getItem(position);

        // Get view
        TextView tvFullName = convertView.findViewById(R.id.item_employee_tv_fullname);
        TextView tvPosition = convertView.findViewById(R.id.item_employee_tv_position);
        ImageView ivManager = convertView.findViewById(R.id.item_employee_iv_manager);
        LinearLayout llParent = convertView.findViewById(R.id.item_employee_ll_parent);

        // Set fullname
        if (employee != null && employee.getFullName() != null) {
            tvFullName.setText(employee.getFullName());
        } else {
            tvFullName.setText("");
        }

        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee != null && employee.isManager()) {
            ivManager.setVisibility(View.VISIBLE);
            tvPosition.setVisibility(View.GONE);
        } else {
            ivManager.setVisibility(View.GONE);
            tvPosition.setVisibility(View.VISIBLE);
            tvPosition.setText(context.getString(R.string.staff));
        }

        // Show different color backgrounds for 2 continuous employees
        if (position % 2 == 0) {
            llParent.setBackgroundResource(R.color.white);
        } else {
            llParent.setBackgroundResource(R.color.light_blue);
        }
        return convertView;
    }
}
