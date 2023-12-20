package com.example.library;

import java.sql.Connection;

public interface LibraryItem {
    int getPrice();
    boolean getQuantity();
    void buy(Client client, Connection connection) throws Exception;

}

