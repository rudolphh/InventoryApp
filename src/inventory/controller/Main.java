package inventory.controller;

import inventory.model.InHousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.model.Product;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main implements Initializable {

    @FXML
    private TextField searchPartTextField;

    @FXML
    private Button searchPartBtn;

    @FXML
    private TableView<Part> partsListTableView;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partCostCol;

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
    private TableView<Product> productsListTableView;

    @FXML
    private TableColumn<Product, Integer> prodIDCol;

    @FXML
    private TableColumn<Product, String> prodNameCol;

    @FXML
    private TableColumn<Product, Integer> prodInvCol;

    @FXML
    private TableColumn<Product, Double> prodCostCol;

    @FXML
    private Button prodAddBtn;

    @FXML
    private Button prodModBtn;

    @FXML
    private Button prodDelBtn;

    @FXML
    private Button exitBtn;

    private FilteredList<Part> partFilteredList;
    private FilteredList<Product> prodFilteredList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Inventory.addPart(new InHousePart(1, "Transmission", 299.18, 10, 1, 20, 20383));
        Inventory.addPart(new InHousePart(2, "brakes", 99.03, 5, 3, 30, 205653));
        Inventory.addPart(new InHousePart(3, "tire", 69.85, 20, 8, 40, 1849393));

        initializeColumns(partIDCol, partNameCol, partInvCol, partCostCol);
        initializeColumns(prodIDCol, prodNameCol, prodInvCol, prodCostCol);


        ObservableList<Part> allParts = Inventory.getAllParts();
        partsListTableView.setItems(allParts);

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        productsListTableView.setItems(allProducts);


        // set up search functionality
        partFilteredList = allParts.filtered(b-> true);
        SortedList<Part> sortedPartList = new SortedList<>(partFilteredList);
        sortedPartList.comparatorProperty().bind(partsListTableView.comparatorProperty());
        partsListTableView.setItems(sortedPartList);

        prodFilteredList = allProducts.filtered(b-> true);
        SortedList<Product> sortedProdList = new SortedList<>(prodFilteredList);
        sortedProdList.comparatorProperty().bind(productsListTableView.comparatorProperty());
        productsListTableView.setItems(sortedProdList);

        // set up search textField handlers (enter key)
        searchPartTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                searchPart();
            }
        });

        searchProdTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                searchProduct();
            }
        });

        exitBtn.setCancelButton(true);
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

    private void searchProduct() {
        String newValue = searchProdTextField.getText();

        prodFilteredList.setPredicate(part -> {

            if(newValue == null || newValue.isEmpty()){
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();
            if(part.getName().toLowerCase().contains(lowerCaseFilter)){
                return true;
            } else return String.valueOf(part.getId()).contains(lowerCaseFilter);
        });
    }

// Button Click Handlers for Main Screen (main.fxml)

    public void loadScreen(ActionEvent actionEvent, String screen) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(screen));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    // Click Handlers for Part Buttons on Main Screen
    public void clickSearchPart(ActionEvent actionEvent) {
        searchPart();
    }

    public void loadPartScreen(Part thePart, String title, String screenLabel, String exceptionMsg){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/part.fxml"));
            Parent theParent = loader.load();
            Parts controller = loader.getController();

            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle(title);
            newWindow.setResizable(false);// for add/modify part screens
            newWindow.setScene(new Scene(theParent));

            controller.initScreenLabel(screenLabel);
            controller.setPart(thePart);
            controller.initializeFieldData();
            newWindow.show();
        } catch (Exception e){
            System.out.println(exceptionMsg);
            e.printStackTrace();
        }
    }

    @FXML
    public void clickAddPart(ActionEvent actionEvent) {
        loadPartScreen(null, "Add Part to Inventory", "Add Part",
                "Cannot load add part window");
    }

    public void clickModPart(ActionEvent actionEvent) {

        Part thePart = partsListTableView.getSelectionModel().getSelectedItem();

        if(thePart == null){
            dialog(Alert.AlertType.INFORMATION, "Select Part", "No part selected",
                    "You must select a part to modify");
        } else {
            loadPartScreen(thePart, "Modify Product in Inventory", "Modify Part",
                    "Cannot load modify part window");
        }
    }

    public void clickDelPart(ActionEvent actionEvent) {

        int selectedIndex = partsListTableView.getSelectionModel().getSelectedIndex();

        if(selectedIndex == -1){
            dialog(Alert.AlertType.INFORMATION, "Select Part", "No part selected",
                    "You must select a part to delete");
        }
        else {
            String partName = Inventory.getAllParts().get(selectedIndex).getName();

            Optional<ButtonType> result = dialog(Alert.AlertType.CONFIRMATION, "Delete " + partName,
                    "Confirm Delete - Part : " + partName,
                    "Are you sure you want to delete " + partName + "?\n\n");

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(partsListTableView.getItems().get(selectedIndex));
            }
        }
    }


    // Click Handlers for Product Buttons on Main Screen
    public void clickSearchProd(ActionEvent actionEvent) {
        searchProduct();
    }


    private void loadProdScreen(Product theProduct, String title, String screenLabel, String exceptionMsg){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/product.fxml"));
            Parent theParent = loader.load();
            Products controller = loader.getController();

            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle(title);
            newWindow.setMinHeight(500);
            newWindow.setMinWidth(996);
            newWindow.setScene(new Scene(theParent));

            controller.initScreenLabel(screenLabel);
            controller.setProduct(theProduct);
            controller.initializeFieldData();
            newWindow.show();
        } catch (Exception e){
            System.out.println(exceptionMsg);
            e.printStackTrace();
        }
    }

    public void clickAddProd(ActionEvent actionEvent) {

        loadProdScreen(null,"Add Product to Inventory", "Add Product",
                "Cannot load add product window");
    }

    public void clickModProd(ActionEvent actionEvent) {

        Product theProduct = productsListTableView.getSelectionModel().getSelectedItem();

        if(theProduct == null){
            dialog(Alert.AlertType.INFORMATION, "Select Product", "No product selected",
                    "You must select a product to modify");
        } else {
            loadProdScreen(theProduct, "Modify Product in Inventory", "Modify Product",
                    "Cannot load modify product window");
        }
    }

    public void clickDelProd(ActionEvent actionEvent) {
        Product selectedProd = productsListTableView.getSelectionModel().getSelectedItem();

        if(selectedProd == null){
            dialog(Alert.AlertType.INFORMATION, "Select Product", "No Product Selected",
                    "You must select a product to delete");
        }
        else {
            Optional<ButtonType> result = dialog(Alert.AlertType.CONFIRMATION, "Delete " + selectedProd.getName(),
                    "Confirm Delete - Product : " + selectedProd.getName(),
                    selectedProd.getName() + " has " + selectedProd.getAllAssociatedParts().size() +
                            " associated part(s). \n\nAre you sure you want to delete this product ?\n\n");

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProd);
            }
        }
    }

    public void clickExit(ActionEvent actionEvent) {
        Optional<ButtonType> result = Main.dialog(Alert.AlertType.CONFIRMATION,
                "Exit Inventory Management System" , "Confirm Exit Application",
                "Are you sure you want to exit the application?\n\n");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.close();
        }
    }


    // For use in other controllers
    static <T> void initializeColumns(TableColumn<T, Integer> idCol, TableColumn<T, String> nameCol,
                                      TableColumn<T, Integer> invCol, TableColumn<T, Double> costCol) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        costCol.setCellFactory(tc -> new TableCell<T, Double>() {

            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
    }

    static Optional<ButtonType> dialog(Alert.AlertType alertType, String title, String header, String content){
        Alert alert = new Alert(alertType);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }



}// end Main


