package entity;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String className;

    public Student(int id, String name, String className) {
        this.id = id;
        this.name = name;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "\n Thông tin sinh viên: \n" +
                "ID: " + id + "\n" +
                "Tên: " + name + "\n" +
                "Lớp: " + className;
    }
}