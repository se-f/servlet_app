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

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT  * FROM abonne WHERE idab=?";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        if (username == null)
            pw.println("<script>alert('NON CONNECTE!!'); window.location.href='login';</script>");

        int idAb = Integer.parseInt(req.getParameter("idab"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "");
                 PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, idAb);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    generateEditForm(pw, rs, idAb);
                } else {
                    pw.println("<h1>Abonné inexistant!!</h1>");
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


    private void generateEditForm(PrintWriter pw, ResultSet rs, int idAb) throws SQLException {
        pw.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Modifier abonné</title>\n" +
                "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
                "</head>\n" +
                "<body style='display:flex; height:100vh; justify-content:center; align-items:center;'>\n" +
                "<div class='container mt-5'>\n" +
                "<div class='row justify-content-center'>\n" +
                "<div class='col-md-6'>\n" +
                "<div class='card'>\n" +
                "<div class='card-body'>\n" +
                "<h2 class='card-title text-center mb-4'>Modifier abonné</h2>\n" +
                "<form action='editUrl?id=" + idAb + "' method='post'>\n" +
                "<table class='table'>\n" +
                "<tr><td>ID:</td><td><input type='text' readonly class='form-control' name='idab' value='" + rs.getString(1) + "'></td></tr>\n" +
                "<tr><td>Nom:</td><td><input type='text' class='form-control' name='nomab' value='" + rs.getString(2) + "'></td></tr>\n" +
                "<tr><td colspan='2'>\n" +
                "<div class='text-center'>\n" +
                "<input type='submit' class='btn btn-primary mr-2' value='Enregistrer'>\n" +
                "<a href='homepage' class='btn btn-danger'>Annuler</a>\n" +
                "</div>\n" +
                "</td></tr>\n" +
                "</table>\n" +
                "</form>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
    }
}
