package inventory.model;

import javafx.collections.ObservableList;

public class Inventory {

// properties
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

// methods
    // create
    public void addPart(Part newPart){ }
    public void addProduct(Product newProduct){ }

    // read
    public Part lookupPart(int partID){ return null;  }
    public ObservableList<Part> lookupPart(String partName){ return null; }

    public Product lookupProduct(int productID){ return null; }
    public ObservableList<Product> lookupProduct(String productName){ return null; }

    public ObservableList<Part> getAllParts(){ return null; }
    public ObservableList<Product> getAllProducts(){ return null; }

    // update
    public void updatePart(int index, Part selectedPart){ }
    public void updateProduct(int index, Product selectedProduct){ }

    // delete
    public boolean deletePart(Part selectedPart) { return true; }
    public boolean deleteProduct(Product selectedProduct){ return true; }

}
