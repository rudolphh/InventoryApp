package inventory.controller;

import inventory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Products implements Initializable {

    @FXML
    private Label prodScreenLabel;

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
    private TextField searchPartTextField;

    @FXML
    private Button searchPartBtn;

    @FXML
    private TableView<Part> addPartsTableView;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partCostCol;

    @FXML
    private Button addPartBtn;

    @FXML
    private TableView<Part> productPartsTableView;

    @FXML
    private TableColumn<Part, Integer> prodPartIDCol;

    @FXML
    private TableColumn<Part, String> prodPartNameCol;

    @FXML
    private TableColumn<Part, Integer> prodPartInvCol;

    @FXML
    private TableColumn<Part, Double> prodPartCostCol;

    @FXML
    private Button removePartBtn;

    private FilteredList<Part> partFilteredList;

    private ObservableList<Part> productParts = FXCollections.observableArrayList();

    private Product selectedProduct = null;
    private int selectedProdInventoryIndex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.initializeColumns(partIDCol, partNameCol, partInvCol, partCostCol);
        Main.initializeColumns(prodPartIDCol, prodPartNameCol, prodPartInvCol, prodPartCostCol);

        ObservableList<Part> allParts = Inventory.getAllParts();
        addPartsTableView.setItems(allParts);
        
        productPartsTableView.setItems(productParts);

        // set up search functionality
        partFilteredList = allParts.filtered(b-> true);
        SortedList<Part> sortedPartList = new SortedList<>(partFilteredList);
        sortedPartList.comparatorProperty().bind(addPartsTableView.comparatorProperty());
        addPartsTableView.setItems(sortedPartList);

        // set up search textField handlers (enter key)
        searchPartTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                searchPart();
            }
        });
    }

    private void searchPart() {

        String newValue = searchPartTextField.getText();

        partFilteredList.setPredicate(part -> {

            if(newValue == null || newValue.isEmpty()){
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();
            if(part.getName().toLowerCase().contains(lowerCaseFilter)){
                return true;
            } else return String.valueOf(part.getId()).contains(lowerCaseFilter);
        });
    }

    public void clickSearchPart(ActionEvent actionEvent) {
        searchPart();
    }

    public void clickAddPart(ActionEvent actionEvent) {

        // get the selected part
        Part thePart = addPartsTableView.getSelectionModel().getSelectedItem();

        if(thePart == null){
            Main.dialog(Alert.AlertType.INFORMATION, "Select Part", "No part selected",
                    "You must select a part to add to the product");
        } else {

            if(productParts.indexOf(thePart) == -1) { // if the part isn't already associated
                productParts.add(thePart); // add it to the product parts list
            } else {
                Main.dialog(Alert.AlertType.INFORMATION, "Duplicate Part", "Duplicate part selected",
                        "This part is already associated with the product");
            }
        }
    }

    public void clickRemovePart(ActionEvent actionEvent) {
        // get the selected part
        Part thePart = productPartsTableView.getSelectionModel().getSelectedItem();

        if(thePart == null){
            Main.dialog(Alert.AlertType.INFORMATION, "Select Part", "No part selected",
                    "You must select a part to remove from the product");
        } else {
            productParts.remove(thePart);
        }

    }

    private void saveProduct(Product theProduct){
        boolean newProduct = selectedProduct == null;
        if (newProduct) { // create a new part
            Inventory.addProduct(theProduct);
        } else { // save the modified part
            Inventory.updateProduct(selectedProdInventoryIndex, theProduct);
        }
    }

    public void clickSaveProd(ActionEvent actionEvent) {
        // extract text from fields
        int id = Integer.parseInt(prodIDTextField.getText());

        String prodName = prodNameTextField.getText();
        double prodCost = Double.parseDouble(prodCostTextField.getText().isEmpty() ? "0" : prodCostTextField.getText());
        int prodInv = Integer.parseInt(prodInvTextField.getText().isEmpty() ? "0" : prodInvTextField.getText());
        int prodMin = Integer.parseInt(prodMinTextField.getText().isEmpty() ? "0" : prodMinTextField.getText());
        int prodMax = Integer.parseInt(prodMaxTextField.getText().isEmpty() ? "0" : prodMaxTextField.getText());

        Product theProduct;
        if(true && productParts.size() > 0){ // all input is valid and product has at least one associated part

            theProduct = new Product(id, prodName, prodCost, prodInv, prodMin, prodMax);
            for (Part p : productParts){
                theProduct.addAssociatedPart(p);
            }
            saveProduct(theProduct);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.close();
        } else { // failed validation dialog box
            Main.dialog(Alert.AlertType.INFORMATION, "No associated parts",
                    "A product must have at least one part",
                    "Select at least one part to associate with a product");
        }
    }

    public void clickCancelProd(ActionEvent actionEvent) {
        Optional<ButtonType> result = Main.dialog(Alert.AlertType.CONFIRMATION,
                "Cancel " + prodScreenLabel.getText(), "Confirm cancel",
                "Are you sure you want to cancel?\n\n");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.close();
        }
    }


    // for setting up controller before window loaded
    void initScreenLabel(String labelText) {
        prodScreenLabel.setText(labelText);
    }

    void setProduct(Product theProduct) {
        this.selectedProduct = theProduct;
    }

    void initializeFieldData() {
        
        if(selectedProduct == null){
            prodIDTextField.setText(String.valueOf(Inventory.getCurrentProductID()));
        } else {
            ObservableList<Product> theList = Inventory.getAllProducts();
            this.selectedProdInventoryIndex = theList.indexOf(selectedProduct);

            prodIDTextField.setText(Integer.toString(selectedProduct.getId()));
            prodNameTextField.setText(selectedProduct.getName());
            prodInvTextField.setText(Integer.toString(selectedProduct.getInventory()));
            prodCostTextField.setText(Double.toString(selectedProduct.getPrice()));
            prodMinTextField.setText(Integer.toString(selectedProduct.getMin()));
            prodMaxTextField.setText(Integer.toString(selectedProduct.getMax()));

            productParts = selectedProduct.getAllAssociatedParts();
            productPartsTableView.setItems(productParts);
        }
    }
}
