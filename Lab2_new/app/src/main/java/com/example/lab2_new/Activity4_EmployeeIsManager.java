package com.example.lab2_new;

import androidx.annotation.NonNull;

public class Activity4_EmployeeIsManager{
    private String id;
    private String name;
    private boolean isManager;
    public Activity4_EmployeeIsManager(){
        super();
    }
    public Activity4_EmployeeIsManager(String id, String name, boolean isManager) {
        this.id = id;
        this.name = name;
        this.isManager = isManager;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return name;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManager(boolean isManager) {this.isManager = isManager;}

    @NonNull
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}
