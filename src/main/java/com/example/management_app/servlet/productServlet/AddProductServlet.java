package com.example.management_app.servlet.productServlet;

import com.example.management_app.dao.CustomerDao;
import com.example.management_app.dao.InvoiceDao;
import com.example.management_app.dao.ProductDao;
import com.example.management_app.model.Customer;
import com.example.management_app.model.Invoice;
import com.example.management_app.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/products/add")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAll();
        req.setAttribute("products", products);

        req.getRequestDispatcher("/WEB-INF/add-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Float total = Float.parseFloat(req.getParameter("price"));
        Float vat = Float.parseFloat(req.getParameter("vat"));

        Product product = new Product(name, description, total, vat);

        ProductDao productDao = new ProductDao();

        productDao.save(product);

        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
