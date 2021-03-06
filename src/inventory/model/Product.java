package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    // properties
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int inventory;
    private int min;
    private int max;

// constructor

    public Product(){ }

    public Product(int id, String name, double price, int inventory, int min, int max) {
        associatedParts = FXCollections.observableArrayList();
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.min = min;
        this.max = max;
    }

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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
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

    public void addAssociatedPart(Part aPart) {
        associatedParts.add(aPart);
    }

    public boolean deleteAssociatedPart(Part selectedAsPart) {
        associatedParts.remove(selectedAsPart);
        return true;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
