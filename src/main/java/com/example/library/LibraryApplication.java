package com.example.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.sql.Connection;

public class LibraryApplication extends Application {
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
