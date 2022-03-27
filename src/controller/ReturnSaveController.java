package controller;

import DB.DbConnection;
import model.Accessories;
import model.ReturnItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnSaveController {

    public boolean saveReturnItem(ReturnItem R) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT into Return_Item VALUES (?,?,?,?,?,?,?)");
        Stm.setObject(1, R.getReturnid());
        Stm.setObject(2, R.getCustomeridR());
        Stm.setObject(3, R.getItemcodeR());
        Stm.setObject(4, R.getReturnreason());
        Stm.setObject(5, R.getBillingdateR());
        Stm.setObject(6, R.getReturndate());
        Stm.setObject(7, R.getWarrantydueR());


        return Stm.executeUpdate() > 0;
    }
    public ArrayList<ReturnItem> getAllReturnItem() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Return_Item");
        ResultSet resultSet=stm.executeQuery();
        ArrayList<ReturnItem>returnItems=new ArrayList<>();
        while (resultSet.next()){
            returnItems.add(new ReturnItem(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));
        }
        return returnItems;

    }

    public boolean updateReturnItem(ReturnItem R) throws SQLException, ClassNotFoundException {
        PreparedStatement Stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Return_Item SET customeridR=?,itemcodeR=?,returnreason=?,billingdateR=?,returndate=?,warrantydueR=? WHERE returnid=?");

        Stm.setObject(1,R.getCustomeridR());
        Stm.setObject(2,R.getItemcodeR());
        Stm.setObject(3,R.getReturnreason());
        Stm.setObject(4,R.getBillingdateR());
        Stm.setObject(5,R.getReturndate());
        Stm.setObject(6,R.getWarrantydueR());
        Stm.setObject(7,R.getReturnid());

      /*  returnid         VARCHAR(10) NOT NULL,
        customeridR   VARCHAR(15),
                itemcodeR     VARCHAR(15),
                returnreason     VARCHAR(45),
                billingdateR       VARCHAR(15),
                returndate     VARCHAR(25),
                warrantydueR       VARCHAR(25),*/

        return Stm.executeUpdate()>0;


    }

    public boolean deleteReturnItem(String returnid) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Return_Item WHERE returnid='"+returnid+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public ReturnItem getReturnItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Return_Item WHERE rid=?");
        preparedStatement.setObject(1, id);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new ReturnItem(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );

        } else {
            return null;
        }
    }
}
