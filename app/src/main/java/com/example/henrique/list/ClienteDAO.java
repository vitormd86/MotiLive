package com.example.henrique.list;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutionException;


public class ClienteDAO extends AsyncTask<Void, Void, Boolean> {
    private static Connection connection;
    private Context context;
    private static final String url = "jdbc:mysql://192.169.198.138:3306/motilive";
    private static final String user = "root";
    private static final String pass = "4linux";
    private static Connection  con; 		// armazena a conexão atual
    private Log log; 				// gera log
    private ProgressDialog dialog; 	// dialog para mostrar o progresso da conexão


    public ClienteDAO(Context context) throws SQLException {

        this.context = context;

        con = connect(context);


        try {
            if (execute().get()) if (isConected()) {
                this.connection = connect(context);

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
    public Boolean isConected()
    {
        try
        {
            if (con == null)
            {
                return false;
            } else {
                return (!this.con.isClosed());
            }
        } catch (java.sql.SQLException e) {
            return false;
        }
    }
    public static Connection connect(Context context)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            Toast.makeText( context, "conectado", Toast.LENGTH_SHORT);

            //   log.add("Conectado com sucesso!");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    public void disconnect()
    {
        try {
            con.close();
            con.isClosed();
            //  log.add("Desconectado!");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(this.context);
        dialog.setMessage("Aguarde... conectando ao banco de dados...");
        dialog.show();
    }

    /***
     * O médoto doInBackground é chamado através do método run() de AsyncTask
     * Ele roda após o método onPreExecute
     * É aqui que vamos realizar a conexão com o database.
     */
    @Override
    protected Boolean doInBackground(Void... params) {
        connect(context);
        return isConected();
    }

    /***
     * O médoto onPostExecute é chamado após a execução do método doInBackground()
     * Aqui vamos fechar a janela de diálogo da conexão com o database
     */
    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        dialog.dismiss();
    }
}
