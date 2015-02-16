package com.example.henrique.list;

import android.database.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class ProfissionalDAO {
    private Connection connection;
    public ProfissionalDAO() throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public void adiciona(Profissional profissional) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        PreparedStatement stmt = this.connection.prepareStatement("insert into pessoa (nome,id) values (?, ?)");
// seta os valores
        stmt.setString(1,profissional.getNome());
        stmt.setString(2,profissional.getId());
// executa
        stmt.execute();
        stmt.close();
    }
}
