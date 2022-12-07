package com.example.management_app.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Float vat;

    @OneToMany(mappedBy = "product")
    private List<InvoiceProductAssociation> associationList = new ArrayList<>();

    public Product() {

    }

    public Product(String name, String description, Float price, Float vat) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.vat = vat;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public List<InvoiceProductAssociation> getAssociationList() {
        return associationList;
    }

    public void setAssociationList(List<InvoiceProductAssociation> associationList) {
        this.associationList = associationList;
    }
}
