package inventory.model;

public class OutsourcedPart extends Part{

// properties
    private String companyName;

// constructor
    public OutsourcedPart(int id, String name, double price, int inventory, int min, int max, String companyName){
        super(id, name, price, inventory, min, max);
        this.companyName = companyName;
    }

// methods
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}
