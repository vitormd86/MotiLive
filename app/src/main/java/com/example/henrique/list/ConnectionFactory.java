package com.example.henrique.list;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// implementando a classe e extendendo de AsynTask
public  class ConnectionFactory extends AsyncTask<Void, Void, Boolean>  {

    /***
     * Definindo as variáveis endereço do servidor, usuário e senha
     * Url: jdbc:mysql://<ip>:<porta>/<database>";
     */
    private static final String url = "jdbc:mysql://192.169.198.138:3306/motilive";
    private static final String user = "root";
    private static final String pass = "4linux";
    private static Connection  con; 		// armazena a conexão atual
    private Log log; 				// gera log
    private Context context;		// armazena o conexto da activity que instanciou a conexão mysql para utilização com dialogs da Ui
    private ProgressDialog dialog; 	// dialog para mostrar o progresso da conexão

    /***
     * Recebe o contexto da activity que instanciou a conexão. Este contexto é necessário para
     * exibir dialogos como "Aguarde.. conectando, Aguarde.. executando consulta, etc".
     * @param context
     */
    public ConnectionFactory(Context context)
    {
        this.context = context;
        //  log = new Log();
    }

    /***
     * Função para verificar o estado da conexão. Retorna true para conexão aberta.
     * @return estado da conexão
     */
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
        } catch (SQLException e) {
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

    /***
     * Disconecta do servidor
     */
    public void disconnect()
    {
        try {
            con.close();
            con.isClosed();
            //  log.add("Desconectado!");
        } catch (SQLException e) {
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