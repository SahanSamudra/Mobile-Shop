package controller;

import DB.DbConnection;
import model.Mobile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MobileController {

    public List<String> getMobileIDs() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst=stm.executeQuery();
        List<String> ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    public boolean savemobile(Mobile M) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm= DbConnection.getInstance().getConnection().prepareStatement("INSERT into Item VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Stm.setObject(1,M.getMid());
        Stm.setObject(2,M.getModel());
        Stm.setObject(3,M.getImei());
        Stm.setObject(4,M.getBrand());
        Stm.setObject(5,M.getType());
        Stm.setObject(6,M.getQty());
        Stm.setObject(7,M.getBprice());
        Stm.setObject(8,M.getTcost());
        Stm.setObject(9,M.getSprice());
        Stm.setObject(10,M.getDescription());
        Stm.setObject(11,M.getSid());
        Stm.setObject(12,M.getBillNumber());
        Stm.setObject(13,M.getBillDate());

        return Stm.executeUpdate()>0;
    }
    public ArrayList<Mobile> getAllMobile() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item  WHERE type=?");
        stm.setObject(1,"Mobile");
        ResultSet rst=stm.executeQuery();
        ArrayList<Mobile>mobiles=new ArrayList<>();
        while (rst.next()){
            mobiles.add(new Mobile(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getString(5),rst.getInt(6),rst.getDouble(7),rst.getDouble(8),rst.getDouble(9),
                    rst.getString(10),rst.getString(11),rst.getString(12),rst.getString(13)));



        }
        return mobiles;
    }

    public boolean updateMobile(Mobile M) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET   model=?, imei=?, brand=?, type=?, qty=?, bprice=?, tcost=?, sprice=?, description=?, sid=?, billnum=?, bdate=? WHERE Iid=? ");

        Stm.setObject(1,M.getModel());
        Stm.setObject(2,M.getImei());
        Stm.setObject(3,M.getBrand());
        Stm.setObject(4,M.getType());
        Stm.setObject(5,M.getQty());
        Stm.setObject(6,M.getBprice());
        Stm.setObject(7,M.getTcost());
        Stm.setObject(8,M.getSprice());
        Stm.setObject(9,M.getDescription());
        Stm.setObject(10,M.getSid());
        Stm.setObject(11,M.getBillNumber());
        Stm.setObject(12,M.getBillDate());
        Stm.setObject(13,M.getMid());

        return Stm.executeUpdate()>0;
    }

    public boolean deleteMobile(String Iid) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE Iid='"+Iid+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public Mobile getMobile(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE Iid=?");
        preparedStatement.setObject(1, id);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new Mobile(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(4),
                    rst.getString(3),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getDouble(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)
            );

        } else {
            return null;
        }
    }



}
