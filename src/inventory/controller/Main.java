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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Inventory.addPart(new InHousePart(1, "Transmission", 299.00, 10, 1, 20, 20383));
        Inventory.addPart(new InHousePart(2, "brakes", 99.00, 5, 3, 30, 205653));

        //        System.out.println(Inventory.getAllParts().get(0).getName());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Part> allParts = Inventory.getAllParts();
        partsListTableView.setItems(allParts);

        // set up search functionality
        partFilteredList = new FilteredList<>(allParts, b-> true);
        SortedList<Part> sortedPartList = new SortedList<>(partFilteredList);
        sortedPartList.comparatorProperty().bind(partsListTableView.comparatorProperty());
        partsListTableView.setItems(sortedPartList);

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
        System.out.println(newValue);
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

    @FXML
    public void clickAddPart(ActionEvent actionEvent) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/part.fxml"));
            Parent theParent = loader.load();
            Parts controller = loader.getController();

            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle("Add Part to Inventory");
            newWindow.setResizable(false);// for add/modify part screens
            newWindow.setScene(new Scene(theParent));

            controller.initScreenLabel("Add Part");
            controller.initializeFieldData();
            newWindow.show();
        } catch (Exception e){
            System.out.println("Cannot load add part window");
            e.printStackTrace();
        }
    }

    public void clickModPart(ActionEvent actionEvent) {

        Part thePart = partsListTableView.getSelectionModel().getSelectedItem();

        if(thePart == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Part");
            alert.setHeaderText("No part selected");
            alert.setContentText("You must select a part to modify");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/part.fxml"));
                Parent theParent = loader.load();
                Parts controller = loader.getController();

                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);
                newWindow.setTitle("Modify Product in Inventory");
                newWindow.setResizable(false);// for add/modify part screens
                newWindow.setScene(new Scene(theParent));

                controller.initScreenLabel("Modify Part");
                controller.setPart(thePart);
                controller.initializeFieldData();
                newWindow.show();
            } catch (Exception e) {
                System.out.println("Cannot load modify part window");
                e.printStackTrace();
            }
        }
    }

    public void clickDelPart(ActionEvent actionEvent) {

        int selectedIndex = partsListTableView.getSelectionModel().getSelectedIndex();

        if(selectedIndex == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Part");
            alert.setHeaderText("No part selected");
            alert.setContentText("You must select a part to delete");
            alert.showAndWait();
        }
        else {
            String partName = Inventory.getAllParts().get(selectedIndex).getName();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Delete " + partName);
            alert.setHeaderText("Confirm Delete - Part : " + partName);
            alert.setContentText("Are you sure you want to delete " + partName + "?\n\n");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(partsListTableView.getItems().get(selectedIndex));
            }
        }
    }


    // Click Handlers for Product Buttons on Main Screen
    public void clickSearchProd(ActionEvent actionEvent) {

    }

    public void clickAddProd(ActionEvent actionEvent) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/product.fxml"));
            Parent theParent = loader.load();
            Products controller = loader.getController();

            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle("Add Product to Inventory");
            newWindow.setMinHeight(500);
            newWindow.setMinWidth(996);
            newWindow.setScene(new Scene(theParent));

            controller.initScreenLabel("Create Product");
            newWindow.show();
        } catch (Exception e){
            System.out.println("Cannot load add product window");
        }
    }

    public void clickModProd(ActionEvent actionEvent) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/product.fxml"));
            Parent theParent = loader.load();
            Products controller = loader.getController();

            Stage newWindow = new Stage();
            newWindow.setTitle("Modify Product in Inventory");
            newWindow.setMinHeight(500);
            newWindow.setMinWidth(996);
            newWindow.setScene(new Scene(theParent));

            controller.initScreenLabel("Modify Product");
            newWindow.show();
        } catch (Exception e){
            System.out.println("Cannot load modify product window");
        }
    }

    public void clickDelProd(ActionEvent actionEvent) {
    }

    public void clickExit(ActionEvent actionEvent) {
    }
}

/*

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/part.fxml"));
            Parts controller = loader.getController();
            controller.initScreenLabel("Modify Part");
            Parent theParent = loader.load();
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle("Modify Product in Inventory");
            newWindow.setResizable(false);// for add/modify part screens
            newWindow.setScene(new Scene(theParent));
            newWindow.show();
        } catch (Exception e){
            System.out.println("Cannot load part window");
        }

 */