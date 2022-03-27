package model;

public class Repair {
    private String rid;
    private String rcid;
    private String imeiR;
    private String brandModel;
    private String faultType;
    private String fault;
    private String bringDate;
    private String daytoGive;

    public Repair() {
    }

    public Repair(String rid, String rcid, String imeiR, String brandModel, String faultType, String fault, String bringDate, String daytoGive) {
        this.rid = rid;
        this.rcid = rcid;
        this.imeiR = imeiR;
        this.brandModel = brandModel;
        this.faultType = faultType;
        this.fault = fault;
        this.bringDate = bringDate;
        this.daytoGive = daytoGive;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRcid() {
        return rcid;
    }

    public void setRcid(String rcid) {
        this.rcid = rcid;
    }

    public String getImeiR() {
        return imeiR;
    }

    public void setImeiR(String imeiR) {
        this.imeiR = imeiR;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getBringDate() {
        return bringDate;
    }

    public void setBringDate(String bringDate) {
        this.bringDate = bringDate;
    }

    public String getDaytoGive() {
        return daytoGive;
    }

    public void setDaytoGive(String daytoGive) {
        this.daytoGive = daytoGive;
    }
}
