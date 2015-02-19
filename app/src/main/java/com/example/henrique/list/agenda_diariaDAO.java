package com.example.henrique.list;

import android.database.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class agenda_diariaDAO {
    private Connection connection;

    public agenda_diariaDAO() throws SQLException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void adiciona(agenda_diariaDAO agenda_diaria) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        PreparedStatement stmt = this.connection.prepareStatement("insert into pessoa (id,data,dia_util,hora_entrada, hora_saida,id_profissional) values (?, ?, ?, ?, ?, ?)");
// seta os valores

   /*     stmt.setString(1,agenda_diaria.getId());
        stmt.setString(2,agenda_diaria.getData());
        stmt.setString(3,agenda_diaria.getDia_util());
        stmt.setString(3,agenda_diaria.getHora_entrada());
        stmt.setString(3,agenda_diaria.getHora_saida());
        stmt.setString(3,agenda_diaria.getId_profissional());
// executa
        stmt.execute();
        stmt.close();
}
*/
    }
}