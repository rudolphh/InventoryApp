package inventory.model;

public class InHousePart extends Part{

    private int machineID;

    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineID){ }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
