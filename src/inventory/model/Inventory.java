package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

// properties
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int currentPartID = 0;
    private static int currentProductID = 0;


    public static int getCurrentPartID() {
        return currentPartID;
    }

    public static void incCurrentPartID(int currentPartID) {
        Inventory.currentPartID = currentPartID;
    }

    public static int getCurrentProductID() {
        return currentProductID;
    }

    public static void setCurrentProductID(int currentProductID) {
        Inventory.currentProductID = currentProductID;
    }



// methods
    // create
    public static void addPart(Part newPart){ allParts.add(newPart); }
    public static void addProduct(Product newProduct){ allProducts.add(newProduct); }

    // read
    public static Part lookupPart(int partID){ return null;  }
    public static ObservableList<Part> lookupPart(String partName){ return null; }

    public static Product lookupProduct(int productID){ return null; }
    public static ObservableList<Product> lookupProduct(String productName){ return null; }

    public static ObservableList<Part> getAllParts(){ return allParts; }
    public static ObservableList<Product> getAllProducts(){ return null; }

    // update
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product selectedProduct){ }

    // delete
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct){ return true; }


    // helpers
    public static int totalParts(){ return allParts.size(); }
}
