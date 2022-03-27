package controller;

import DB.DbConnection;
import model.Accessories;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessoriesController {



    public boolean accessories(Accessories A) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT into Item VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Stm.setObject(1, A.getAid());
        Stm.setObject(2, A.getAmodel());
        Stm.setObject(3, A.getAimei());
        Stm.setObject(4, A.getAbrand());
        Stm.setObject(5, A.getAtype());
        Stm.setObject(6, A.getAqty());
        Stm.setObject(7, A.getAbprice());
        Stm.setObject(8, A.getAtotalcost());
        Stm.setObject(9, A.getAsprice());
        Stm.setObject(10, A.getAdescription());
        Stm.setObject(11, A.getAsid());
        Stm.setObject(12, A.getAbillnum());
        Stm.setObject(13, A.getAbdate());

        return Stm.executeUpdate() > 0;
    }

    public ArrayList<Accessories> getAllAccessories() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE type=?");
        stm.setObject(1,"Accessories");
        ResultSet resultSet=stm.executeQuery();
        ArrayList<Accessories>accessories=new ArrayList<>();
        while (resultSet.next()){
            accessories.add(new Accessories(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getDouble(7),resultSet.getDouble(8),
                    resultSet.getDouble(9),resultSet.getString(10),
                    resultSet.getString(11),resultSet.getString(12),resultSet.getString(13)));
        }
        return accessories;

    }





    public boolean updateAccessories(Accessories A) throws SQLException, ClassNotFoundException {
       PreparedStatement Stm= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET   model=?, imei=?, brand=?, type=?, qty=?, bprice=?, tcost=?, sprice=?, description=?, sid=?, billnum=?, bdate=? WHERE Iid=? ");

        Stm.setObject(1,A.getAmodel());
        Stm.setObject(2,A.getAimei());
        Stm.setObject(3,A.getAbrand());
        Stm.setObject(4,A.getAtype());
        Stm.setObject(5,A.getAqty());
        Stm.setObject(6,A.getAbprice());
        Stm.setObject(7,A.getAtotalcost());
        Stm.setObject(8,A.getAsprice());
        Stm.setObject(9,A.getAdescription());
        Stm.setObject(10,A.getAsid());
        Stm.setObject(11,A.getAbillnum());
        Stm.setObject(12,A.getAbdate());
        Stm.setObject(13,A.getAid());

        return Stm.executeUpdate()>0;


    }

    public boolean deleteAccessories(String Iid) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE Iid='"+Iid+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public Accessories getAccessories(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE Iid=?");
        preparedStatement.setObject(1, id);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new Accessories(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
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
