package com.example.management_app.servlet.invoiceServlet;

import com.example.management_app.dao.InvoiceDao;
import com.example.management_app.model.Invoice;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/invoices/delete")
public class DeleteInvoiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idInvoice");

        try {
            Long id = Long.parseLong(idStr);
            InvoiceDao invoiceDao = new InvoiceDao();
            Optional<Invoice> invoice = invoiceDao.findById(id);

            if (invoice.isPresent()) {
                invoiceDao.delete(invoice.get());
                resp.sendRedirect(req.getContextPath() + "/invoices");
            } else {
                //TODO : game not found
            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}
