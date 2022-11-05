package model;

import JDBC.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Purchase {
    private int idPurchase;
    private String date;
    private ArrayList<Product> products = new ArrayList<>();

    public int getIdPurchase() {
        return this.idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
