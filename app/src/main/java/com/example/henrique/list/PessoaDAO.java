package com.example.henrique.list;

import android.content.Context;
import android.database.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PessoaDAO {
    private Connection connection;
    public PessoaDAO(Context context) throws SQLException {
        this.connection = ConnectionFactory.connect(context);
    }
    public void adiciona(Pessoa pessoa) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        PreparedStatement stmt = this.connection.prepareStatement("insert into pessoa (nome,email,endereco) values (?, ?, ?)");
// seta os valores
        stmt.setString(1,pessoa.getNome());
        stmt.setString(2,pessoa.getEmail());
        stmt.setString(3,pessoa.getEndereco());
// executa
        stmt.execute();
        stmt.close();
    }
}
