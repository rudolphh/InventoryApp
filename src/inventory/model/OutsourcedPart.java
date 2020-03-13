package inventory.model;

public class OutsourcedPart extends Part{

// properties
    private String companyName;

// methods
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName){ }


}
