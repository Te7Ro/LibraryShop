package com.example.library;

import java.sql.*;

public class Client {
    private int id;
    private String name;
    private int money;
    public Client(int id, Connection connection) throws Exception{
        this.id = id;
        String query = "SELECT * FROM Clients WHERE client_id="+id+";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            name = rs.getString("client_name");
            money = rs.getInt("money");
        }
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void insertMoney(int money, Connection connection) throws Exception{
        this.money += money;
        String query = "UPDATE clients SET money="+this.money+" WHERE client_id="+id+";";
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }
}

