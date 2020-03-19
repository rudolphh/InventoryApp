package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

// properties
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int currentPartID = 1;
    private static int currentProductID = 1;

    public static int getCurrentPartID() {
        return currentPartID;
    }
    public static int getCurrentProductID() {
        return currentProductID;
    }

// methods
    // create
    public static void addPart(Part newPart){
        allParts.add(newPart);
        Inventory.currentPartID++;
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
        Inventory.currentProductID++;
    }

    // read
    public static Part lookupPart(int partID){ return null;  }
    public static ObservableList<Part> lookupPart(String partName){ return null; }

    public static Product lookupProduct(int productID){ return null; }
    public static ObservableList<Product> lookupProduct(String productName){ return null; }

    public static ObservableList<Part> getAllParts(){ return allParts; }
    public static ObservableList<Product> getAllProducts(){ return allProducts; }

    // update
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product selectedProduct){ allProducts.set(index, selectedProduct); }

    // delete
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;
    }

}
