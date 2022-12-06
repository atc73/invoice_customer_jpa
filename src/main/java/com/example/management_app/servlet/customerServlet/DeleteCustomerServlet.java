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

@WebServlet(urlPatterns = "/customers/delete")
public class DeleteCustomerServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idCustomer");

        try {
            Long id = Long.parseLong(idStr);
            CustomerDao dao = new CustomerDao();
            Optional<Customer> customer = dao.findById(id);

            if (customer.isPresent()) {
                dao.delete(customer.get());
                resp.sendRedirect(req.getContextPath() + "/customers");
            } else {
                //TODO : game not found
            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}
