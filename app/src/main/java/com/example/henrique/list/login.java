package com.example.henrique.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Henrique on 15/02/2015.
 */
public class login extends Activity {

    private static final String DB_URL = "jdbc:mysql://192.169.198.138:3306/MotiLive";
    private static final String USER = "root";
    private static final String PASS = "4MotiLive";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String loginSelecionado;
    private String senhaSelecionado;
    EditText login = (EditText) findViewById(R.id.LoginET);
    EditText senha = (EditText) findViewById(R.id.senhaET);
    Button  botao = (Button) findViewById(R.id.loginBTN);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);


        testDB();

    }

    private void testDB() {



        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT login, senha FROM pessoa where login = "+ login.getText().toString();
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name

                loginSelecionado = rs.getString("login");
                senhaSelecionado = rs.getString("senha");

            }
            rs.close();
            stmt.close();
            conn.close();
            if (senhaSelecionado.equals(senha.getText().toString())){
                //entao loga e depois
                Toast.makeText(this, "ta funcionando", Toast.LENGTH_LONG);
            }else{
                Toast.makeText(this,"Nao ta funcionando", Toast.LENGTH_SHORT);
            }
            //STEP 6: Clean-up environment

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
