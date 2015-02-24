package com.example.henrique.list.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.henrique.list.Bean.Agenda_Diaria;
import com.example.henrique.list.Bean.Agendamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Time;


public class AgendamentoDAO extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private static final String url = "jdbc:mysql://192.169.198.138:3306/motilive";
    private static final String user = "root";
    private static final String pass = "4linux";
    private static Connection con;        // armazena a conexão atual
    private Log log;                // gera log
    private ProgressDialog dialog;    // dialog para mostrar o progresso da conexão


    public AgendamentoDAO(Context context) throws SQLException {

        this.context = context;

    }

    public void adiciona(Agendamento agendamento) throws SQLException, java.sql.SQLException {
// prepared statement para inserção

        int id = agendamento.getId();
        int id_agenda_diaria = agendamento.getId_agenda_diaria();
        int id_servico = agendamento.getId_servico();
        int id_cliente = agendamento.getId_cliente();
        Time hora_entrada = agendamento.getHora_entrada();
        Time hora_saida = agendamento.getHora_saida();


        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO AGENDAMENTO (ID_AGENDA_DIARIA,ID_SERVICO,ID_CLIENTE, ID,HORA_ENTRADA,HORA_SAIDA) " + " VALUES(?,?,?,?,?,?)");

            stmt.setInt(1, id_agenda_diaria);
            stmt.setInt(2, id_servico);
            stmt.setInt(3, id_cliente);
             stmt.setInt(4, id);
            stmt.setTime(5, hora_entrada);
            stmt.setTime(6, hora_saida);

            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();

        }
        Toast.makeText(context, "nao conseguiu fazer insert", Toast.LENGTH_LONG).show();


    }

    public void remove(Agenda_Diaria agenda_diaria) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = agenda_diaria.getId();
        int id_profissional = agenda_diaria.getId_profissional();
        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM CLIENTE WHERE ID=" + id + " AND ID_PESSOA=" + id_profissional);

            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Agenda_Diaria agenda_diaria) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = agenda_diaria.getId();
        int id_profissional = agenda_diaria.getId_profissional();
        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("UPDATE CLIENTE SET ID_PESSOA=" + id_profissional + " WHERE id=" + id);

            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    public Boolean isConected() {
        try {
            if (con == null) {
                return false;
            } else {
                return (!this.con.isClosed());
            }
        } catch (java.sql.SQLException e) {
            return false;
        }
    }

    /**
     * Log
     *
     * @return texto armazenado no log.
     */
    public String getLog() {
        return log.toString();
    }

    /**
     * Conecta ao servidor através do mysql connector.
     *
     * @return estado da conexão.
     */
    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            //   log.add("Conectado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConected();
    }

    /**
     * Disconecta do servidor
     */
    public void disconnect() {
        try {
            con.close();
            con.isClosed();
            //  log.add("Desconectado!");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
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

    /**
     * O médoto doInBackground é chamado através do método run() de AsyncTask
     * Ele roda após o método onPreExecute
     * É aqui que vamos realizar a conexão com o database.
     */
    @Override
    protected Boolean doInBackground(Void... params) {
        connect();
        return isConected();
    }

    /**
     * O médoto onPostExecute é chamado após a execução do método doInBackground()
     * Aqui vamos fechar a janela de diálogo da conexão com o database
     */
    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        dialog.dismiss();
    }
}

