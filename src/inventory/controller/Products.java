package inventory.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Products implements Initializable {

    @FXML
    private Label productScreenLabel;

    @FXML
    private TextField prodIDTextField;

    @FXML
    private TextField prodNameTextField;

    @FXML
    private TextField prodInvTextField;

    @FXML
    private TextField prodCostTextField;

    @FXML
    private TextField prodMinTextField;

    @FXML
    private TextField prodMaxTextField;

    @FXML
    private TextField partSearchTextField;

    @FXML
    private Button partSearchButton;

    @FXML
    private TableView<?> AddPartsTableView;

    @FXML
    private Button AddPartButton;

    @FXML
    private TableView<?> ProductPartsTableView;

    @FXML
    private Button RemovePartButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void initScreenLabel(String labelText) {
        productScreenLabel.setText(labelText);
    }
}
