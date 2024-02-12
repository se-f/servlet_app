package org.example.servletapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "homepageServlet", value = "/homepage")
public class Homepage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {


            HttpSession session = request.getSession();
            Object username = session.getAttribute("username");
            if (username == null)
                out.println("<script>alert('NON CONNECTE!!'); window.location.href='login';</script>");

            // HTML FORM
            out.println("<html><head><title>Ajouter abonné</title>"
                    + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">"
                    + "<style>"
                    + "    body {"
//                    + "        display: flex;"
//                    + "        align-items: center;"
                    + "        height: 100vh;"
                    + "        margin: 0;"
                    + "        font-family: arial;}"
                    + "    .container1 {"
                    + "        text-align: center; width:45vw;}"
                    + "    form {"
                    + "        display: inline-block;"
                    + "        text-align: left;"
                    + "        padding: 20px;"
                    + "        border: 1px solid #ccc;"
                    + "        border-radius: 5px;}"
                    + "     .maincontainer{ display: flex; align-items: center; }"
                    + "     .session_container{"
                    + "           padding-bottom:5px; margin-top:-5vh; text-align:center;"
                    + "           display:flex; flex-direction:row; align-items:center; justify-content:center;" +
                    "        }"
                    + "    .session_container p { padding-left: 5px; padding-right:5px; }"
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
                    + "<div class=\"maincontainer\">"
                    + "<div class=\"container1\">"
                    + "<form action=\"register\" method=\"post\">"
                    + "<h1 class=\"text-center mb-4\">Ajouter abonné</h1>");


            // ID TEXTFIELD
            out.println("<label for=\"idab\">ID:</label>");
            out.println("<input type=\"text\" id=\"idab\" name=\"idab\" placeholder=\"99\" />");

            // NOMAB TEXTFIELD
            out.println("<label for=\"nomab\">Nom:</label>");
            out.println("<input type=\"text\" id=\"nomab\" name=\"nomab\" placeholder=\"XYZ\" />");

            // BUTTON
            out.println("<br><button type=\"submit\">Créer</button></form>");
            out.println("</div>");
//            out.println("</div>");


            // Forward the request to ContentProviderServlet
            RequestDispatcher dispatcher = request.getRequestDispatcher("/getAbonnes");
            dispatcher.include(request, response);

            out.println("</div>");
            out.println("<div class=\"session_container\">");
            out.println("<p>INFO:</p>");


            // Getting other session info
            if (username != null) {
                out.println("<p>Connecté tant que: " + username + "</p>");
            } else
                out.println("<p>Pas encore connecté!</p>");


            Object registeredAb = session.getAttribute("registeredab");
            if (registeredAb != null)
                out.println("<p>Dernier abonné créé: " + registeredAb + "</p>");

            Object modifiedAb = session.getAttribute("modifiedab");
            if (modifiedAb != null)
                out.println("<p>Dernier abonné modifié: " + modifiedAb + "</p>");

            Object deletedAb = session.getAttribute("deletedab");
            if (deletedAb != null)
                out.println("<p>Dernier abonné supprimé: " + deletedAb + "</p>");

            out.println("</div></body></html>");

        }
    }
}
