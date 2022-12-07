package com.example.management_app.servlet.productServlet;

import com.example.management_app.dao.CustomerDao;
import com.example.management_app.dao.ProductDao;
import com.example.management_app.model.Customer;
import com.example.management_app.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAll();

        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/product-list.jsp").forward(req, resp);
    }
}
