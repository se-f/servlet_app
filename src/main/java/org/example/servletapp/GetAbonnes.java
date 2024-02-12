package org.example.servletapp;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "getAbonnesServlet", value = "/getAbonnes")
public class GetAbonnes extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String query = "SELECT * FROM abonne";

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery();) {

                generateTable(pw, rs);

            } catch (SQLException se) {
                pw.println("<h1>Error: " + se.getMessage() + "</h1>");
                se.printStackTrace();
            }
        } catch (ClassNotFoundException cnf) {
            pw.println("<h1>Error: " + cnf.getMessage() + "</h1>");
            cnf.printStackTrace();
        }
    }

    private void generateTable(PrintWriter pw, ResultSet rs) throws SQLException {
        pw.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Liste des abonnés</title>\n" +
                "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
                "<style>\n" +
                ".small-input { max-width: 800px; }\n" +
                ".form-group.row { margin-bottom: 10px; }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container d-flex justify-content-center align-items-center\" style=\"height: 100vh; width:50vw;\">\n" +
                "<div class=\"card p-4\" style=\"width: 800px;\">\n" +
                "<h1 class=\"text-center mb-4\">Liste des abonnés</h1>\n" +
                "<table class=\"table\">\n" +
                "<thead class=\"thead-light\">\n" +
                "<tr><th>ID</th>" +
                "<th>Nom abonné</th>" +
                "<th>Modifier</th><th>Supprimer</th></tr>\n" +
                "</thead>\n" +
                "<tbody>");

        while (rs.next()) {
            pw.println("<tr>" +
                    "<td>" + rs.getInt(1) + "</td>" +
                    "<td>" + rs.getString(2) + "</td>" +
                    "<td><a href='editScreen?idab=" + rs.getInt(1) + "'>Modifier</a></td>" +
                    "<td><a href='deleteEntry?idab=" + rs.getInt(1) + "' style='color: red;'>Supprimer</a></td>" +
                    "</tr>");
        }

        pw.println("</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
    }


}
