package org.example.servletapp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
        filterName = "ValidationFilter",
        urlPatterns = {"/register", "/editUrl"} // URL patterns of the servlets to be filtered

)
public class ValidationFilter implements Filter {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get form data
        String idAb = request.getParameter("idab");
        String nomAb = request.getParameter("nomab");

        // Check if form data is valid
        if (idAb == null || nomAb.trim().isEmpty() || !isInteger(idAb)) {

            // Form data is invalid
            PrintWriter pw = httpResponse.getWriter();
            pw.println("<script>alert('Donnees du formulaire non valides!');window.location.href='homepage';</script>");

        } else {
            // Form data is valid
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
