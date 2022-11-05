package model;

import JDBC.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product {
    private int idProduct;
    private String name, url;
    private ArrayList<Category> categories = new ArrayList<>();

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
}
