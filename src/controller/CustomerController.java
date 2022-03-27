package controller;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Mobile;
import model.Supplier;
import util.ValidationUtil;
import views.tm.CustomerTm;
import views.tm.MobileTm;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerController {


    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusContact;
    public JFXTextField tctCustomerId;
    public TableColumn colCustomerId;
    public TableColumn colCusName;
    public TableColumn colCusContact;
    public TableColumn colCusAddress;
    public AnchorPane loadBillingContext;
    public TableView tblCustomers;
    public Button btnSave;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(C0-)[0-9]{1,3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,40}$");
    Pattern contactPattern = Pattern.compile("^(071|072|073|074|070|075|076|077|078|079)[-]?[0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");

    private void storeValidations() {
        map.put(tctCustomerId, idPattern);
        map.put(txtCusName, namePattern);
        map.put(txtCusContact, contactPattern);
        map.put(txtCusAddress,addressPattern);
    }

    public void initialize() {
        btnSave.setDisable(true);
        storeValidations();

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCusContact.setCellValueFactory(new PropertyValueFactory<>("noOfContact"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));



        try {
            loadCustomers(new CustomerSaveController().getAllCustomer());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void btnCustomerSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new Customer(tctCustomerId.getText(),
                txtCusName.getText(),
                txtCusContact.getText(),
                txtCusAddress.getText()


        );
        if (new CustomerSaveController().saveCustomer(c1)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadCustomers(new CustomerSaveController().getAllCustomer());

            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void loadCustomers(ArrayList<Customer> customers) {
        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
        customers.forEach(c -> {
            observableList.add(new CustomerTm(c.getCustomerId(), c.getCustomerName(), c.getCustomerAddress(), c.getNoOfContact()));
            tblCustomers.setItems(observableList);
        });

    }

    public void btnCustomerUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c = new Customer(tctCustomerId.getText(), txtCusName.getText(), txtCusContact.getText(), txtCusAddress.getText());


        if (new CustomerSaveController().updateCustomer(c)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadCustomers(new CustomerSaveController().getAllCustomer());

            }


        } else {
            loadCustomers(new CustomerSaveController().getAllCustomer());
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnCustomerDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (new CustomerSaveController().deleteCustomer(tctCustomerId.getText())) {

            loadCustomers(new CustomerSaveController().getAllCustomer());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
            tctCustomerId.clear();


        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void CustomerKeyPress(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String cid = tctCustomerId.getText();

            Customer C1 = new CustomerSaveController().getCustomer(tctCustomerId.getText());
            if (C1 == null) {

                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            } else {
                setData(C1);
            }
        }
    }

    private void setData(Customer c1) {
        tctCustomerId.setText(c1.getCustomerId());
        txtCusName.setText(c1.getCustomerName());
        txtCusContact.setText(c1.getNoOfContact());
        txtCusAddress.setText(c1.getCustomerAddress());


    }

    public void txtKeyPress(KeyEvent keyEvent) {

        Object response = ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }

    }

    public void btnSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer C1 = new CustomerSaveController().getCustomer(tctCustomerId.getText());
        if (C1 == null) {

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(C1);
        }


    }
}
