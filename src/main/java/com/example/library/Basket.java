package com.example.library;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
public class Basket implements LibraryItem{
    private List<LibraryItem> books;
    public Basket(){
        this.books = new ArrayList<>();
    }
    public void addItem(LibraryItem book){
        books.add(book);
    }

    public boolean getQuantity() {
        boolean result = true;
        for(LibraryItem book : books){
            if(!book.getQuantity()) result = false;
        }
        return result;
    }

    public int getPrice(){
        int sum = 0;
        for(LibraryItem book : books){
            sum += book.getPrice();
        }
        return sum;
    }
    public void buy(Client client, Connection connection) throws Exception{
        for(LibraryItem book : books){
            book.buy(client, connection);
        }
    }
}

