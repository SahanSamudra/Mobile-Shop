package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Accessories;
import model.Mobile;
import util.ValidationUtil;
import util.ValidationUtil1;
import views.tm.AccessoriesTm;
import views.tm.MobileTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddItemsController {
    public AnchorPane AddMobileContext;
    public JFXTextField txtId;
    public JFXTextField txtModel;
    public Button btnSave;
    public JFXTextField txtBrand;
    public TableView tblMobile;
    public ComboBox cmbItemTable;
    public TableView tblAccessories;
    public AnchorPane AddAccessoriesContext;
    public JFXTextField txtModelA;
    public Button btnSaveAccessories;
    public JFXTextField txtBrandA;
    public TableColumn colMId;
    public TableColumn colMModel;
    public TableColumn colImei;
    public TableColumn colMBrand;
    public TableColumn colMType;
    public TableColumn colMQty;
    public TableColumn colMBPrice;
    public TableColumn colMTotalCost;
    public TableColumn colMSellingPrice;
    public TableColumn colMDescription;
    public TableColumn colMSiD;
    public TableColumn colBillNumber;
    public TableColumn colBillDate;
    public JFXTextField txtImei;
    public JFXTextField txtQty;
    public JFXTextField txtType;
    public JFXTextField txtBuyingPrice;
    public JFXTextField txtSellingPrice;
    public JFXTextField txtDescription;
    public JFXTextField txtBillNumber;
    public JFXTextField txtBillDate;
    public JFXTextField txtIdA;
    public JFXTextField txtTypeA;
    public JFXTextField txtQtyA;
    public JFXTextField txtTotalCostA;
    public JFXTextField txtBPriceA;
    public JFXTextField txtSPriceA;
    public JFXTextField txtDescriptionA;
    public JFXTextField txtSupplierIdA;
    public JFXTextField txtBillNumberA;
    public JFXTextField txtBillDateA;
    public TableColumn colAiD;
    public JFXComboBox cmbSupplierIdA;
    public JFXComboBox cmbSupplierId;
    public TableColumn colAModel;
    public TableColumn colABrand;
    public TableColumn colAType;
    public TableColumn colAQty;
    public TableColumn colABuyingPrice;
    public TableColumn colATotalCost;
    public TableColumn colASellingPrice;
    public TableColumn colADescription;
    public TableColumn colASupplierId;
    public TableColumn colABillNumberA;
    public TableColumn colABillDateA;
    public JFXDatePicker dpDateMobile;
    public JFXDatePicker dpAccessories;
    public JFXTextField txtRefferanceCode;
    public TableColumn colReferance;
    public Button btnSaveM;
    public Button btnSaveA;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern qtyPattern = Pattern.compile("^[0-9]{1,6}$");
    Pattern idPattern = Pattern.compile("^(M0-)[0-9]{1,3}$");
    Pattern idAPattern = Pattern.compile("^(A0-)[0-9]{1,3}$");
    Pattern modelPattern = Pattern.compile("^[A-z0-9/ ]{1,30}$");
    Pattern brandPattern = Pattern.compile("^[A-z0-9/ ]{1,30}$");
    Pattern typePattern =Pattern.compile("^(Mobile)$");
    Pattern typeAPattern =Pattern.compile("^(Accessories)$");

    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{1,40}$");

    LinkedHashMap<JFXTextField, Pattern> map1 = new LinkedHashMap<>();
    Pattern qtyPatternA = Pattern.compile("^[0-9]{1,6}$");
    Pattern idPatternA = Pattern.compile("^(M0-)[0-9]{1,3}$");
    Pattern idAPatternA = Pattern.compile("^(A0-)[0-9]{1,3}$");
    Pattern modelPatternA = Pattern.compile("^[A-z0-9/ ]{1,30}$");
    Pattern brandPatternA = Pattern.compile("^[A-z0-9/ ]{1,30}$");
    Pattern typePatternA =Pattern.compile("^(Mobile)$");
    Pattern typeAPatternA =Pattern.compile("^(Accessories)$");

    Pattern addressPatternA = Pattern.compile("^[A-z0-9/ ]{1,40}$");






    public void  initialize(){

        btnSaveM.setDisable(true);
        btnSaveA.setDisable(true);
        storeValidations();
        storeValidations1();

        colMId.setCellValueFactory(new PropertyValueFactory<>("mid"));
        colMModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colImei.setCellValueFactory(new PropertyValueFactory<>("imei"));
        colMBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colMType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colMBPrice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
        colMTotalCost.setCellValueFactory(new PropertyValueFactory<>("Tcost"));
        colMSellingPrice.setCellValueFactory(new PropertyValueFactory<>("Sprice"));
        colMDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMSiD.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colBillNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
        colBillDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));

        try {
            loadAllMobiles(new MobileController().getAllMobile());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        //--------------------------------------------------------------------------------------------

        colAiD.setCellValueFactory(new PropertyValueFactory<>("aid"));
        colAModel.setCellValueFactory(new PropertyValueFactory<>("amodel"));
        colReferance.setCellValueFactory(new PropertyValueFactory<>("aimei"));
        colABrand.setCellValueFactory(new PropertyValueFactory<>("abrand"));
        colAType.setCellValueFactory(new PropertyValueFactory<>("atype"));
        colAQty.setCellValueFactory(new PropertyValueFactory<>("aqty"));
        colABuyingPrice.setCellValueFactory(new PropertyValueFactory<>("abprice"));
        colATotalCost.setCellValueFactory(new PropertyValueFactory<>("atotalcost"));
        colASellingPrice.setCellValueFactory(new PropertyValueFactory<>("asprice"));
        colADescription.setCellValueFactory(new PropertyValueFactory<>("adescription"));
        colASupplierId.setCellValueFactory(new PropertyValueFactory<>("asid"));
        colABillNumberA.setCellValueFactory(new PropertyValueFactory<>("abillnum"));
        colABillDateA.setCellValueFactory(new PropertyValueFactory<>("abdate"));

        try {
            loadAllAccessories(new AccessoriesController().getAllAccessories());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //----------------------------------------------------------------------------------------


        try {
            loadSupIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        AddMobileContext.setTranslateX(1000);
        AddAccessoriesContext.setTranslateX(1000);

        cmbItemTable.getItems().addAll("MobileTable","AccessoriesTable");
        cmbItemTable.setValue("MobileTable");
        cmbItemTable.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                   if(newValue.equals("MobileTable")){

                       tblMobile.setVisible(true);
                       tblAccessories.setVisible(false);
                   }
                   else{

                       tblAccessories.setVisible(true);
                       tblMobile.setVisible(false);
                }

                });



    }

    private void storeValidations() {
        map.put(txtId, idPattern);
        map.put(txtType,typePattern);
        map.put(txtBrand,brandPattern);
        map.put(txtModel, modelPattern);
        map.put(txtImei,addressPattern);
        map.put(txtDescription,addressPattern);
        map.put(txtBuyingPrice,qtyPattern);
        map.put(txtSellingPrice,qtyPattern);
        map.put(txtQty,qtyPattern);
        map.put(txtBillNumber,addressPattern);
    }

    private void storeValidations1(){

        map1.put(txtIdA, idAPatternA);
        map1.put(txtTypeA,typeAPatternA);
        map1.put(txtBrandA,modelPatternA);
        map1.put(txtModelA,modelPatternA);
        map1.put(txtRefferanceCode,addressPatternA);
        map1.put(txtDescriptionA,addressPatternA);
        map1.put(txtBPriceA,qtyPatternA);
        map1.put(txtSPriceA,qtyPatternA);
        map1.put(txtQtyA,qtyPatternA);
        map1.put(txtBillNumberA,addressPatternA);

    }


    //----------------------------------------------------------------------------------

    public void btnAddMobile(ActionEvent actionEvent) throws IOException {
        AddAccessoriesContext.setTranslateX(1000);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(AddMobileContext);

        slide.setToX(0);
        slide.play();

        AddMobileContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });

    }

    public void btnBackAddMobile(ActionEvent actionEvent) {

        AddMobileContext.setTranslateX(1000);
    }

    public void btnSaveMobile(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        double Tcost=Double.parseDouble(txtQty.getText())*Double.parseDouble(txtBuyingPrice.getText());
        Mobile m=new Mobile(txtId.getText(),txtModel.getText(),txtImei.getText(),txtBrand.getText(),txtType.getText(),
                Integer.parseInt(txtQty.getText()),Double.parseDouble(txtBuyingPrice.getText()),Tcost,
                Double.parseDouble(txtSellingPrice.getText()),txtDescription.getText(), (String) cmbSupplierId.getValue(),
                txtBillNumber.getText(),dpDateMobile.getValue().toString());



        if (new MobileController().savemobile(m)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllMobiles(new MobileController().getAllMobile());

            }
         /*   loadAllMobiles(new MobileController().getAllMobile());
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();*/
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void loadAllMobiles(ArrayList<Mobile>mobiles){
        ObservableList<MobileTm> observableList= FXCollections.observableArrayList();
        mobiles.forEach(m->{
            observableList.add(new MobileTm(m.getMid(),m.getModel(),m.getImei(),m.getBrand(),m.getType(),m.getQty(),
                    m.getBprice(),m.getTcost(),m.getSprice(),m.getDescription(),m.getSid(),m.getBillNumber(),m.getBillDate()));
            tblMobile.setItems(observableList);
        });

    }

    public void btnUpdateMobile(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        double Tcost=Double.parseDouble(txtQty.getText())*Double.parseDouble(txtBuyingPrice.getText());
        Mobile m=new Mobile(txtId.getText(),txtModel.getText(),txtImei.getText(),txtBrand.getText(),txtType.getText(),
                Integer.parseInt(txtQty.getText()),Double.parseDouble(txtBuyingPrice.getText()),Tcost,
                Double.parseDouble(txtSellingPrice.getText()),txtDescription.getText(), (String) cmbSupplierId.getValue(),
                txtBillNumber.getText(),dpDateMobile.getValue().toString());



        if (new MobileController().updateMobile(m)) {  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllMobiles(new MobileController().getAllMobile());

            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnDeleteMobile(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new MobileController().deleteMobile(txtId.getText())) {
            loadAllMobiles(new MobileController().getAllMobile());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
            txtId.clear();

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }


    private void setData(Mobile m1) {
        txtId.setText(m1.getMid());
        txtModel.setText(m1.getModel());
        txtImei.setText(m1.getImei());
        txtBrand.setText(m1.getBrand());
        txtType.setText(m1.getType());
        txtQty.setText(String.valueOf(m1.getQty()));
        txtBuyingPrice.setText(String.valueOf(m1.getBprice()));
        txtSellingPrice.setText(String.valueOf(m1.getSprice()));
        txtDescription.setText(m1.getDescription());
        cmbSupplierId.setValue(m1.getSid());
        txtBillNumber.setText(m1.getBillNumber());





    }


    //-----------------------------------------------------------------------------

    public void btnAddAccessories(ActionEvent actionEvent) {

        AddMobileContext.setTranslateX(1000);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(AddAccessoriesContext);

        slide.setToX(0);
        slide.play();

        AddAccessoriesContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });
    }

    public void btnCancelAccessories(ActionEvent actionEvent) {

        AddAccessoriesContext.setTranslateX(1000);
    }

    public void btnSaveAccessories(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        double atotalcost = Double.parseDouble(txtQtyA.getText()) * Double.parseDouble(txtBPriceA.getText());
        Accessories a = new Accessories(txtIdA.getText(), txtModelA.getText(),txtImei.getText(), txtBrandA.getText(), txtTypeA.getText(),
                Integer.parseInt(txtQtyA.getText()), Double.parseDouble(txtBPriceA.getText()),
                atotalcost, Double.parseDouble(txtSPriceA.getText()), txtDescriptionA.getText(),
                (String) cmbSupplierIdA.getValue(), txtBillNumberA.getText(), dpAccessories.getValue().toString());

        if (new AccessoriesController().accessories(a)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllAccessories(new AccessoriesController().getAllAccessories());

            }

    }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void loadAllAccessories(ArrayList<Accessories>accessories){
        ObservableList<AccessoriesTm> observableList=FXCollections.observableArrayList();
        accessories.forEach(a->{
            observableList.add(new AccessoriesTm(a.getAid(),a.getAmodel(),a.getAimei(),a.getAbrand(),a.getAtype(),a.getAqty(), a.getAbprice(),a.getAtotalcost(),a.getAsprice(),
                    a.getAdescription(),a.getAsid(),a.getAbillnum(),a.getAbdate()));
            tblAccessories.setItems(observableList);
        });
    }

  public void btnUpdateAccessories(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


      double atotalcost = Double.parseDouble(txtQtyA.getText()) * Double.parseDouble(txtBPriceA.getText());
      Accessories a = new Accessories(txtIdA.getText(), txtModelA.getText(), txtImei.getText(), txtBrandA.getText(), txtTypeA.getText(),
              Integer.parseInt(txtQtyA.getText()), Double.parseDouble(txtBPriceA.getText()),
              atotalcost, Double.parseDouble(txtSPriceA.getText()), txtDescriptionA.getText(),
              (String) cmbSupplierIdA.getValue(), txtBillNumberA.getText(), dpAccessories.getValue().toString());


      if (new AccessoriesController().updateAccessories(a)) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
          Optional<ButtonType> buttonType = alert.showAndWait();
          if (buttonType.get().equals(ButtonType.YES)) {
              loadAllAccessories(new AccessoriesController().getAllAccessories());




          } else {
              new Alert(Alert.AlertType.WARNING, "Try Again").show();
          }
      }
  }

    public void btnDeleteAccessories(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

       if (new AccessoriesController().deleteAccessories(txtId.getText())) {
            loadAllAccessories(new AccessoriesController().getAllAccessories());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
            txtId.clear();

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }



      private void setData(Accessories a1) {
        txtIdA.setText(a1.getAid());
        txtModelA.setText(a1.getAmodel());
        txtRefferanceCode.setText(a1.getAimei());
        txtBrandA.setText(a1.getAbrand());
        txtTypeA.setText(a1.getAtype());
        txtQtyA.setText(String.valueOf(a1.getAqty()));
        txtBPriceA.setText(String.valueOf(a1.getAbprice()));
        txtSPriceA.setText(String.valueOf(a1.getAsprice()));
        txtDescriptionA.setText(a1.getAdescription());
        cmbSupplierIdA.setValue(a1.getAsid());
        txtBillNumberA.setText(a1.getAbillnum());

    }

//-----------------------------------------------------------------------------------------


    public void loadSupIDs() throws SQLException, ClassNotFoundException {
        List<String> supids=new SupplierSaveController().getSupplierIDs();
        cmbSupplierId.getItems().addAll(supids);
        cmbSupplierIdA.getItems().addAll(supids);
    }






    public void btnIDSearchM(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {



            Mobile M1 = new MobileController().getMobile(txtId.getText());
            if (M1 == null) {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            } else {
                setData(M1);
            }
        }

    public void MKeyPress(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnSaveM);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void btnASearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       /* if (keyEvent.getCode() == KeyCode.ENTER) {
            String aid = txtIdA.getText();*/

            Accessories A1 = new AccessoriesController().getAccessories(txtIdA.getText());
            if (A1 == null) {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            } else {
                setData(A1);
            }
        }


   public void AKeyPress(KeyEvent keyEvent) {
       Object response = ValidationUtil1.validate1(map1, btnSaveA);

       if (keyEvent.getCode() == KeyCode.ENTER) {
           if (response instanceof JFXTextField) {
               JFXTextField errorText = (JFXTextField) response;
               errorText.requestFocus();
           } else if (response instanceof Boolean) {

           }
       }
   }
}





