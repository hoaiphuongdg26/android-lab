package com.example.lab2_new;

import androidx.annotation.NonNull;

public class EmployeePartTime extends Employee {
    @Override
    public double TinhLuong() {
        return 150;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString() +" -->PartTime="+TinhLuong();
    }
}
