package views.tm;

public class PlaceOrderTm {

    private String code;
    private String type;
    private String brand;
    private String model;
    private String imei;
    private String warranty;
    private String description;
    private int qty;
    private double price;
    private double cost;

    public PlaceOrderTm() {
    }

    public PlaceOrderTm(String code, String type, String brand, String model, String imei, String warranty, String description, int qty, double price, double cost) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.imei = imei;
        this.warranty = warranty;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
