package com.example.library;

import java.sql.*;

public class Book implements LibraryItem{
    private int id;
    private String name;
    private String author;
    private int price;
    private int quantity;
    public Book(int id, Connection connection) throws Exception{
        this.id=id;
        String query = "SELECT * FROM Books WHERE book_id="+id+";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            name = rs.getString("book_name");
            author = rs.getString("author");
            price = rs.getInt("price");
            quantity = rs.getInt("quantity");
        }
    }
    public int getPrice(){
        return price;
    }
    public boolean getQuantity() {
        return quantity>0;
    }

    public void buy(Client client, Connection connection) throws Exception{
        String query = "UPDATE books SET quantity="+(quantity-1)+" WHERE book_id="+id+";";
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        client.insertMoney(-getPrice(),connection);
        System.out.println("You bought book: "+this.name+" of the author: "+this.author);
    }

    public String toString() {
        return String.format("%-3d| %-40s| %-25s| %-6d",id,name,author,price);
    }
}