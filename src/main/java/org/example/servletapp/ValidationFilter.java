package org.example.servletapp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
        filterName = "ValidationFilter",
        urlPatterns = {"/register", "/editUrl"} // Les URL qui doivent être filtrées

)
public class ValidationFilter implements Filter {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Récupérer les paramètres du formulaire
        String idAb = request.getParameter("idab");
        String nomAb = request.getParameter("nomab");

        // Validation des données
        if (idAb == null || nomAb.trim().isEmpty() || !isInteger(idAb)) {

            // Les données ne sont pas valides, rediriger ou afficher un message d'erreur
            PrintWriter pw = httpResponse.getWriter();
            pw.println("<script>alert('Donnees du formulaire non valides!');window.location.href='homepage';</script>");

        } else {
            // Les données sont valides, continuer la chaîne de filtres
            chain.doFilter(request, response);
        }
    }


    private boolean isInteger(String str) {
        try {
            if (str == null)
                return false;
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
