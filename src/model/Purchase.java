package model;

import JDBC.Conexao;
import crud.CRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Purchase {
    //Falta read de Purchase, Purchase_Product e save de Purchase_Product e Purchase
    private int idPurchase;
    private String date;
    private boolean successfullyRecorded = false;
    private ArrayList<Product> products = null;
    
    public Purchase() {
        
    }
    
    public Purchase(String date) {
        this.date = date;
    }

    public Purchase(int idPurchase, String date) {
        this.idPurchase = idPurchase;
        this.date = date;
    }

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
        if(products == null) {
            this.products = Product.load(this.idPurchase);
        }
        
        return this.products;
    }
    
    public boolean getSuccessfullyRecorded() {
        return successfullyRecorded;
    }

    public void savePurchase_Product (int idProduct) {
        Conexao dbConnection = new Conexao();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO Product_Category"
                + "(idPurchaseProduct, FK_idPurchase, FK_idProduct) VALUES"
                + "(Purchase_Product_seq, nextval, ?, ?)"; 
    }

    public void save() {
        Conexao dbConnection = new Conexao();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO Purchase"
                + "(idPurchase, datePurchase, FK_idClientUser) VALUES" 
                + "(Purchase_seq.nextval, ?, ?)"; 

        try {
            preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL, new String[] { "idPurchase" });

            preparedStatement.setString(1, this.getDate());
            preparedStatement.setInt(2, CRUD.getGlobal().getIdUser());
            // execute insert SQL statement
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (null != generatedKeys && generatedKeys.next()) {
                this.idPurchase = generatedKeys.getInt(1);
            }

            successfullyRecorded = true;
        } catch (SQLException e) {
            System.out.println("[ERROR]: RECORD FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }
    }

    public void update() {
        Conexao dbConnection = new Conexao();
        String insertTableSQL = "UPDATE Purchase SET date = ? WHERE idPurchase = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, this.getDate());
            preparedStatement.setInt(2, this.getIdPurchase());
            preparedStatement.executeUpdate();
            successfullyRecorded = true;
        } catch (Exception e) {
            System.out.println("[ERROR]: UPDATE FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }
    }

    public void delete() {
        Conexao dbConnection = new Conexao();
        String insertTableSQL = "DELETE FROM Purchase WHERE idPurchase = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.getIdPurchase());
            preparedStatement.executeUpdate();
            successfullyRecorded = true;
        } catch (Exception e) {
            System.out.println("[ERROR]: DELETE FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }
    }
    
        public static ArrayList<Purchase> getAll() {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();

        Conexao dbConnection = new Conexao();
        String insertTableSQL = "SELECT Purchase.idPurchase, Purchase.datePurchase FROM Purchase where FK_idClientUser = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, CRUD.getGlobal().getIdUser());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Purchase purchase = new Purchase(rs.getInt("idPurchase"), rs.getString("datePurchase"));

                purchases.add(purchase);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: GET ALL FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }

        return purchases;
    }
}
