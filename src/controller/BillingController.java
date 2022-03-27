package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.ValidationUtil;
import views.tm.BillingTm;
import views.tm.PlaceOrderTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class BillingController {


    public Label lblDate;
    public Label lblTime;
    public AnchorPane loadCustomerContext;
    public JFXTextField txtBrandName;
    public JFXTextField txtPrice;
    public JFXTextField txtWarrantyDue;
    public JFXTextField txtItemType;
    public JFXTextField txtModel;
    public JFXTextField txtDesceiption;
    public JFXTextField txtQty;
    public JFXTextField txtDiscount;
    public Label lblItemCount;
    public Label lblDiscount;
    public Label lblTotal;
    public Label viewCustomerInOrderContext;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContact;
    public JFXTextField txtCustomerID;
    public TableView tblBilling;
    public TableColumn colItemID;
    public TableColumn colBrandName;
    public TableColumn colModel;
    public TableColumn colWarramtyDue;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQty;
    public JFXTextField txtIMEI;
    public TableColumn colItemType;
    public TableColumn colImei;
    public ComboBox cmbCustomer;
    public JFXTextField txtQtyOnHand;
    public ComboBox cmbItemCode;
    public TableColumn colItemTotal;
    public Label lblFinalTotal;
    public Label lblOrderId;
    public Button btnAddToCart;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern qtyPattern = Pattern.compile("^[0-9]{1,6}$");

    private void storeValidations(){
        map.put(txtQty, qtyPattern);

    }

    int cartSelectedRawRemove = -1;


    public void initialize(){
        btnAddToCart.setDisable(true);
        storeValidations();



        colItemID.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colImei.setCellValueFactory(new PropertyValueFactory<>("imei"));
        colWarramtyDue.setCellValueFactory(new PropertyValueFactory<>("warranty"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("cost"));


/*
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("Bcid"));
        colItemID.setCellValueFactory(new PropertyValueFactory<>("Bicode"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("Bitype"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("Bbrandname"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("Bmodel"));
        colImei.setCellValueFactory(new PropertyValueFactory<>("Bimei"));
        colWarramtyDue.setCellValueFactory(new PropertyValueFactory<>("Bwarrantydue"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Bdescription"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Bqty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));


        try {
            loadAllBilling(new BillingSaveController().getAllBilling());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


       /* loadPayementID();*/




        cmbCustomer.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{

            try {
                setData(new  CustomerSaveController().getCustomer((String)newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));/////////



   /*     cmbItemCode.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{

            try {
                setData(new  AccessoriesController().getAccessories((String)newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));/////////*/

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{

            try {
                setData(new  MobileController().getMobile((String)newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

           try {
                setData(new  AccessoriesController().getAccessories((String)newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));/////////

       try {
            loadMobileIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

 /*       try {
            loadAccessoriesIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        try {
            loadCusIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        initClock();
        setOrderId();


        tblBilling.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            cartSelectedRawRemove= (int) newValue;
        });

    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));


            SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            lblDate.setText(formatter2.format(date));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnSearchCustomer(ActionEvent actionEvent) throws IOException {


    }

    public void loadCusIDs() throws SQLException, ClassNotFoundException {
        List<String> cusids=new CustomerSaveController().getCusIDs();
        cmbCustomer.getItems().addAll(cusids);

    }

    public void SelectCustomerKeyPress(KeyEvent keyEvent) {
    }

    private void setData(Customer cc1) {
        txtCustomerID.setText(cc1.getCustomerId());
        txtCustomerName.setText(cc1.getCustomerName());
        txtCustomerContact.setText(cc1.getNoOfContact());
        txtCustomerAddress.setText(cc1.getCustomerAddress());


    }


    public void loadMobileIDs() throws SQLException, ClassNotFoundException {
        List<String> mobids=new MobileController().getMobileIDs();
        cmbItemCode.getItems().addAll(mobids);

    }

    private void setData(Accessories aa1) {

        txtModel.setText(aa1.getAmodel());
        txtBrandName.setText(aa1.getAbrand());
        txtItemType.setText(aa1.getAtype());
        txtIMEI.setText(aa1.getAimei());

        txtQtyOnHand.setText(String.valueOf(aa1.getAqty()));
        txtPrice.setText(String.valueOf(aa1.getAsprice()));
        txtDesceiption.setText(aa1.getAdescription());



    }

    private void setData(Mobile mm1) {

        txtModel.setText(mm1.getModel());
        txtIMEI.setText(mm1.getImei());
        txtBrandName.setText(mm1.getBrand());
        txtItemType.setText(mm1.getType());
        txtPrice.setText(String.valueOf(mm1.getSprice()));
        txtDesceiption.setText(mm1.getDescription());
        txtQtyOnHand.setText(String.valueOf(mm1.getQty()));





    }
    private void setOrderId(){

        try {
            lblOrderId.setText(new  OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnRemove(ActionEvent actionEvent) {
    }
    ObservableList<PlaceOrderTm> oblist =FXCollections.observableArrayList();
    public void btnAddToCart(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

       String ItemType = txtItemType.getText();
       String BrandName =txtBrandName.getText();
       String Model =   txtModel.getText();
       String Imei = txtIMEI.getText();
       String WarrantyDue = txtWarrantyDue.getText();
       String Description= txtDesceiption.getText();
       int QtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
       int QtyForCustomer = Integer.parseInt(txtQty.getText());
       double UnitPrice =Double.parseDouble(txtPrice.getText());
       double Total = QtyForCustomer * UnitPrice;

       if (QtyOnHand<QtyForCustomer){
           new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
           return;
       }



       PlaceOrderTm tm= new PlaceOrderTm((String) cmbItemCode.getValue(),
               ItemType,
               BrandName,
               Model,
               Imei,
               WarrantyDue,
               Description,
               QtyForCustomer,
               UnitPrice,
               Total

       );

       int rowNumber=isExists(tm);

       if (rowNumber==-1){
           oblist.add(tm);

       }else {
         PlaceOrderTm temp = oblist.get(rowNumber);
        PlaceOrderTm newTm = new PlaceOrderTm(
                temp.getCode(),temp.getType(),
                temp.getBrand(),temp.getModel(),
                temp.getImei(),temp.getWarranty(),temp.getDescription(),temp.getQty()+QtyForCustomer,
                UnitPrice,Total+temp.getCost()
        );

           if (QtyOnHand<temp.getQty()){
               new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
               return;
           }

        oblist.remove(rowNumber);
        oblist.add(newTm);
       }
       tblBilling.setItems(oblist);

        calculateCost();
    }


   private int isExists(PlaceOrderTm tm){

       for (int i = 0; i < oblist.size() ; i++) {
                if (tm.getCode().equals(oblist.get(i).getCode())){
                    return i;
                }
       }

       return -1;
    }

    void calculateCost(){

        double ttl=0;
        for (PlaceOrderTm tm:oblist
             ) {
          ttl+=  tm.getCost();
        }
            lblFinalTotal.setText(ttl+"  /=");
    }


    public void btnClear(ActionEvent actionEvent) {

        if (cartSelectedRawRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select Row").show();
        }else {
            oblist.remove(cartSelectedRawRemove);
            calculateCost();
            tblBilling.refresh();
        }
    }

    public void btnPlaceOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<ItemReports> items= new ArrayList<>();
        double total=0;
        for (PlaceOrderTm tempTm:oblist
             ) {

            total+=tempTm.getCost();
            items.add(new ItemReports(tempTm.getCode(),tempTm.getPrice(),
                    tempTm.getQty(),tempTm.getCost(),lblDate.getText())



            );

        }
        Order order =new Order(
                lblOrderId.getText(),
                txtCustomerID.getText(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items
        );
        if (new OrderController().placeOrder(order)){

            try {
                JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../invoice/Dream Bill.jrxml"));
                JasperReport compileReport = JasperCompileManager.compileReport(design);
                /*Get all values from table*/
                ObservableList<PlaceOrderTm> items1 = tblBilling.getItems();
                /*Create a Bean Array Data Source and pass the table values to it*/

                /*setting values for parameters*/
                String OrderId = lblOrderId.getText();
                String Cost =lblFinalTotal.getText();


                /*Setting parameter values*/
                HashMap map = new HashMap();
                map.put("OrderId", OrderId);// id= param name of report // customerID= input value of text field
                map.put("Cost", Cost);

                JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanArrayDataSource(items1.toArray()));
                JasperViewer.viewReport(jasperPrint, false);

                //if you wanna print the report directly you can use this instead of JasperViewer
                /*JasperPrintManager.printReport(jasperPrint,false);*/

            } catch (JRException e) {
                e.printStackTrace();
            }

            }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void KeyPress(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddToCart);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
}
