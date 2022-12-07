package model;

import JDBC.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Category {
    private int idCategory;
    private String name, description;
    private boolean successfullyRecorded = false;

    public Category(int idCategory, String name, String description) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
    }

    public int getIdCategory() {
        return this.idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getSuccessfullyRecorded() {
        return successfullyRecorded;
    }

    public void save() {
        Conexao dbConnection = new Conexao();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO Category"
                + "(idCategory, name, description) VALUES" //
                + "(Category_seq.nextval, ?, ?)"; //

        try {
            preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL, new String[] { "idCategory" });

            preparedStatement.setString(1, this.getName());
            preparedStatement.setString(2, this.getDescription());
            // execute insert SQL statement
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (null != generatedKeys && generatedKeys.next()) {
                this.idCategory = generatedKeys.getInt(1);
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
        String insertTableSQL = "UPDATE Category SET name = ?, description = ? WHERE idCategory = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, this.getName());
            preparedStatement.setString(2, this.getDescription());
            preparedStatement.setInt(3, this.getIdCategory());
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
        String insertTableSQL = "DELETE FROM Category WHERE idCategory = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.getIdCategory());
            preparedStatement.executeUpdate();
            successfullyRecorded = true;
        } catch (Exception e) {
            System.out.println("[ERROR]: DELETE FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }
    }

    public static ArrayList<Category> getByProduct(int idProduct) {
        ArrayList<Category> categories = new ArrayList<Category>();

        Conexao dbConnection = new Conexao();
        String insertTableSQL = "SELECT Category.idCategory, Category.name, Category.description FROM Category inner join Product_Category on Product_Category.FK_idCategory = Category.idCategory WHERE Product_Category.FK_idProduct = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, idProduct);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getInt("idCategory"), rs.getString("name"), rs.getString("description"));

                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: GET ALL FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }

        return categories;
    }

    public static ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<Category>();

        Conexao dbConnection = new Conexao();
        String insertTableSQL = "SELECT Category.idCategory, Category.name, Category.description FROM Category";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getInt("idCategory"), rs.getString("name"), rs.getString("description"));

                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: GET ALL FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }

        return categories;
    }
}