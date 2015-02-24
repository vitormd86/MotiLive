package com.example.henrique.list.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.henrique.list.Bean.Pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;


public class PessoaDAO extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private static final String url = "jdbc:mysql://192.169.198.138:3306/motilive";
    private static final String user = "root";
    private static final String pass = "4linux";
    private static Connection con;        // armazena a conexão atual
    private Log log;                // gera log
    private ProgressDialog dialog;    // dialog para mostrar o progresso da conexão


    public PessoaDAO(Context context) throws SQLException {

        this.context = context;

    }

    public void adiciona(Pessoa pessoa) throws SQLException, java.sql.SQLException {
// prepared statement para inserção

        int id = pessoa.getId();
        String nome = pessoa.getNome();
        String cpf_cnpj = pessoa.getCPF_CNPJ();
        String endereco = pessoa.getEndereco();
        String numero = pessoa.getNumero();
        String complemento = pessoa.getComplemento();
        String bairro = pessoa.getBairro();
        String cidade = pessoa.getCidade();
        String estado = pessoa.getEstado();
        String CEP = pessoa.getCEP();
        Date data_nascimento = pessoa.getData_nascimento();
        String email = pessoa.getEmail();
        String ddd = pessoa.getDDD();
        String telefone = pessoa.getTelefone();
        String sexo = pessoa.getSexo();
        Date dt_atualizacao = pessoa.getDt_atualizacao();
        String status = pessoa.getStatus();
        String login = pessoa.getLogin();
        String senha = pessoa.getSenha();
        String facebook_login = pessoa.getFacebook_login();
        String google_login = pessoa.getGoogle_login();


        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO PESSOA" + "(ID,NOME,CPF_CNPJ, ENDERECO,NUMERO,COMPLEMENTO,BAIRRO,CIDADE,ESTADO,CEP,DATA_NASCIMENTO,EMAIL," +
                    "DDD,TELEFONE,SEXO,DT_ATUALIZACAO,STATUS,LOGIN,SENHA,FACEBOOK_LOGIN,GOOGLE_LOGIN)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, cpf_cnpj);
            stmt.setString(4, endereco);
            stmt.setString(5, numero);
            stmt.setString(6, complemento);
            stmt.setString(7, bairro);
            stmt.setString(8, cidade);
            stmt.setString(9, estado);
            stmt.setString(10, CEP);
            stmt.setDate(11, data_nascimento);
            stmt.setString(12, email);
            stmt.setString(13, ddd);
            stmt.setString(14, telefone);
            stmt.setString(15, sexo);
            stmt.setDate(16, dt_atualizacao);
            stmt.setString(17, status);
            stmt.setString(18, login);
            stmt.setString(19, senha);
            stmt.setString(20, facebook_login);
            stmt.setString(20, google_login);


            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();

        }
        Toast.makeText(context, "nao conseguiu fazer insert", Toast.LENGTH_LONG).show();


    }

    public void remove(Pessoa pessoa) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = pessoa.getId();
        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM CLIENTE WHERE ID=" + id + " AND ID_PESSOA=");

            stmt.execute();
            stmt.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Pessoa pessoa) throws SQLException, java.sql.SQLException {
// prepared statement para inserção
        int id = pessoa.getId();
        try {

            PreparedStatement stmt;
            stmt = con.prepareStatement("UPDATE CLIENTE SET ID_PESSOA=" +  " WHERE id=" + id);

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

