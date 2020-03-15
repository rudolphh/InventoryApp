package inventory.model;

public class InHousePart extends Part{

// properties
    private int machineID;

    // constructor
    public InHousePart(int id, String name, double price, int inventory, int min, int max, int machineID){
        super(id, name, price, inventory, min, max);
        this.machineID = machineID;
    }

// methods
    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
