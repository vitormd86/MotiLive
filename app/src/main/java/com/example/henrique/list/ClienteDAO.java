package com.example.henrique.list;

import android.database.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ClienteDAO {
    private Connection connection;
    public ClienteDAO() throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public void adiciona(Cliente cliente ) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = cliente.getId();
        int id_pessoa = cliente.getId_pessoa();
        try {
            PreparedStatement stmt;
            stmt = connection.prepareStatement("insert into cliente ('íd_pessoa','id',) values (" + id_pessoa + "," + id + ")");
            stmt.execute();
            stmt.close();
        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
// seta os valores

     //   stmt.setString(1,cliente.getId());
// executa

    }
}
