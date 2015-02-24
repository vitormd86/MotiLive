package com.example.henrique.list.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.henrique.list.Bean.Agenda_Diaria;
import com.example.henrique.list.Bean.Plano_Profissional;
import com.example.henrique.list.atividadeTeste;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Time;



public class Plano_ProfissionalDAO extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private static final String url = "jdbc:mysql://192.169.198.138:3306/motilive";
    private static final String user = "root";
    private static final String pass = "4linux";
    private static Connection con;        // armazena a conexão atual
    private Log log;                // gera log
    private ProgressDialog dialog;    // dialog para mostrar o progresso da conexão


    public Plano_ProfissionalDAO(Context context) throws SQLException {

        this.context = context;

    }
    private int id_plano;
    private int id;
    private int id_profissional;
    private java.util.Date data_ativacao_plano;
    private double qtde_creditos_restantes;
    private String data_inicial;
    private String data_final;
    private String status;

    public void adiciona(Plano_Profissional plano_profissional) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = plano_profissional.getId();
        int id_profissional = plano_profissional.getId_profissional();
        int id_plano = plano_profissional.getId_plano();
        int  qtde_creditos_restantes = plano_profissional.getQtde_creditos_restantes();

        Date data_ativacao_plano = plano_profissional.getData_ativacao_plano();
        Date data_inicial = plano_profissional.getData_inicial();
        Date data_final = plano_profissional.getData_final();

      boolean status = plano_profissional.getStatus();



        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO PLANO_PROFISSIONAL (ID,ID_PROFISSIONAL,ID_PLANO,QTDE_CREDITOS_RESTANTES,DATA_ATIVACAO_PLANO,DATA_INICIAL,DATA_FINAL,STATUS) " + " VALUES(?,?,?,?,?,?,?)");

            stmt.setInt     (1, id);
            stmt.setInt     (2, id_profissional);
            stmt.setInt    (3, id_plano);
            stmt.setInt     (4, qtde_creditos_restantes);
            stmt.setDate    (5, data_ativacao_plano);
            stmt.setDate    (6, data_inicial);
            stmt.setDate    (6, data_final);
            stmt.setBoolean    (6, status);


            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, "nao conseguiu fazer insert", Toast.LENGTH_LONG).show();

        }

    }
    public void remove(Plano_Profissional plano_profissional) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = plano_profissional.getId();
        int id_profissional = plano_profissional.getId_profissional();
        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM CLIENTE WHERE ID="+id+" AND ID_PESSOA="+id_profissional );

            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Plano_Profissional plano_profissional) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = plano_profissional.getId();
        int id_profissional = plano_profissional.getId_profissional();
        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("UPDATE CLIENTE SET ID_PESSOA="+id_profissional+" WHERE id="+id );

            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
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

    /***
     * Log
     * @return texto armazenado no log.
     */
    public String getLog()
    {
        return log.toString();
    }

    /***
     * Conecta ao servidor através do mysql connector.
     * @return estado da conexão.
     */
    public boolean connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            //   log.add("Conectado com sucesso!");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return isConected();
    }

    /***
     * Disconecta do servidor
     */
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

    /***
     * O método onPreExecute é chamado antes do método execute() de AsyncTask
     * Neste caso utilizamos para abrir um dialogo avisando o usuário
     * de que está sendo realizada uma conexão com o database
     */
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
        connect();
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

