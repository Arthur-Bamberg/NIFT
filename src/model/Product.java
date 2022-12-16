package model;

import JDBC.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product {

  //Falta read de Product e Product_Category
  private int idProduct;
  private String name, url;
  private boolean successfullyRecorded = false;
  private ArrayList<Category> categories = null;

  public Product(int idProduct, String name, String url) {
    this.idProduct = idProduct;
    this.name = name;
    this.url = url;
  }

  public Product(String name, String url) {
    this.name = name;
    this.url = url;
  }

  public Product() {}

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
    if (categories == null) {
      categories = Category.load(this.idProduct);
    }

    return this.categories;
  }

  public boolean getSuccessfullyRecorded() {
    return successfullyRecorded;
  }

  public void saveProduct_Category(int idCategory) {
    Conexao dbConnection = new Conexao();
    PreparedStatement preparedStatement = null;

    String insertTableSQL =
      "INSERT INTO Product_Category" +
      "(FK_idProduct, FK_idCategory) VALUES" +
      "(?, ?)";

    try {
      preparedStatement =
        dbConnection.getConexao().prepareStatement(insertTableSQL);

      preparedStatement.setInt(1, this.getIdProduct());
      preparedStatement.setInt(2, idCategory);
      // execute insert SQL statement
      preparedStatement.executeUpdate();

      successfullyRecorded = true;
    } catch (SQLException e) {
      System.out.println("[ERROR]: RECORD FAILED --> " + e);
    } finally {
      dbConnection.desconecta();
    }
  }

  public void save() {
    Conexao dbConnection = new Conexao();
    PreparedStatement preparedStatement = null;

    String insertTableSQL =
      "INSERT INTO Product" +
      "(idProduct, name, url) VALUES" +
      "(Product_seq.nextval, ?, ?)";

    try {
      preparedStatement =
        dbConnection
          .getConexao()
          .prepareStatement(insertTableSQL, new String[] { "idProduct" });

      preparedStatement.setString(1, this.getName());
      preparedStatement.setString(2, this.getUrl());
      // execute insert SQL statement
      preparedStatement.executeUpdate();

      ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

      if (null != generatedKeys && generatedKeys.next()) {
        this.idProduct = generatedKeys.getInt(1);
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
    String insertTableSQL =
      "UPDATE Category SET name = ?, url = ? WHERE idProduct = ?";

    try {
      PreparedStatement preparedStatement = dbConnection
        .getConexao()
        .prepareStatement(insertTableSQL);
      preparedStatement.setString(1, this.getName());
      preparedStatement.setString(2, this.getUrl());
      preparedStatement.setInt(3, this.getIdProduct());
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
    String insertTableSQL = "DELETE FROM Product WHERE idProduct = ?";

    try {
      PreparedStatement preparedStatement = dbConnection
        .getConexao()
        .prepareStatement(insertTableSQL);
      preparedStatement.setInt(1, this.getIdProduct());
      preparedStatement.executeUpdate();
      successfullyRecorded = true;
    } catch (Exception e) {
      System.out.println("[ERROR]: DELETE FAILED --> " + e);
    } finally {
      dbConnection.desconecta();
    }
  }

  public static ArrayList<Product> getAll() {
    ArrayList<Product> products = new ArrayList<Product>();

    Conexao dbConnection = new Conexao();
    String insertTableSQL =
      "SELECT Product.idProduct, Product.name, Product.url FROM Product";

    try {
      PreparedStatement preparedStatement = dbConnection
        .getConexao()
        .prepareStatement(insertTableSQL);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Product product = new Product(
          rs.getInt("idProduct"),
          rs.getString("name"),
          rs.getString("url")
        );

        products.add(product);
      }
    } catch (SQLException e) {
      System.out.println("[ERROR]: GET ALL FAILED --> " + e);
    } finally {
      dbConnection.desconecta();
    }

    return products;
  }

  public static ArrayList<Product> loadByPurchase(int idPurchase) {
    ArrayList<Product> products = new ArrayList<Product>();

    Conexao dbConnection = new Conexao();
    String insertTableSQL =
      "SELECT " +
      "Product.idProduct, " +
      "Product.name, " +
      "Product.url " +
      "FROM Product " +
      "inner join Purchase_Product " +
      "on Purchase_Product.FK_idProduct = product.idProduct " +
      "inner join Purchase " +
      "on purchase.idPurchase = Purchase_Product.FK_idPurchase " +
      "where purchase.idPurchase = ?";

    try {
      PreparedStatement preparedStatement = dbConnection
        .getConexao()
        .prepareStatement(insertTableSQL);
      preparedStatement.setInt(1, idPurchase);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Product product = new Product(
          rs.getInt("idProduct"),
          rs.getString("name"),
          rs.getString("url")
        );

        products.add(product);
      }
    } catch (SQLException e) {
      System.out.println("[ERROR]: GET ALL FAILED --> " + e);
    } finally {
      dbConnection.desconecta();
    }

    return products;
  }

  public static ArrayList<Product> loadByCategory(int idCategory) {
    ArrayList<Product> products = new ArrayList<Product>();

    Conexao dbConnection = new Conexao();
    String insertTableSQL =
      "SELECT Product.idProduct, Product.name, Product.url FROM Product inner join Product_Category on Product_Category.FK_idProduct = Product.idProduct WHERE Product_Category.FK_idCategory = ?";

    try {
      PreparedStatement preparedStatement = dbConnection
        .getConexao()
        .prepareStatement(insertTableSQL);
      preparedStatement.setInt(1, idCategory);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Product product = new Product(
          rs.getInt("idProduct"),
          rs.getString("name"),
          rs.getString("url")
        );

        products.add(product);
      }
    } catch (SQLException e) {
      System.out.println("[ERROR]: GET ALL FAILED --> " + e);
    } finally {
      dbConnection.desconecta();
    }

    return products;
  }
}
