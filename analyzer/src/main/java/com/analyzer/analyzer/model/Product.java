package com.analyzer.analyzer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private Client client;
    private Category category;

    private String name, ref, brand;
    private String date;
    private boolean gatantee;

    public Product(Category category, Client client, String name, String ref, String brand, String date,
            boolean guarantee) {
        super();
        this.category = category;
        this.client = client;
        this.name = name;
        this.ref = ref;
        this.brand = brand;
        this.date = date;
        this.gatantee = guarantee;
    }

    public void setInfos() {
        name = "";
        ref = "";
        brand = "";
        date = "";
        gatantee = false;
    }

    public void setReference(String ref) {
        this.ref = ref;
    }

    public void setDateAchat(String date) {
        this.date = date;
    }
}
