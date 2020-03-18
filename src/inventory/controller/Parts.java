package inventory.controller;

import inventory.model.InHousePart;
import inventory.model.Inventory;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import javafx.collections.ObservableList;
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
    private Part selectedPart = null;
    private int selectedPartInventoryIndex;

    //////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partSaveBtn.setDefaultButton(true);// fire button when 'enter' is pressed
        partCancelBtn.setCancelButton(true);// fire button when 'esc' is pressed
    }

    void initializeFieldData(){

        if (selectedPart == null) {
            partIDTextField.setText(Integer.toString(Inventory.getCurrentPartID()));
        } else {
            ObservableList<Part> theList = Inventory.getAllParts();
            this.selectedPartInventoryIndex = theList.indexOf(selectedPart);
            Part modPart = theList.get(this.selectedPartInventoryIndex);
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


    private void savePart(Part thePart){
        boolean newPart = selectedPart == null;
        if (newPart) { // create a new part
            Inventory.addPart(thePart);
        } else { // save the modified part
            Inventory.updatePart(selectedPartInventoryIndex, thePart);
        }
    }

    public void clickSavePart(ActionEvent actionEvent) {

        // extract text from fields
        int id = Integer.parseInt(partIDTextField.getText());

        String partName = partNameTextField.getText();
        double partCost = Double.parseDouble(partCostTextField.getText());
        int partInv = partInvTextField.getText().isEmpty() ? 0 : Integer.parseInt(partInvTextField.getText());
        int partMin = Integer.parseInt(partMinTextField.getText());
        int partMax = Integer.parseInt(partMaxTextField.getText());
        String partMacCo = partMacCoTextField.getText();// machine IDs can only be 9 digits (limitation of int)

        Part thePart;
        if(true){ // all input is valid
            if(inHouseRadioBtn.isSelected()){
                thePart = new InHousePart(id, partName, partCost, partInv, partMin, partMax, Integer.parseInt(partMacCo.trim()));
            } else {
                thePart = new OutsourcedPart(id, partName, partCost, partInv, partMin, partMax, partMacCo.trim());
            }
            savePart(thePart);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.close();
        } else { //dialog box

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

    void setPart(Part thePart) {
        this.selectedPart = thePart;
    }
}// end Parts.java

