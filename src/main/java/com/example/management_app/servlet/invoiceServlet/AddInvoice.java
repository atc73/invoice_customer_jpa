package com.example.management_app.servlet.invoiceServlet;

import com.example.management_app.dao.AssoDao;
import com.example.management_app.dao.CustomerDao;
import com.example.management_app.dao.InvoiceDao;
import com.example.management_app.dao.ProductDao;
import com.example.management_app.model.Customer;
import com.example.management_app.model.Invoice;
import com.example.management_app.model.InvoiceProductAssociation;
import com.example.management_app.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/invoices/add")
public class AddInvoice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getAll();

        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAll();

        req.setAttribute("customers", customers);
        req.setAttribute("products", products);

        req.getRequestDispatcher("/WEB-INF/add-invoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(req.getParameter("invoiceDate"));
        String customerId = req.getParameter("customers");
        Long id = Long.parseLong(customerId);

        CustomerDao customerDao = new CustomerDao();
        ProductDao productDao = new ProductDao();
        AssoDao assoDao = new AssoDao();

        Float total = 0F;

        String[] checkedProductsStr = req.getParameterValues("checkedProducts");
        String[] quantitiesStr = req.getParameterValues("quantity");

        List<Integer> quantities = new ArrayList<>();
        for(int i = 0; i < checkedProductsStr.length; i++) {
            if(quantitiesStr[i] == "") {
                break;
            } else {
                quantities.add(Integer.parseInt(quantitiesStr[i]));
            }
        }

        List<Long> checkedProducts = new ArrayList<>();
        for(int i = 0; i < checkedProductsStr.length; i++) {
            checkedProducts.add(Long.parseLong(checkedProductsStr[i]));
            Float checkedProductPrice = productDao.findById(Long.parseLong(checkedProductsStr[i])).get().getPrice();
            Integer checkedProductQuantity = quantities.get(i);
            total += checkedProductPrice * checkedProductQuantity;
        }


        Optional<Customer> customer =  customerDao.findById(id);

        if (customer.isPresent()) {
            Invoice invoice = new Invoice(date, total, customer.get());
            InvoiceDao invoiceDao = new InvoiceDao();
            invoiceDao.save(invoice);
            for(int i = 0; i < checkedProductsStr.length; i++) {

                Optional<Product> product = productDao.findById(checkedProducts.get(i));
                Integer quantity = quantities.get(i);

                InvoiceProductAssociation asso = new InvoiceProductAssociation(quantity, invoice, product.get());
                assoDao.save(asso);
            }
        } else {
            System.out.printf("customer not found");
        }
        resp.sendRedirect(req.getContextPath() + "/invoices");
    }
}
