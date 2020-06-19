/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.Services;
import com.Savers.Entities.event;
import com.Savers.IService.IService;
import com.Savers.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrStealYourMom
 */
public class ServiceEvent  implements IService<event> {
    private Connection con;
    private Statement ste;

    public ServiceEvent() {
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
     public void ajouter(event c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `event` ( `categorie`,`foundation`,`noun`,`Description`,`date`,`image`,`updated_at`) VALUES ( ?,?,?,?,?,?,?);");
    pre.setInt(1, c.getId_Category());
    pre.setInt(2, c.getId_Foundation());
    pre.setString(3, c.getNoun());
    pre.setString(4, c.getDescription());
    pre.setString(5, c.getDate().toString());
    pre.setString(6, c.getImage());
    pre.setString(7, c.getUpdated_at().toString());
    pre.executeUpdate();
    }
     
    @Override
    public List<event> readAll() throws SQLException {
    List<event> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idC=rs.getInt(2);
               int idF=rs.getInt(3);
               String noun=rs.getString("noun");
               String desc=rs.getString("Description");
               Date date1=rs.getDate(6);
               String image=rs.getString("image");
               Date date2=rs.getDate(8);
               event p=new event(id, idC,idF,noun,desc,date1,image,date2);
     arr.add(p);
     }
    return arr;
    }
    
    public List<event> displayClause(String cls) throws SQLException {
    List<event> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event "+cls+"");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idC=rs.getInt(2);
               int idF=rs.getInt(3);
               String noun=rs.getString("noun");
               String desc=rs.getString("Description");
               Date date1=rs.getDate(6);
               Date date2=rs.getDate(8);
               String image=rs.getString("image");
               event p=new event(id, idC,idF,noun,desc,date1,image,date2);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
    public boolean update(event c) throws SQLException {
    PreparedStatement pre=con.prepareStatement("UPDATE event SET categorie=? ,foundation=?,noun=?, Description=?, date=? ,image=?,updated_at=?");
    pre.setInt(1, c.getId_Category());
    pre.setInt(2, c.getId_Foundation());
    pre.setString(3, c.getNoun());
    pre.setString(4, c.getDescription());
    pre.setString(5, c.getDate().toString());
    pre.setString(6, c.getImage());
    pre.setString(7, c.getUpdated_at().toString());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
    
    @Override
    public boolean delete(event c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `event` WHERE `id`= ?");
        pre.setInt(1,c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
}
