package org.example.servletapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", value = "/loginservice")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            PrintWriter out = response.getWriter();
            out.println("<html><body><p>Login failed. Can't find JDBC driver.</p></body></html>");
        }


        if (isValidUser(username, password)) {
            // Create a session and store user information
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect to a welcome page or perform further actions
            response.sendRedirect("homepage");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<html><body><p>Login failed. Invalid username or password.</p></body></html>");
        }
    }

    private boolean isValidUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "")) {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If there is a matching user in the database
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
