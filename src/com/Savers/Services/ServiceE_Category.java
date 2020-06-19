/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.Services;
import com.Savers.Entities.e_category;
import com.Savers.IService.IService;
import com.Savers.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrStealYourMom
 */
public class ServiceE_Category  implements IService<e_category> {
    private Connection con;
    private Statement ste;

    public ServiceE_Category() {
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
     public void ajouter(e_category c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO e_category ( type ) VALUES ( ?);");
    pre.setString(1, c.getType());
    pre.executeUpdate();
    }
     
    @Override
    public List<e_category> readAll() throws SQLException {
    List<e_category> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from e_category");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String type=rs.getString("type");
               e_category p=new e_category(id, type);
     arr.add(p);
     }
    return arr;
    }
    
    public List<e_category> displayClause(String cls) throws SQLException {
    List<e_category> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from e_category "+cls+" ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String type=rs.getString("type");
               System.out.println(type);
               e_category p=new e_category(id, type);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
    public boolean update(e_category c) throws SQLException {
       PreparedStatement pre=con.prepareStatement("UPDATE  e_category SET type= ? WHERE id= ?;");
    pre.setString(1, c.getType());
    pre.setInt(2, c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
    
    @Override
    public boolean delete(e_category c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM e_category WHERE id= ?");
        pre.setInt(1,c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
}
