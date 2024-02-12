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

@WebServlet("/editUrl")
public class EditServlet extends HttpServlet {
    private static final String query = "UPDATE abonne SET nomab=? WHERE idab=?";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        // Getting book ID
        int idAb = Integer.parseInt(req.getParameter("idab"));

        // Get the new values from the request
        String nomAb = req.getParameter("nomab");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "");
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, nomAb);
                ps.setInt(2, idAb);

                // Executing the update & verifying its status
                int count = ps.executeUpdate();
                if (count == 1) {
                    // Display success message and redirect
                    pw.println("<script>alert('Abonné modifié avec succès!'); window.location.href='homepage';</script>");

                    // Set session attribute to the modified user ID
                    HttpSession session = req.getSession();
                    session.setAttribute("modifiedab", idAb);

                } else {
                    // Display error message
                    pw.println("<script>alert('ERROR!');</script>");
                }

            } catch (SQLException se) {
                pw.println("<h1>Error: " + se.getMessage() + "</h1>");
                se.printStackTrace();
            }
        } catch (ClassNotFoundException cnf) {
            pw.println("<h1>Error: " + cnf.getMessage() + "</h1>");
            cnf.printStackTrace();
        } finally {
            pw.println("</tbody>\n" +
                    "</table>\n" +
                    "<a href='homepage' class='btn btn-secondary'>Go to Homepage</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

}
