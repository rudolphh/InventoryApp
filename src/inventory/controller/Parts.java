package inventory.controller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Parts implements Initializable {

    @FXML
    private RadioButton inHouseRadioBtn;

    @FXML
    private ToggleGroup toggleGroup1;

    @FXML
    private RadioButton outsourcedRadioBrn;

    @FXML
    private TextField partIDTextField;

    @FXML
    private TextField partNameTextField;

    @FXML
    private TextField partInvTextField;

    @FXML
    private TextField partCostTextField;

    @FXML
    private TextField partMinTextField;

    @FXML
    private TextField partMaxTextField;

    @FXML
    private TextField partMacIDTextField;

    @FXML
    private Button partSaveBtn;

    @FXML
    private Button partCancelBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

