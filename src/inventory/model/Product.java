package inventory.model;

import javafx.collections.ObservableList;

public class Product {

// properties
    ObservableList<Part> associatedParts;
    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;

// constructor
    Product(int id, String name, double price, int stock, int min, int max) { }

// methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part aPart) { }
    public boolean deleteAssociatedPart(Part selectedAsPart){ return true; }
    public ObservableList<Part> getAllAssociatedParts(){ return null; }

}
