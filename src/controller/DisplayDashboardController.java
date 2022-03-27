package controller;

import DB.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;

public class DisplayDashboardController {
    public Label lblCustomer;
    public Label lblSupplierCount;
    public Label lblRepairCount;
    public Label lblOrder;
    public Label lblProducts;
    public javafx.scene.chart.PieChart PieChart;
    public AreaChart areaChart;
    public Label lblSoldItem;
    public javafx.scene.chart.PieChart mainChart;





    private void loardChart() throws SQLException, ClassNotFoundException {
        PreparedStatement prst = DbConnection.getInstance().getConnection().prepareStatement("select * from Item WHERE `type`=?");
        prst.setObject(1,"Mobile");
        ResultSet resultSet = prst.executeQuery();
        int accessories=0;
        int mobile=0;

        while (resultSet.next()){
            accessories+=Integer.parseInt(resultSet.getString(6));
        }
        prst = DbConnection.getInstance().getConnection().prepareStatement("select * from Item WHERE `type`= ?");
        prst.setObject(1,"Accessories");
        resultSet = prst.executeQuery();
        while (resultSet.next()){
            mobile+=Integer.parseInt(resultSet.getString(6));
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Accessories", accessories),
                        new PieChart.Data("Mobile", mobile));

        mainChart.setData(pieChartData);

    }

    private void soldItem () throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT \n" +
                                "    SUM(itemcount) qty\n" +
                                "FROM\n" +
                                "    Item_Reports")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            lblSoldItem.setText(String.valueOf(customerCount));
        }
    }

    public void loadPieChart() throws SQLException, ClassNotFoundException {
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = observableArrayList(
                new PieChart.Data("January", 13),
                new PieChart.Data("February", 25),
                new PieChart.Data("March", 10),
                new PieChart.Data("April", 43),
                new PieChart.Data("April", 25),
                new PieChart.Data("May", 22),
                new PieChart.Data("June", 12),
                new PieChart.Data("July", 82),
                new PieChart.Data("August", 22),
                new PieChart.Data("September", 32),
                new PieChart.Data("October", 24),
                new PieChart.Data("November", 22),
                new PieChart.Data("December", 22));
        PieChart.setData(pieChartData);

        XYChart.Series series =new XYChart.Series();
        series.getData().add(new XYChart.Data("1",23));
        series.getData().add(new XYChart.Data("2",65));
        series.getData().add(new XYChart.Data("3",68));
        series.getData().add(new XYChart.Data("4",32));
        series.getData().add(new XYChart.Data("5",56));
        series.getData().add(new XYChart.Data("6",76));
        series.getData().add(new XYChart.Data("7",44));
        areaChart.getData().add(series);

    }

    public void initialize(){


        try {
            loardChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            soldItem();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            loadPieChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ProductsCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            OrderCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            RepairCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            SupplierCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            CustomerCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void RepairCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("Select * From Repair_Item");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblRepairCount.setText(String.valueOf(a));
        }

    }

    public void CustomerCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("Select * From Customer");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblCustomer.setText(String.valueOf(a));
        }

    }

    public void SupplierCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("Select * From Supplier");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblSupplierCount.setText(String.valueOf(a));
        }

    }

    public void OrderCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("Select * From Orders");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblOrder.setText(String.valueOf(a));
        }

    }



    private static class DBConnection {
    }

    public void ProductsCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("Select * From Item");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblProducts.setText(String.valueOf(a));
        }

    }
}
