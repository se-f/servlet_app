package org.example.servletapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginScreenServlet", value = "/login")
public class LoginScreenServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            // HTML FORM
            out.println("<html><head><title>Se connecter</title>"
                    + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">"
                    + "<style>"
                    + "    body {"
                    + "        display: flex;"
                    + "        align-items: center;"
                    + "        height: 100vh;"
                    + "        margin: 0;"
                    + "        font-family: arial;}"
                    + "    .container1 {"
                    + "        text-align: center; width:100vw;}"
                    + "    form {"
                    + "        display: inline-block;"
                    + "        text-align: left;"
                    + "        padding: 20px;"
                    + "        width: 29vw;"
                    + "        border: 1px solid #ccc;"
                    + "        border-radius: 5px;}"
                    + "    label {"
                    + "        display: block;"
                    + "        margin-bottom: 8px; margin-top: 8px;}"
                    + "    input {"
                    + "        width: 100%;"
                    + "        padding: 8px;"
                    + "        margin-bottom: 16px;"
                    + "        box-sizing: border-box;}"
                    + "    button {"
                    + "        padding: 10px;"
                    + "        background-color: #007bff;"
                    + "        color: white;"
                    + "        border: none;"
                    + "        border-radius: 5px; margin-top:50px;"
                    + "        cursor: pointer;  margin-top:10px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class=\"container1\">"
                    + "<form action=\"loginservice\" method=\"post\">"
                    + "<h1 class=\"text-center mb-4\">Se connecter</h1>");


            // ID TEXTFIELD
            out.println("<label for=\"login\">Utilisateur:</label>");
            out.println("<input type=\"text\" id=\"login\" name=\"login\" placeholder=\"XYZ\" />");

            // NOMAB TEXTFIELD
            out.println("<label for=\"password\">Mot de passe:</label>");
            out.println("<input type=\"password\" id=\"password\" name=\"password\" placeholder=\"*******\" />");

            // BUTTON
            out.println("<br><button type=\"submit\">Login</button></form>");
            out.println("</div>");
            out.println("</div>");


            out.println("</body></html>");

        }
    }
}
