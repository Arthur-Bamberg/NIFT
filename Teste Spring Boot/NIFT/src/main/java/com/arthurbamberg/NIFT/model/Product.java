package com.arthurbamberg.NIFT.model;

import java.util.ArrayList;

public class Product {
	private int idProduct;
    private String name, url;
    private boolean successfullyRecorded = false;
    private ArrayList<Category> categories = new ArrayList<>();

    public Product(int idProduct, String name, String url) {
        this.idProduct = idProduct;
        this.name = name;
        this.url = url;
    }

    public int getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public boolean getSuccessfullyRecorded() {
        return successfullyRecorded;
    }

}
