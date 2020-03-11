package inventory.controller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Main implements Initializable {

    @FXML
    private TextField searchPartTextField;

    @FXML
    private Button searchPartBtn;

    @FXML
    private TableColumn<?, ?> partIDCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> partInvCol;

    @FXML
    private TableColumn<?, ?> partCostCol;

    @FXML
    private Button partAddBtn;

    @FXML
    private Button partModBtn;

    @FXML
    private Button partDelBtn;

    @FXML
    private TextField searchProdTextField;

    @FXML
    private Button searchProdBtn;

    @FXML
    private TableColumn<?, ?> prodIDCol;

    @FXML
    private TableColumn<?, ?> prodNameCol;

    @FXML
    private TableColumn<?, ?> prodInvCol;

    @FXML
    private TableColumn<?, ?> prodCostCol;

    @FXML
    private Button prodAddBtn;

    @FXML
    private Button prodModBtn;

    @FXML
    private Button prodDelBtn;

    @FXML
    private Button exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

