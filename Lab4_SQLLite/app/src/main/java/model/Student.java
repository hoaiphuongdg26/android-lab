package model;

public class Student {
    public String StudentId;
    public String FullName;
    public Integer id;

    public Student(Integer ID ,String studentId, String fullname) {
        id = ID;
        FullName = fullname;
        StudentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        this.StudentId = studentId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }
}
