package model;

public class Mobile {
    private String mid;
    private String model;
    private String imei;
    private String brand;
    private String type;
    private int qty;
    private double Bprice;
    private double Tcost;
    private double Sprice;
    private String description;
    private String sid;
    private String billNumber;
    private String billDate;

    public Mobile() {
    }

    public Mobile(String mid, String model, String imei, String brand, String type, int qty, double bprice, double tcost, double sprice, String description, String sid, String billNumber, String billDate) {
        this.mid = mid;
        this.model = model;
        this.imei = imei;
        this.brand = brand;
        this.type = type;
        this.qty = qty;
        Bprice = bprice;
        Tcost = tcost;
        Sprice = sprice;
        this.description = description;
        this.sid = sid;
        this.billNumber = billNumber;
        this.billDate = billDate;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getBprice() {
        return Bprice;
    }

    public void setBprice(double bprice) {
        Bprice = bprice;
    }

    public double getTcost() {
        return Tcost;
    }

    public void setTcost(double tcost) {
        Tcost = tcost;
    }

    public double getSprice() {
        return Sprice;
    }

    public void setSprice(double sprice) {
        Sprice = sprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
