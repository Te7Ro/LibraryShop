package com.example.library;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LibraryController {
    @FXML
    private ListView<LibraryItem> listView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private TextField moneyTextField;
    @FXML
    private MultipleSelectionModel<LibraryItem> selectionModel;
    @FXML
    private AnchorPane libraryAnchor;
    @FXML
    private AnchorPane menuAnchor;
    @FXML
    private TextField idTextField;
    @FXML
    protected void idTextFieldTyped(){
        idTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    idTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    @FXML
    protected void moneyTextFieldTyped(){
        moneyTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    moneyTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    @FXML
    protected void showBooks(Connection connection) throws Exception{
        String query = "SELECT * FROM books;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<Book> books = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("book_id");
            books.add(new Book(id,connection));
        }
        ObservableList<LibraryItem> observableList = FXCollections.observableArrayList(books);
        listView.setItems(observableList);
    }
    @FXML
    protected void showMoney(Client client) throws Exception{
        String s = String.format("You have %d tenge",client.getMoney());
        moneyLabel.setText(s);
    }
    @FXML
    protected void showClient(Client client){
        String s = String.format("Welcome %s",client.getName());
        nameLabel.setText(s);
    }
    @FXML
    protected void setSelectionModel(){
        selectionModel = listView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
    }
    @FXML
    protected void insertMoneyClicked() throws Exception{
        int money = Integer.parseInt(moneyTextField.getText());
        LibraryApplication.client.insertMoney(money, LibraryApplication.connection);
        moneyTextField.setText("0");
        showMoney(LibraryApplication.client);
    }
    @FXML
    protected void buyMoneyClicked() throws Exception{
        Basket basket = new Basket();
        ObservableList<LibraryItem> books = listView.getSelectionModel().getSelectedItems();
        for(LibraryItem book : books){
            System.out.println(book);
            basket.addItem(book);
        }

        PurchaseRequest request = new PurchaseRequest(basket,LibraryApplication.client,LibraryApplication.connection);

        PurchaseHandler handler = new QuantityCheckHandler(new AffordabilityCheckHandler(new InsertIntoPurchasesHandler(null)));

        String result = handler.handleRequest(request);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(result);
        alert.show();

        showMoney(LibraryApplication.client);
        showBooks(LibraryApplication.connection);
    }
    @FXML
    protected void enterClicked() throws Exception{
        LibraryApplication.client = new Client(Integer.parseInt(idTextField.getText()), LibraryApplication.connection);
        setSelectionModel();
        showBooks(LibraryApplication.connection);
        showClient(LibraryApplication.client);
        showMoney(LibraryApplication.client);
        libraryAnchor.setVisible(true);
        menuAnchor.setVisible(false);
    }

}
