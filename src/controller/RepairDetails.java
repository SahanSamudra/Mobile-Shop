package controller;

import model.Repair;
import model.Supplier;

import java.sql.SQLException;

public interface RepairDetails {

    boolean saveRepair(Repair repair) throws SQLException,ClassNotFoundException;
}
