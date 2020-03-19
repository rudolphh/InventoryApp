package inventory.controller;

import inventory.model.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Parts implements Initializable {

    @FXML
    private Label partScreenLabel;

    @FXML
    private RadioButton inHouseRadioBtn;

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

            partIDTextField.setText(Integer.toString(selectedPart.getId()));
            partNameTextField.setText(selectedPart.getName());
            partInvTextField.setText(Integer.toString(selectedPart.getInventory()));
            partCostTextField.setText(Double.toString(selectedPart.getPrice()));
            partMinTextField.setText(Integer.toString(selectedPart.getMin()));
            partMaxTextField.setText(Integer.toString(selectedPart.getMax()));

            if(selectedPart instanceof InHousePart){
                inHouseRadioBtn.setSelected(true);
                partMacCoLabel.setText("Machine ID");
                partMacCoTextField.setPromptText("Machine Identifier");
                partMacCoTextField.setText(Integer.toString(((InHousePart) selectedPart).getMachineID()));
            } else if (selectedPart instanceof OutsourcedPart){
                outsourcedRadioBtn.setSelected(true);
                partMacCoLabel.setText("Company");
                partMacCoTextField.setPromptText("Company Name");
                partMacCoTextField.setText(((OutsourcedPart) selectedPart).getCompanyName());
            }
        }
    }

    /***
     * Save a part to the inventory (added or modified).
     * @param thePart the part to be added or modified
     */
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
        double partCost = Double.parseDouble(partCostTextField.getText().isEmpty() ? "0" : partCostTextField.getText());
        int partInv = Integer.parseInt(partInvTextField.getText().isEmpty() ? "0" : partInvTextField.getText());
        int partMin = Integer.parseInt(partMinTextField.getText().isEmpty() ? "0" : partMinTextField.getText());
        int partMax = Integer.parseInt(partMaxTextField.getText().isEmpty() ? "0" : partMaxTextField.getText());
        String partMacCo = partMacCoTextField.getText();// machine IDs can only be 9 digits (limitation of int)

        Part thePart;

        // better input validation can go here
            if(inHouseRadioBtn.isSelected()){
                thePart = new InHousePart(id, partName, partCost, partInv, partMin, partMax,
                        Integer.parseInt(partMacCoTextField.getText().isEmpty() ? "0" : partMacCo.trim()));
            } else {
                thePart = new OutsourcedPart(id, partName, partCost, partInv, partMin, partMax, partMacCo.trim());
            }
            savePart(thePart);
            Main.closeThisWindow(actionEvent);
        // else { // validation failed dialog box }

    }

    public void clickCancelPart(ActionEvent actionEvent) {
        Optional<ButtonType> result = Main.dialog(Alert.AlertType.CONFIRMATION,
                "Cancel " + partScreenLabel.getText(), "Confirm cancel",
                "Are you sure you want to cancel?\n\n");

        if (result.isPresent() && result.get() == ButtonType.OK)
            Main.closeThisWindow(actionEvent);
    }

    public void selectInHouse(ActionEvent actionEvent) {
        partMacCoLabel.setText("Machine ID");
        partMacCoTextField.setPromptText("Machine Identifier");
    }

    public void selectOutsourced(ActionEvent actionEvent) {
        partMacCoLabel.setText("Company");
        partMacCoTextField.setPromptText("Name of Company");
    }

//  controller methods for getting data from the outside

    /***
     *
     * @param labelText the text to change the screens main label
     */
    void initScreenLabel(String labelText) {
        partScreenLabel.setText(labelText);
    }

    /***
     * Pass the part selected from the main controller to parts controller
     * @param thePart copy of the part selected from the main screen controller
     */
    void setPart(Part thePart) {
        this.selectedPart = thePart;
    }
}// end Parts.java

