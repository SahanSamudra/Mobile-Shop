package controller;

import model.Customer;
import model.Supplier;

import java.sql.SQLException;

public interface SupplierDetails {
    boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException;
}
