package com.example.library;

import java.sql.Connection;

public class PurchaseRequest {
    private LibraryItem libraryItem;
    private Client client;
    private Connection connection;
    public PurchaseRequest(LibraryItem libraryItem, Client client, Connection connection){
        this.libraryItem = libraryItem;
        this.client = client;
        this.connection = connection;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    public Client getClient() {
        return client;
    }
    public Connection getConnection(){return connection;}
}

