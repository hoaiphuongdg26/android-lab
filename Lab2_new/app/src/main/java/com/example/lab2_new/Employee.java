package com.example.lab2_new;

import androidx.annotation.NonNull;

public abstract class Employee {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double TinhLuong();

    @NonNull
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}
