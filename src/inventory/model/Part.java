package inventory.model;

public abstract class Part {

// Properties
    private int id;
    private String name;
    private double price;
    private int inventory;
    private int min;
    private int max;

// Constructors
    public Part(){ }

    public Part(int id, String name, double price, int inventory, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.min = min;
        this.max = max;
    }

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
}
