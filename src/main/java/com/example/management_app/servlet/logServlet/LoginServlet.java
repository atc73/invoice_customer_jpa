package com.example.management_app.servlet.logServlet;

import com.example.management_app.dao.UserDao;
import com.example.management_app.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = LoginServlet.URL)
public class LoginServlet extends HttpServlet {


    public static final String URL = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            // Filtrage OK
            resp.sendRedirect("/invoices");
        }else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();
        Optional<User> foundUser = userDao.findByUsername(username);

        if (foundUser.isPresent()) {

            if (password.equals(foundUser.get().getPassword())) {
                // Get existing session or create one if not exist
                HttpSession session = req.getSession(true);

                session.setAttribute("username", username);
                // Expiration after 30 minutes
                session.setMaxInactiveInterval(30 * 60);

                resp.sendRedirect("/invoices");
            } else {
                req.setAttribute("loginFail", true);
                doGet(req, resp);
            }

        } else {
            req.setAttribute("loginFail", true);
            doGet(req, resp);
        }
    }
}
