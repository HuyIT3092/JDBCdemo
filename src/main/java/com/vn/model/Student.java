package com.vn.model;

public class Student {
    private Integer ID;
    private String name;
    private Double mark;

    public Student(){

    }

    public Student(Integer ID, String name, Double mark) {
        super();
        this.ID = ID;
        this.name = name;
        this.mark = mark;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
