package inventory.controller;

import inventory.model.InHousePart;
import inventory.model.Inventory;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    private final String machineIDLabel = "Machine ID";
    private final String machineIDPrompt = "Machine Identifier";
    private final String companyNameLabel = "Company";
    private final String companyNamePrompt = "Company Name";

    @FXML
    private TextField partMacCoTextField;

    @FXML
    private Button partSaveBtn;

    @FXML
    private Button partCancelBtn;

    @FXML
    private int selectedRowIndex = -1;


//////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partSaveBtn.setDefaultButton(true);// fire button when 'enter' is pressed
        partCancelBtn.setCancelButton(true);// fire button when 'esc' is pressed
    }

    private void initializeFieldData(){

        if (selectedRowIndex == -1) {
            partIDTextField.setText(Integer.toString(Inventory.totalParts()+1));
        } else {
            Part modPart = Inventory.getAllParts().get(selectedRowIndex);
            partIDTextField.setText(Integer.toString(modPart.getId()));
            partNameTextField.setText(modPart.getName());
            partInvTextField.setText(Integer.toString(modPart.getInventory()));
            partCostTextField.setText(Double.toString(modPart.getPrice()));
            partMinTextField.setText(Integer.toString(modPart.getMin()));
            partMaxTextField.setText(Integer.toString(modPart.getMax()));

            if(modPart instanceof InHousePart){
                inHouseRadioBtn.setSelected(true);
                partMacCoLabel.setText(machineIDLabel);
                partMacCoTextField.setPromptText(machineIDPrompt);
                partMacCoTextField.setText(Integer.toString(((InHousePart) modPart).getMachineID()));
            } else if (modPart instanceof OutsourcedPart){
                outsourcedRadioBtn.setSelected(true);
                partMacCoLabel.setText(companyNameLabel);
                partMacCoTextField.setPromptText(companyNamePrompt);
                partMacCoTextField.setText(((OutsourcedPart) modPart).getCompanyName());
            }
        }
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

        boolean newPart = selectedRowIndex == -1;

        // extract text from fields
        int id = newPart ? Inventory.getAllParts().size()+1 : Integer.parseInt(partIDTextField.getText());

        String partName = partNameTextField.getText();
        double partCost = Double.parseDouble(partCostTextField.getText());
        int partInv = partInvTextField.getText().isEmpty() ? 0 : Integer.parseInt(partInvTextField.getText());
        int partMin = Integer.parseInt(partMinTextField.getText());
        int partMax = Integer.parseInt(partMaxTextField.getText());
        String partMacCo = partMacCoTextField.getText();// machine IDs can only be 9 digits (limitation of int)

        if(inHouseRadioBtn.isSelected()){

            if(true) { // do validation
                if (newPart) { // create a new part
                    Inventory.addPart(new InHousePart(id, partName, partCost, partInv, partMin, partMax,
                            Integer.parseInt(partMacCo.trim())));
                } else { // save the modified part
                    Inventory.updatePart(selectedRowIndex, new InHousePart(id, partName, partCost, partInv, partMin, partMax,
                            Integer.parseInt(partMacCo.trim())));
                }
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.close();
            } else { // dialog box

            }

        } else if (outsourcedRadioBtn.isSelected()){

            if(true) { // do validation
                Inventory.addPart(new OutsourcedPart(id, partName, partCost, partInv, partMin, partMax, partMacCo));
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.close();
            }
        }


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


//  controller methods for getting data from the outside
    void initScreenLabel(String labelText) {
        partScreenLabel.setText(labelText);
    }

    void setInventoryIndex(int selectedRowIndex) {
        this.selectedRowIndex = selectedRowIndex;
        initializeFieldData();// initialize field data after
    }


}// end Parts.java

