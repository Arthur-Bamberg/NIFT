package model;

import JDBC.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    //Falta read de ClientUser 
    private  int idUser; //isManager não implementado
    private String name, email, password;
    private boolean isValid, successfullyRecorded = false;
    
    public User() {
    }
    
    public User(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean setSuccessfullyRecorded() {
        return this.successfullyRecorded;
    }
    public void setSuccessfullyRecorded(boolean successfullyRecorded) {
        this.successfullyRecorded = successfullyRecorded;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean setIsValid() {
        return this.isValid;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean getSuccessfullyRecorded() {
        return successfullyRecorded;
    }

    public void save() {
        Conexao dbConnection = new Conexao();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO ClientUser"
                + "(idClientUser, name, email, password, isManager) VALUES" 
                + "(ClientUser_seq.nextval, ?, ?, ?, 0)"; 

        try {
            preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL, new String[] { "idClientUser" });

            preparedStatement.setString(1, this.getName());
            preparedStatement.setString(2, this.getEmail());
            preparedStatement.setString(3, this.getPassword());
            // execute insert SQL statement
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (null != generatedKeys && generatedKeys.next()) {
                this.idUser = generatedKeys.getInt(1);
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
        String insertTableSQL = "UPDATE Category SET name = ?, email = ?, password = ? WHERE idClientUser = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, this.getName());
            preparedStatement.setString(2, this.getEmail());
            preparedStatement.setString(3, this.getPassword());
            preparedStatement.setInt(4, this.getIdUser());
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
        String insertTableSQL = "DELETE FROM ClientUser WHERE idClientUser = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.getIdUser());
            preparedStatement.executeUpdate();
            successfullyRecorded = true;
        } catch (Exception e) {
            System.out.println("[ERROR]: DELETE FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }
    }

    public void validateUser(String email, String password) {
        Conexao dbConnection = new Conexao();
        String insertTableSQL = "SELECT * FROM ClientUser where email = ? and password = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                this.setIsValid(true);
                this.setIdUser(rs.getInt("idClientUser"));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: validateUser FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }
    }
}