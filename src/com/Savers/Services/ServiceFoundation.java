/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.Services;
import com.Savers.Entities.foundation;
import com.Savers.IService.IService;
import com.Savers.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author MrStealYourMom
 */
public class ServiceFoundation  implements IService<foundation> {
    private Connection con;
    private Statement ste;

    public ServiceFoundation() {
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
     public void ajouter(foundation c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO foundation ( categorie,noun,Description,image,updated_at) VALUES ( ?,?,?,?,?);");
    pre.setInt(1, c.getId_Categorie());
    pre.setString(2, c.getNoun());
    pre.setString(3, c.getDescription());
    pre.setString(4, c.getImage());
    pre.setString(5, c.getUpdated_at().toString());
        System.out.println(c.getUpdated_at().toString());
    pre.executeUpdate();
    }
     
    @Override
    public List<foundation> readAll() throws SQLException {
    List<foundation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from foundation ORDER BY noun");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int idC=rs.getInt(2);
               String noun=rs.getString("noun");
               String desc=rs.getString("Description");
               String image=rs.getString("image");
               Date date=rs.getDate(6);
               foundation p=new foundation(id,idC,noun,desc,image,date);
     arr.add(p);
     }
    return arr;
    }
    
    public List<foundation> displayClause(String cls) throws SQLException {
    List<foundation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from foundation "+cls+" ORDER BY noun");
     while (rs.next()) {                
              int id=rs.getInt(1);
               int idC=rs.getInt(2);
               String noun=rs.getString("noun");
               String desc=rs.getString("Description");
               String image=rs.getString("image");
               Date date=rs.getDate(6);
               foundation p=new foundation(id,idC,noun,desc,image,date);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
    public boolean update(foundation c) throws SQLException {
    PreparedStatement pre=con.prepareStatement("UPDATE foundation SET categorie=? ,noun=?, Description=?,image=?,updated_at=? WHERE id=? ");
    pre.setInt(1, c.getId_Categorie());
    pre.setString(2, c.getNoun());
    pre.setString(3, c.getDescription());
    pre.setString(4, c.getImage());
    pre.setDate(5, c.getUpdated_at());
    pre.setInt(6, c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
    
    @Override
    public boolean delete(foundation c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `foundation` WHERE `id`= ?");
        pre.setInt(1,c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
}
