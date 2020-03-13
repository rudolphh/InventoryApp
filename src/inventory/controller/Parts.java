package inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Parts implements Initializable {

    @FXML
    private Label partScreenLabel;

    @FXML
    private RadioButton inHouseRadioBtn;

    @FXML
    private ToggleGroup toggleGroup1;

    @FXML
    private RadioButton outsourcedRadioBtn;

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
    private Label partMacCoLabel;

    @FXML
    private TextField partMacCoTextField;

    @FXML
    private Button partSaveBtn;

    @FXML
    private Button partCancelBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initScreenLabel(String labelText) {
        partScreenLabel.setText(labelText);
    }

    public void selectInHouse(ActionEvent actionEvent) {
        partMacCoLabel.setText("Machine ID");
        partMacCoTextField.setPromptText("Machine Identifier");
    }


    public void selectOutsourced(ActionEvent actionEvent) {
        partMacCoLabel.setText("Company");
        partMacCoTextField.setPromptText("Name of Company");
    }

    public void clickSavePart(ActionEvent actionEvent) {

        // extract text from fields
        String partName = partNameTextField.getText();
        String partInv = partInvTextField.getText().isEmpty() ? "0" : partInvTextField.getText();
        String partCost = partCostTextField.getText();
        String partMin = partMinTextField.getText();
        String partMax = partMaxTextField.getText();
        String partMacCo = partMacCoTextField.getText();


    }

    public void clickCancelPart(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Cancel " + partScreenLabel.getText());
        alert.setHeaderText("Confirm cancel");
        alert.setContentText("Are you sure you want to cancel?\n\n");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.close();
        }
    }
}

