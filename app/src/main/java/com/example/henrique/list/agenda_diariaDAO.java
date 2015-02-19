package com.example.henrique.list;

import android.database.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Henrique on 16/02/2015.
 */
public class Agenda_DiariaDAO {
    private Connection connection;
    public Agenda_DiariaDAO() throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public void adiciona(Agenda_Diaria agenda_diaria) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        PreparedStatement stmt = this.connection.prepareStatement("insert into pessoa (id,data,dia_util,hora_entrada, hora_saida,id_profissional) values (?, ?, ?, ?, ?, ?)");
// seta os valores

        stmt.setString(1,agenda_diaria.getId());
        stmt.setString(2,agenda_diaria.getData());
        stmt.setString(3,agenda_diaria.getDia_util());
        stmt.setString(3,agenda_diaria.getHora_entrada());
        stmt.setString(3,agenda_diaria.getHora_saida());
        stmt.setString(3,agenda_diaria.getId_profissional());
// executa
        stmt.execute();
        stmt.close();
    }
}
