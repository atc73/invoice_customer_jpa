package com.example.management_app.servlet.invoiceServlet;

import com.example.management_app.dao.InvoiceDao;
import com.example.management_app.model.Invoice;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/invoices")
public class InvoiceListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InvoiceDao invoiceDao = new InvoiceDao();
        List<Invoice> invoices = invoiceDao.getAll();

        req.setAttribute("invoices", invoices);

        req.getRequestDispatcher("/WEB-INF/invoice-list.jsp").forward(req, resp);
    }
}
