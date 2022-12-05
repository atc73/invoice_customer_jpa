package com.example.management_app.model;

import jakarta.persistence.*;

@Entity
public class InvoiceProductAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_product_association_id", nullable = false)
    private Long invoiceProductAssociationId;

    private Integer productQuantity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    public InvoiceProductAssociation() {

    }

    public Long getInvoiceProductAssociationId() {
        return invoiceProductAssociationId;
    }

    public void setInvoiceProductAssociationId(Long invoiceProductAssociationId) {
        this.invoiceProductAssociationId = invoiceProductAssociationId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
