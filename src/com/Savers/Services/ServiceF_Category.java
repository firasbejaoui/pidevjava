/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.Services;
import com.Savers.Entities.f_category;
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
public class ServiceF_Category  implements IService<f_category> {
    private Connection con;
    private Statement ste;

    public ServiceF_Category() {
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
     public void ajouter(f_category c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `f_category` ( `type`) VALUES ( ?);");
    pre.setString(1, c.getType());
    pre.executeUpdate();
    }
     
    @Override
    public List<f_category> readAll() throws SQLException {
    List<f_category> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from f_category ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String type=rs.getString("type");
               f_category p=new f_category(id, type);
     arr.add(p);
     }
    return arr;
    }
    
    public List<f_category> displayClause(String cls) throws SQLException {
    List<f_category> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from f_category "+cls);
     while (rs.next()) {                
               int id=rs.getInt(1);
               String type=rs.getString("type");
               f_category p=new f_category(id, type);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
    public boolean update(f_category c) throws SQLException {
    PreparedStatement pre=con.prepareStatement("UPDATE f_category SET type= ? WHERE id= ?");
    pre.setString(1, c.getType());
    pre.setInt(2, c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
    
    @Override
    public boolean delete(f_category c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM f_category WHERE id= ?");
        System.out.println("Delete");
        pre.setInt(1,c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }
}
