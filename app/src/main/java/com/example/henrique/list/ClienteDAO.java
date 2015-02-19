package com.example.henrique.list;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutionException;


public class ClienteDAO extends Activity {
    private static Connection connection;
    private Context context;


    public ClienteDAO(Context context) throws SQLException {

        ConnectionFactory con;
        this.context = context;

        con = new ConnectionFactory(context);


        try {
            if (con.execute().get()) if (con.isConected()) {
                this.connection = con.connect(context);

            } else {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
