package com.youssef.addstudents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    // Constructor that takes a database connection

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }


	public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (number, lastname, firstname) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, student.getNumber());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.executeUpdate();
        }
    }

    // Method to retrieve all students from the database

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setNumber(resultSet.getInt("number"));
                student.setLastName(resultSet.getString("lastname"));
                student.setFirstName(resultSet.getString("firstname"));
                students.add(student);
            }
        }
        return students;
    }

    // Method to update a student based on the number

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET lastname=?, firstname=? WHERE number=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getLastName());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setInt(3, student.getNumber());
            preparedStatement.executeUpdate();
        }
    }

    // Method to delete a student based on the number

    public void deleteStudent(int studentNumber) throws SQLException {
        String query = "DELETE FROM students WHERE number=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentNumber);
            preparedStatement.executeUpdate();
        }
    }
}
