package com.example.paysa_madlab_project;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Server_configure {

    private String server_url;
    private String auth_username;
    private String auth_pass;
    static Connection conn=null;

    public Server_configure(String url,String auth_username,String auth_pass){
        this.server_url=url;
        this.auth_username=auth_username;
        this.auth_pass=auth_pass;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(server_url,auth_username,auth_pass);
            System.out.println("connected...\n");
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch (Exception e){}
    }
    public static ResultSet execute_query(String query){
        ResultSet rs=null;
        try{
            Statement statement=conn.createStatement();
            rs=statement.executeQuery(query);
            ResultSetMetaData rsmd=rs.getMetaData();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return rs;
    }

}
