package com.example.library;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;

public class LibraryApplication extends Application {
    public static int id;
    public static Client client;
    public static Connection connection;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("library.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);

        Database database = Database.getInstance();
        connection = database.getConnection();

        stage.setTitle("Library");
        stage.setScene(scene);
        stage.show();

    }
}
