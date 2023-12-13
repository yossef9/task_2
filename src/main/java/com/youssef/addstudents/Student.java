package com.youssef.addstudents;

public class Student {
    private int number;
    private String lastName;
    private String firstName;

    public Student() {
    	
    }
    public Student(int number, String lastName, String firstName) {
    	this.number = number;
    	this.firstName = firstName;
    	this.lastName = lastName;
    }
    

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
