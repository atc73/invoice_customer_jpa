package com.example.management_app.servlet.customerServlet;

import com.example.management_app.dao.CustomerDao;
import com.example.management_app.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/customers/update")
public class UpdateCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCustomer = req.getParameter("id");
        System.out.println("aaaaaaaaaa" + idCustomer);

        try {
            Long id = Long.parseLong(idCustomer);
            System.out.println("***********" + idCustomer);
            CustomerDao dao = new CustomerDao();
            Optional<Customer> customer = dao.findById(id);
            System.out.println("µµµµµµµµµ" + customer);

            if (customer.isPresent()) {
                req.setAttribute("customer", customer.get());
            } else {
                //TODO : game not found
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/update-customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("customerId"));
        String name = req.getParameter("customerName");
        String address = req.getParameter("customerAddress");
        Integer postcode = Integer.parseInt(req.getParameter("customerPostcode"));
        String city = req.getParameter("customerCity");
        String phoneNumber = req.getParameter("customerPhoneNumber");
        String email = req.getParameter("customerEmail");
        Customer customer = new Customer(id, name, address, postcode, city, phoneNumber, email);
        CustomerDao customerDao = new CustomerDao();
        customerDao.update(customer);

        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
