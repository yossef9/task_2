package com.youssef.addstudents;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AddNewStudentsServlet")
public class AddStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
        	// Load the JDBC driver for PostgreSQL
        	Class.forName("org.postgresql.Driver");
            // Replace "jdbc:mysql://your-database-url" with your actual database URL
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/scmdb", "postgres", "Abdes3536");
         // Create a connection to the database
            StudentDAO studentDAO = new StudentDAO(connection);

            if ("add".equals(action)) {
                addStudent(request, response, studentDAO);
            } else if ("view".equals(action)) {
                viewAllStudents(request, response, studentDAO);
            } else if ("update".equals(action)) {
                updateStudent(request, response, studentDAO);
            } else if ("delete".equals(action)) {
                deleteStudent(request, response, studentDAO);
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response, StudentDAO studentDAO)
            throws ServletException, IOException {
        // Retrieve student information from the request
        int number = Integer.parseInt(request.getParameter("number"));
        String lastName = request.getParameter("lastname");
        String firstName = request.getParameter("firstname");

        // Create a Student object
        Student student = new Student();
        student.setNumber(number);
        student.setLastName(lastName);
        student.setFirstName(firstName);

        // Add the student to the database
        try {
            studentDAO.addStudent(student);
            response.getWriter().println("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error adding student to the database.");
        }
    }

    private void viewAllStudents(HttpServletRequest request, HttpServletResponse response, StudentDAO studentDAO)
            throws ServletException, IOException {
        // Retrieve all students from the database
        try {
            List<Student> students = studentDAO.getAllStudents();
            // Display the students (you may want to format this better in a real application)
            for (Student student : students) {
                response.getWriter().println("Number: " + student.getNumber() + ", Last Name: " + student.getLastName()
                        + ", First Name: " + student.getFirstName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error retrieving students from the database.");
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response, StudentDAO studentDAO)
            throws ServletException, IOException {
        // Retrieve student information from the request
        int number = Integer.parseInt(request.getParameter("number"));
        String lastName = request.getParameter("lastname");
        String firstName = request.getParameter("firstname");

        // Create a Student object
        Student student = new Student();
        student.setNumber(number);
        student.setLastName(lastName);
        student.setFirstName(firstName);

        // Update the student in the database
        try {
            studentDAO.updateStudent(student);
            response.getWriter().println("Student updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error updating student in the database.");
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response, StudentDAO studentDAO)
            throws ServletException, IOException {
        // Retrieve student number from the request
        int number = Integer.parseInt(request.getParameter("number"));

        // Delete the student from the database
        try {
            studentDAO.deleteStudent(number);
            response.getWriter().println("Student deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting student from the database.");
        }
    }
}
