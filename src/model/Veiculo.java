package model;

import JDBC.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Veiculo {// Troquei para veiculo porque a classe cria um veículo

    // SQL PARA CRIAÇÃO DA TABELA

    // create table veiculo(
    // id number primary key,
    // modelo varchar2(50) not null,
    //placa varchar2(8) not null,
    // preco number not null,
    // tipo varchar2(5) not null,
    // CHECK(tipo IN('Carro','Moto'))
    // );

    // SQL PARA CRIAÇÃO DA SEQUENCIA PARA CRIAR O ID

    // CREATE SEQUENCE veiculo_seq INCREMENT BY 1 START WITH 1 MINVALUE 1;

    private String placa;
    private String modelo;
    private double preco;
    private String tipo;
    private boolean successfullyRecorded = false;
    private int id;

    public Veiculo(String placa, String modelo, double preco, String tipo) {// Troquei name por modelo, porque é o nome do atributo
        setPlaca(placa);
        setModelo(modelo);
        setPreco(preco);
        setTipo(tipo);
    }

    public Veiculo(String placa, String modelo, double preco, String tipo, int id) {// Troquei name por modelo, porque é o nome do atributo
        setPlaca(placa);
        setModelo(modelo);
        setPreco(preco);
        setTipo(tipo);
        setId(id);
    }

    // Criei gets e setters para referenciar e receber os valores dos atributos

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setTipo(String tipo) {
        if (tipo == "Carro" || tipo == "Moto") {
            this.tipo = tipo;
        } else {
            this.tipo = "[ERRO] Tipo inválido";
        }
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getSuccessfullyRecorded() {
        return successfullyRecorded;
    }

    public void save() {
        Conexao dbConnection = new Conexao();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO veiculo"
                + "(id, placa, preco, modelo, tipo) VALUES" //
                + "(veiculo_seq.nextval, ?, ?, ?, ?)"; //

        try {
            preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL, new String[] { "id" });

            preparedStatement.setString(1, this.getPlaca());
            preparedStatement.setDouble(2, this.getPreco());
            preparedStatement.setString(3, this.getModelo());
            preparedStatement.setString(4, this.getTipo());
            // execute insert SQL statement
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (null != generatedKeys && generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
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
        String insertTableSQL = "UPDATE veiculo SET modelo = ?, placa = ?, preco = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, this.getModelo());
            preparedStatement.setString(2, this.getPlaca());
            preparedStatement.setDouble(3, this.getPreco());
            preparedStatement.setInt(4, this.id);
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
        String insertTableSQL = "DELETE FROM veiculo WHERE id = ?";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            successfullyRecorded = true;
        } catch (Exception e) {
            System.out.println("[ERROR]: DELETE FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }
    }

    public static ArrayList<Veiculo> getAll() {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

        Conexao dbConnection = new Conexao();
        String insertTableSQL = "SELECT * FROM veiculo";

        try {
            PreparedStatement preparedStatement = dbConnection.getConexao().prepareStatement(insertTableSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = null;

                if("Carro".equals(rs.getString("tipo"))) {
                    veiculo = new Carro(rs.getString("placa"), rs.getString("modelo"), rs.getDouble("preco"), rs.getInt("id"));
                } else {
                    veiculo = new Moto(rs.getString("placa"), rs.getString("modelo"), rs.getDouble("preco"), rs.getInt("id"));
                }

                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: GET ALL FAILED --> " + e);
        } finally {
            dbConnection.desconecta();
        }

        return veiculos;
    }
}