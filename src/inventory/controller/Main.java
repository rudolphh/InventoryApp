package inventory.controller;

import inventory.model.Part;
import inventory.model.Product;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    }

    @FXML
    public void clickAddPart(ActionEvent actionEvent) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/part.fxml"));
            Parent theParent = loader.load();
            Parts controller = loader.getController();

            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle("Add Product to Inventory");
            newWindow.setResizable(false);// for add/modify part screens
            newWindow.setScene(new Scene(theParent));

            controller.initScreenLabel("Add Part");
            newWindow.show();
        } catch (Exception e){
            System.out.println("Cannot load add part window");
            e.printStackTrace();
        }
    }

    public void clickModPart(ActionEvent actionEvent) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/part.fxml"));
            Parent theParent = loader.load();
            Parts controller = loader.getController();

            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle("Modify Product in Inventory");
            newWindow.setResizable(false);// for add/modify part screens
            newWindow.setScene(new Scene(theParent));

            controller.initScreenLabel("Modify Part");
            newWindow.show();
        } catch (Exception e){
            System.out.println("Cannot load modify part window");
            e.printStackTrace();
        }
    }

    public void clickDelPart(ActionEvent actionEvent) {
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