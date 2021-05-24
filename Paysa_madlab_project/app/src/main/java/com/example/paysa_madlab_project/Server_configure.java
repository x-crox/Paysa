package com.example.paysa_madlab_project;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server_configure extends AsyncTask<String,Void,String> {

    private String server_url;
    private String auth_username;
    private String auth_pass;
    static Connection conn=null;

    public Server_configure(String url,String auth_username,String auth_pass){
        this.server_url=url;
        this.auth_username=auth_username;
        this.auth_pass=auth_pass;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(server_url,auth_username,auth_pass);
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        return null;
    }
}
