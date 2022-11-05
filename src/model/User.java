package model;

import JDBC.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private  int idUser;
    private boolean isValid;

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isIsValid() {
        return this.isValid;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}