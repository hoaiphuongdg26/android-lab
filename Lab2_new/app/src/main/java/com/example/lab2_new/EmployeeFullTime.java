package com.example.lab2_new;

import androidx.annotation.NonNull;

public class EmployeeFullTime extends Employee {
    @Override
    public double TinhLuong() {
        return 500;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString() +" -->FullTime="+TinhLuong();
    }
}
