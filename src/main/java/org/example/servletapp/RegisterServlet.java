package org.example.servletapp;

import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int idAb = Integer.parseInt(request.getParameter("idab"));
        String nomAb = request.getParameter("nomab");

        String query = "INSERT INTO abonne(idab,nomab) VALUES(?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //loading dynamically the JDBC drivers

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {
                ps.setInt(1, idAb);
                ps.setString(2, nomAb);


                int count = ps.executeUpdate();
                if (count == 1) {
                    // Display success popup and redirect to bookList or homepage
                    pw.println("<script>alert('Abonné ajouté avec succès!'); window.location.href='homepage';</script>");


                    // Set session attribute to the registered user ID
                    HttpSession session = request.getSession();
                    session.setAttribute("registeredab", idAb);

                } else {
                    // Display error popup
                    pw.println("<script>alert('Echec, Not Registered!');</script>");
                }

            } catch (Exception e) {
                e.printStackTrace();
                // Display error popup with message and redirect to homepage
                System.out.println(e);

                // Display error popup
                pw.println("<script>alert('Echec ajout!'); window.location.href='homepage';</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Display error popup with message and redirect to homepage
            System.out.println(e);

            // Display error popup
            pw.println("<script>alert('Echec ajout!'); window.location.href='homepage';</script>");
        }
    }

    public void destroy() {
    }
}