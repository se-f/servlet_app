package org.example.servletapp;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/deleteEntry")
public class DeleteServlet extends HttpServlet {

    private static final String query = "DELETE FROM abonne  WHERE idab=?";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        // Getting user ID
        int idAb = Integer.parseInt(req.getParameter("idab"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {

                ps.setInt(1, idAb);

                int count = ps.executeUpdate();
                if (count == 1) {
                    // Display success popup and redirect to bookList or homepage
                    pw.println("<script>alert('Abonné supprimé avec succès!'); window.location.href='homepage';</script>");


                    // Set session attribute to the deleted user ID
                    HttpSession session = req.getSession();
                    session.setAttribute("deletedab", idAb);

                } else {
                    // Display error popup
                    pw.println("<script>alert('ERROR!');</script>");
                }

            } catch (SQLException se) {
                pw.println("<h1>Error: " + se.getMessage() + "</h1>");
                se.printStackTrace();
            }
        } catch (ClassNotFoundException cnf) {
            pw.println("<h1>Error: " + cnf.getMessage() + "</h1>");
            cnf.printStackTrace();
        }
    }
}
