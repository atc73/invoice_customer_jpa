package com.example.management_app.servlet.customerServlet;


import com.example.management_app.dao.CustomerDao;
import com.example.management_app.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/customers/add")
public class AddCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add-customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("customerName");
        String address = req.getParameter("customerAddress");
        Integer postcode = Integer.parseInt(req.getParameter("customerPostcode"));
        String city = req.getParameter("customerCity");
        String phoneNumber = req.getParameter("customerPhoneNumber");
        String email = req.getParameter("customerEmail");

        Customer customer = new Customer(name, address, postcode, city, phoneNumber, email);

        CustomerDao customerDao = new CustomerDao();

        customerDao.save(customer);

        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
