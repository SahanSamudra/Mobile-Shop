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
import model.Accessories;
import model.Customer;
import model.Repair;
import model.ReturnItem;
import util.ValidationUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ReturnItemController {
    public TableColumn colReturnID;
    public TableColumn colCustomerIdR;
    public TableColumn colItemCodeR;
    public TableColumn colReturnReason;
    public TableColumn colBilingDateR;
    public TableColumn colReturnDate;
    public TableColumn colWarrantyDueR;
    public JFXTextField txtReturnID;
    public JFXTextField txtWarrantyDueR;
    public JFXComboBox cmbCustomerIdR;
    public JFXDatePicker dpBillingDateR;
    public JFXDatePicker dpReturnDateR;
    public JFXTextField txtReturnReason;
    public JFXComboBox cmbItemCodeR;
    public TableView tblReturn;
    public Button btnSave;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(RET-)[0-9]{1,3}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");
    Pattern companyPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");

    private void storeValidations() {
        map.put(txtReturnID, idPattern);
        map.put(txtWarrantyDueR, addressPattern);
        map.put(txtReturnReason, companyPattern);



    }



    public void initialize() {
        btnSave.setDisable(true);
        storeValidations();

        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnid"));
        colCustomerIdR.setCellValueFactory(new PropertyValueFactory<>("customeridR"));
        colItemCodeR.setCellValueFactory(new PropertyValueFactory<>("itemcodeR"));
        colReturnReason.setCellValueFactory(new PropertyValueFactory<>("returnreason"));
        colBilingDateR.setCellValueFactory(new PropertyValueFactory<>("billingdateR"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returndate"));
        colWarrantyDueR.setCellValueFactory(new PropertyValueFactory<>("warrantydueR"));


        try {
            loadReturnItem(new ReturnSaveController().getAllReturnItem());
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


        try {
            loadMobileIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadMobileIDs() throws SQLException, ClassNotFoundException {
        List<String> mobids=new MobileController().getMobileIDs();
        cmbItemCodeR.getItems().addAll(mobids);

    }

    private void loadCusIDs() throws SQLException, ClassNotFoundException {

        List<String> cusIDs=new CustomerSaveController().getCusIDs();
        cmbCustomerIdR.getItems().addAll(cusIDs);
    }

    public void loadReturnItem(ArrayList<ReturnItem> returnItems) {
        ObservableList<ReturnItem> observableList = FXCollections.observableArrayList();
        returnItems.forEach(r -> {
            observableList.add(new ReturnItem(r.getReturnid(), r.getCustomeridR(), r.getItemcodeR(),
                    r.getReturnreason(), r.getBillingdateR(),
                    r.getReturndate(), r.getWarrantydueR()));
            tblReturn.setItems(observableList);
        });
    }

        public void btnSaveRitem (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            ReturnItem r1=new ReturnItem(txtReturnID.getText(),
                    (String) cmbCustomerIdR.getValue(), (String)
                    cmbItemCodeR.getValue(),
                    txtReturnReason.getText(),dpBillingDateR.getValue().toString(),dpReturnDateR.getValue().toString(),txtWarrantyDueR.getText()


            );
            if(new ReturnSaveController().saveReturnItem(r1)){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)) {
                    loadReturnItem(new ReturnSaveController().getAllReturnItem());

                }

               /* loadReturnItem(new ReturnSaveController().getAllReturnItem());

                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();*/
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }

        public void btnUpdateRitem (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


            ReturnItem a=new ReturnItem(txtReturnID.getText(), (String) cmbCustomerIdR.getValue(),(String) cmbItemCodeR.getValue(),txtReturnReason.getText(),dpBillingDateR.getValue().toString(),dpReturnDateR.getValue().toString(),txtWarrantyDueR.getText());



            if (new ReturnSaveController().updateReturnItem(a)) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)) {
                    loadReturnItem(new ReturnSaveController().getAllReturnItem());

                }

         /*       new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();
                loadReturnItem(new ReturnSaveController().getAllReturnItem());*/
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }

        public void btnDeleteRitem (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if (new ReturnSaveController().deleteReturnItem(txtReturnID.getText())) {
                loadReturnItem(new ReturnSaveController().getAllReturnItem());
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
                txtReturnID.clear();

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }

        public void ReturnIdKeyPress (KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                String Iid = txtReturnID.getText();




            }
        }
    private void setData(ReturnItem a1) {
        txtReturnID.setText(a1.getReturnid());
        cmbCustomerIdR.setValue(a1.getCustomeridR());
        cmbItemCodeR.setValue(a1.getItemcodeR());
        txtReturnReason.setText(a1.getReturnreason());
        dpBillingDateR.setValue(LocalDate.parse(a1.getBillingdateR()));
        dpReturnDateR.setValue(LocalDate.parse(a1.getReturndate()));
        txtWarrantyDueR.setText(a1.getWarrantydueR());

    }

    public void KeyPressReturn(KeyEvent keyEvent) {

        Object response = ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void btnSearchId(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ReturnItem R1 = new ReturnSaveController().getReturnItem(txtReturnID.getText());
        if (R1 == null) {

        } else {
            setData(R1);
        }
    }
}



