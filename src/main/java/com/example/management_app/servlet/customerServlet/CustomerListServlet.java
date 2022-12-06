package com.example.management_app.servlet.customerServlet;

import com.example.management_app.dao.CustomerDao;
import com.example.management_app.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customers")
public class CustomerListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customerList = customerDao.getAll();

        req.setAttribute("customers", customerList);
        req.getRequestDispatcher("/WEB-INF/customer-list.jsp").forward(req, resp);
    }
}
