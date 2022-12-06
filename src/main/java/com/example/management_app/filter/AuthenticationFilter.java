package com.example.management_app.filter;

import com.example.management_app.servlet.logServlet.LoginServlet;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/customers/*", "/invoices/*", "/logout"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // Get existing session or return null
        boolean wantCreateIfNotExist = false;
        HttpSession session = req.getSession(wantCreateIfNotExist);

        if(session != null && session.getAttribute("username") != null){
            // Filtrage OK
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            resp.sendRedirect(LoginServlet.URL);
        }
    }
}
