package com.example.management_app.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id", nullable = false)
    private Long invoiceId;

    @Column(name="invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false)
    private Float total;

    @ManyToOne
    @Column(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceProductAssociation> associationList = new ArrayList<>();

    public Invoice() {

    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
