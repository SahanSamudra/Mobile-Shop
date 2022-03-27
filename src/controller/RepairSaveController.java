package controller;

import DB.DbConnection;
import model.Repair;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class RepairSaveController implements RepairDetails{



        @Override
        public boolean saveRepair(Repair repair) throws SQLException, ClassNotFoundException {

            Connection connection = DbConnection.getInstance().getConnection();
            String query = "INSERT INTO  Repair_Item VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, repair.getRid());
            preparedStatement.setObject(2, repair.getRcid());
            preparedStatement.setObject(3, repair.getImeiR());
            preparedStatement.setObject(4, repair.getBrandModel());
            preparedStatement.setObject(5, repair.getFaultType());
            preparedStatement.setObject(6, repair.getFault());
            preparedStatement.setObject(7, repair.getBringDate());
            preparedStatement.setObject(8, repair.getDaytoGive());


            return preparedStatement.executeUpdate() > 0;

        }

    public ArrayList<Repair> getAllRepair() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Repair_Item");
        ResultSet rst=stm.executeQuery();
        ArrayList<Repair>repairs=new ArrayList<>();
        while (rst.next()){
            repairs.add(new Repair(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8)));
        }
        return repairs;
    }
    public boolean updateRepair(Repair R) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Repair_Item SET   rcid=?, imeir=?, brandmodel=?, faulttype=?, fault=?,bringdate=?, daytogive=? WHERE rid=? ");

        Stm.setObject(1,R.getRcid());
        Stm.setObject(2,R.getImeiR());
        Stm.setObject(3,R.getBrandModel());
        Stm.setObject(4,R.getFaultType());
        Stm.setObject(5,R.getFault());
        Stm.setObject(6,R.getBringDate());
        Stm.setObject(7,R.getDaytoGive());
        Stm.setObject(8,R.getRid());

        return Stm.executeUpdate()>0;
    }

    public boolean deleteRepair(String rid) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Repair_Item WHERE rid='"+rid+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public Repair getRepair(String rid) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Repair_Item WHERE rid=?");
        preparedStatement.setObject(1, rid);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new Repair(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)

            );

        } else {
            return null;
        }
    }




}
