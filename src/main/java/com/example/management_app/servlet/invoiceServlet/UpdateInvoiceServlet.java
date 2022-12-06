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

@WebServlet(urlPatterns = "/invoices/update")
public class UpdateInvoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CustomerDao customerDao = new CustomerDao();
        List<Customer> customerList = customerDao.getAll();

        req.setAttribute("customers", customerList);

        String idInvoice = req.getParameter("id");
        System.out.println("aaaaaaaaaa" + idInvoice);

        try {
            Long id = Long.parseLong(idInvoice);
            System.out.println("***********" + idInvoice);
            InvoiceDao invoiceDao = new InvoiceDao();
            Optional<Invoice> invoice = invoiceDao.findById(id);
            System.out.println("µµµµµµµµµ" + invoice);

            if (invoice.isPresent()) {
                req.setAttribute("invoice", invoice.get());
            } else {
                //TODO : game not found
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/update-invoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("invoiceId"));
        LocalDate date = LocalDate.parse(req.getParameter("invoiceDate"));
        Float total = Float.parseFloat(req.getParameter("invoiceTotal"));

        Long customerId = Long.parseLong(req.getParameter("customers"));
        CustomerDao customerDao = new CustomerDao();

        Optional<Customer> customer = customerDao.findById(customerId);

        if (customer.isPresent()) {
            Invoice invoice = new Invoice(id, date, total, customer.get());
            InvoiceDao invoiceDao = new InvoiceDao();
            invoiceDao.update(invoice);
        } else {
            // to do
        }
        resp.sendRedirect(req.getContextPath() + "/invoices");
    }
}
