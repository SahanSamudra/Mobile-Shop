package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Repair;
import model.Supplier;
import util.ValidationUtil;
import views.tm.RepairTm;
import views.tm.SupplierTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class RepairController {
    public TableColumn colRepairID;
    public TableColumn colCustomerIDRepair;
    public TableColumn colImeiRepair;
    public TableColumn colBrandModelRepair;
    public TableColumn colFaultType;
    public TableColumn colFault;
    public TableColumn colBringDate;
    public TableColumn colDayToGive;
    public JFXTextField txtRepairID;
    public JFXComboBox cmbCustomerID;
    public JFXComboBox cmbFaultType;
    public JFXTextField txtMobileImei;
    public JFXTextField txtBrandModel;
    public JFXTextField txtFault;
    public JFXDatePicker dpBringDate;
    public JFXDatePicker dpDaytoGive;
    public TableView tblRepair;
    public Button btnSave;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(R-)[0-9]{1,3}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");
    Pattern companyPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");

    private void storeValidations() {
        map.put(txtRepairID, idPattern);
        map.put(txtFault, addressPattern);
        map.put(txtMobileImei, companyPattern);
        map.put(txtBrandModel,addressPattern);


    }

    public void initialize(){

        storeValidations();
        btnSave.setDisable(true);


        colRepairID.setCellValueFactory(new PropertyValueFactory<>("rid"));
        colCustomerIDRepair.setCellValueFactory(new PropertyValueFactory<>("rcid"));
        colImeiRepair.setCellValueFactory(new PropertyValueFactory<>("imeiR"));
        colBrandModelRepair.setCellValueFactory(new PropertyValueFactory<>("brandModel"));
        colFaultType.setCellValueFactory(new PropertyValueFactory<>("faultType"));
        colFault.setCellValueFactory(new PropertyValueFactory<>("fault"));
        colBringDate.setCellValueFactory(new PropertyValueFactory<>("bringDate"));
        colDayToGive.setCellValueFactory(new PropertyValueFactory<>("daytoGive"));

        try {
            loadAllRepair(new RepairSaveController().getAllRepair());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            loadCusIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbFaultType.getItems().addAll("Software","Hardware");
        cmbFaultType.setValue("MobileTable");
        cmbFaultType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                });


    }

    private void loadCusIDs() throws SQLException, ClassNotFoundException {

        List<String> cusIDs=new CustomerSaveController().getCusIDs();
        cmbCustomerID.getItems().addAll(cusIDs);
    }

    public void loadAllRepair(ArrayList<Repair> repairs){
        ObservableList<Repair> observableList= FXCollections.observableArrayList();
        repairs.forEach(r->{
            observableList.add(new Repair(r.getRid(),r.getRcid(),r.getImeiR(),r.getBrandModel(),r.getFaultType(),
                    r.getFault(),r.getBringDate(),r.getDaytoGive()));
            tblRepair.setItems(observableList);
        });
    }

    public void btnSaveRepair(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Repair r1=new Repair(txtRepairID.getText(),
                cmbCustomerID.getValue().toString(),
                txtMobileImei.getText(),
                txtBrandModel.getText(),
                cmbFaultType.getValue().toString(),
                txtFault.getText(),
                dpBringDate.getValue().toString(),
                dpDaytoGive.getValue().toString()



        );
        if(new RepairSaveController().saveRepair(r1)){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllRepair(new RepairSaveController().getAllRepair());

            }


        } else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void btnUpdateRepair(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Repair r=new Repair(txtRepairID.getText(),
                cmbCustomerID.getValue().toString(),
                txtMobileImei.getText(),
                txtBrandModel.getText(),
                cmbFaultType.getValue().toString(),
                txtFault.getText(),
                dpBringDate.getValue().toString(),
                dpDaytoGive.getValue().toString());



        if (new RepairSaveController().updateRepair(r)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllRepair(new RepairSaveController().getAllRepair());

            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnDeleteRepair(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new RepairSaveController().deleteRepair(txtRepairID.getText())) {

            loadAllRepair(new RepairSaveController().getAllRepair());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
            txtRepairID.clear();


        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }



    private void setData(Repair r1) throws SQLException, ClassNotFoundException {
        txtRepairID.setText(r1.getRid());
        cmbCustomerID.setValue(r1.getRcid());
        txtMobileImei.setText(r1.getImeiR());
        txtBrandModel.setText(r1.getBrandModel());
        cmbFaultType.setValue(r1.getFaultType());
        txtFault.setText(r1.getFault());
        dpBringDate.setValue(LocalDate.parse(r1.getBringDate()));
        dpDaytoGive.setValue(LocalDate.parse(r1.getDaytoGive()));



    }

    public void KeyPressR(KeyEvent keyEvent) {

        Object response = ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void btnRIdSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Repair R1 = new RepairSaveController().getRepair(txtRepairID.getText());
        if (R1 == null) {

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(R1);
        }
    }
}
