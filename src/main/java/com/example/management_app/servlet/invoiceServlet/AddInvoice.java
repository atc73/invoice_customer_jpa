package com.example.management_app.servlet.invoiceServlet;

import com.example.management_app.dao.CustomerDao;
import com.example.management_app.dao.InvoiceDao;
import com.example.management_app.model.Customer;
import com.example.management_app.model.Invoice;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/invoices/add")
public class AddInvoice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getAll();
        req.setAttribute("customers", customers);

        req.getRequestDispatcher("/WEB-INF/add-invoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(req.getParameter("invoiceDate"));
        Float total = Float.parseFloat(req.getParameter("invoiceTotal"));
        String customerId = req.getParameter("customers");
        Long id = Long.parseLong(customerId);


        CustomerDao customerDao = new CustomerDao();
        Optional<Customer> customer =  customerDao.findById(id);

        if (customer.isPresent()) {
            Invoice invoice = new Invoice(date, total, customer.get());
            InvoiceDao invoiceDao = new InvoiceDao();
            invoiceDao.save(invoice);
        } else {
            System.out.printf("customer not found");
        }
        resp.sendRedirect(req.getContextPath() + "/invoices");
    }
}
