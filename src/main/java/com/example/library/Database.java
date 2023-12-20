package com.example.library;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Database instance = null;
    private final Connection connection;

    private Database() throws Exception{
        String url = "jdbc:postgresql://localhost:5432/library";
        String user = "postgres";
        String password = "12345";
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(url,user,password);
    }

    public static Database getInstance() throws Exception {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
}

