package com.ugurakcelik.demo.model.university;
public class Student {

    private String first;
    private String last;
    private final Integer id;

    private static int idCounter = 10000;

    public Student(String first, String last) {
        this.first = first;
        this.last = last;
        this.id = idCounter++;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + first + " " + last;
    }

}

