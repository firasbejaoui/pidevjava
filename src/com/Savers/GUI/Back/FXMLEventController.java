/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.GUI.Back;

import com.Savers.Entities.e_category;
import com.Savers.Entities.event;
import com.Savers.Entities.f_category;
import com.Savers.Entities.foundation;
import com.Savers.Services.ServiceE_Category;
import com.Savers.Services.ServiceEvent;
import com.Savers.Services.ServiceF_Category;
import com.Savers.Services.ServiceFoundation;
import com.Savers.Utils.DataBase;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author MrStealYourMom
 */

public class FXMLEventController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<event> dataEve;
    @FXML
    private TableColumn<event, ?> colNoun;
    @FXML
    private TableColumn<event, ?> colCat;
    @FXML
    private TableColumn<event, ?> colFou;
    
    @FXML
    private TableView<e_category> dataCat;
    @FXML
    private TableColumn<e_category, ?> typeCat;
    
    @FXML 
    private TextField tf_catName;
    @FXML 
    private TextField tf_searchCat;
    @FXML
    private ComboBox<e_category> cb_Cat;
    @FXML
    private ComboBox<foundation> cb_asso;
    
    @FXML 
    private TextField tf_nounE;
    @FXML 
    private TextField tf_descE;
    @FXML 
    private TextField tf_searchE;
    @FXML
    private DatePicker dp_dateE;
    @FXML
    private DatePicker dp_dateE1;
    
    private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataBase.getInstance().getConnection();
        updateCatData();
        updateCB_A();
        updateCB();
        updateEveData();
    }    
    
    @FXML
    private void addC(){
        if(tf_catName.getText()!=""){
        try{
            ServiceE_Category SC=new ServiceE_Category();
            e_category tmp=new e_category(0,tf_catName.getText());
            SC.ajouter(tmp);
            
        }catch(Exception ex){
            
        }
        }else{
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Input Invalide");
                alert.showAndWait();
        
        }
        updateCatData();
        updateCB();
    }
    
    private void updateCB(){
    try{
        ServiceE_Category SF=new ServiceE_Category();
        List<e_category> cls=SF.readAll();
        ObservableList<e_category> cls2 = FXCollections.observableArrayList();
        for(e_category tmp : cls){
           cls2.add(tmp);
        }
        cb_Cat.setItems(cls2);
        }catch(Exception ex){
            
        }
    
    }
    private void updateCB_A(){
    try{
        ServiceFoundation SF=new ServiceFoundation();
        List<foundation> cls=SF.readAll();
        ObservableList<foundation> cls2 = FXCollections.observableArrayList();
        for(foundation tmp : cls){
           cls2.add(tmp);
        }
        cb_asso.setItems(cls2);
        }catch(Exception ex){
            
        }
    
    }
    
    @FXML
    private void deleteC(){
        if(dataCat.getSelectionModel().getSelectedIndex()!=-1){
        try{
            ServiceE_Category SC=new ServiceE_Category();
            e_category tmp=new e_category(dataCat.getSelectionModel().getSelectedItem().getId(),tf_catName.getText());
            SC.delete(tmp);
            
        }catch(Exception ex){
            
        }
        }else{
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez Choisir une Categorie");
                alert.showAndWait();
        
        }
        System.out.println("Delete");
        updateCatData();
        updateCB();
    }
    @FXML
    private void updateC(){
        if(dataCat.getSelectionModel().getSelectedIndex()!=-1 && tf_catName.getText()!=""){
        try{
            ServiceE_Category SC=new ServiceE_Category();
            e_category tmp=new e_category(dataCat.getSelectionModel().getSelectedItem().getId(),tf_catName.getText());
            SC.update(tmp);
            
        }catch(Exception ex){
            
        }
        }else{
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Input Invalide");
                alert.showAndWait();
        
        }
        System.out.println(tf_catName.getText());
        updateCatData();
        updateCB();
    }
    private void updateCatData(){
        try {
            ServiceE_Category SC=new ServiceE_Category();
            List<e_category> cls=SC.readAll();
            ObservableList<e_category> cls2 = FXCollections.observableArrayList();
            for(e_category tmp : cls){
                cls2.add(tmp);
            }
            typeCat.setCellValueFactory(new PropertyValueFactory <>("type"));
            dataCat.setItems(cls2);
            
        } catch (Exception e) {
        }
    }
    @FXML
    private void searchC(){
        try{
        ServiceE_Category SF=new ServiceE_Category();
        List<e_category> cls=SF.displayClause("WHERE type LIKE '%"+tf_searchCat.getText()+"%'");
        ObservableList<e_category> cls2 = FXCollections.observableArrayList();
        for(e_category tmp : cls){
           cls2.add(tmp);
            System.out.println(tmp.getType());
        }
        dataCat.setItems(cls2);
        }catch(Exception ex){
            
        }
    
    }
    
    private void updateEveData(){
       try{
        ServiceEvent SF=new ServiceEvent();
        List<event> cls=SF.readAll();
        ServiceE_Category SC=new ServiceE_Category();
        ServiceFoundation Sf=new ServiceFoundation();
        ObservableList<event> cls2 = FXCollections.observableArrayList();
        for(event tmp : cls){
           List<e_category> cls3=SC.displayClause("WHERE id='"+tmp.getId_Categorie()+"'");
           for(e_category tmp2 : cls3){
               List<foundation> cls4=Sf.displayClause("WHERE id='"+tmp.getId_Foundation()+"'");
               for(foundation tmp3 : cls4){
                   tmp.setCategorie(tmp2);
                   tmp.setFoundation(tmp3);
                    cls2.add(tmp);
               }
               
           }
           
        }
        colNoun.setCellValueFactory(new PropertyValueFactory <>("noun"));
        colCat.setCellValueFactory(new PropertyValueFactory <>("TypeCategorie"));
        colFou.setCellValueFactory(new PropertyValueFactory <>("NounFoundation"));
        dataEve.setItems(cls2); 
        }catch(Exception ex){
            
        }
    }
    
    @FXML
    private void addE(ActionEvent event){
        System.out.println("Hello");
        if(tf_nounE.getText()!="" && tf_descE.getText()!="" && dp_dateE.getValue().toString()!=""){
            try{
            Date dt=Date.valueOf(dp_dateE1.getValue());
            Date dt2=Date.valueOf(dp_dateE.getValue());
            ServiceEvent SF=new ServiceEvent();
            event tmp=new event(0,cb_Cat.getSelectionModel().getSelectedItem().getId(),cb_asso.getSelectionModel().getSelectedItem().getId(),tf_nounE.getText(),tf_descE.getText(),dt," ",dt2);
            SF.ajouter(tmp);
            }
            catch(Exception ex){
                
            }
            
        }else{
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Input Invalide");
                alert.showAndWait();
        
        }
        updateEveData();
        
    }
    
    @FXML
    private void updateE(ActionEvent event){
        System.out.println("Hello");
        if(tf_nounE.getText()!="" && tf_descE.getText()!="" && dp_dateE.getValue().toString()!="" && dp_dateE1.getValue().toString()!="" && dataEve.getSelectionModel().getSelectedIndex()!=-1){
            try{
            Date dt=Date.valueOf(dp_dateE1.getValue());
            Date dt2=Date.valueOf(dp_dateE.getValue());
            ServiceEvent SF=new ServiceEvent();
            event tmp=new event(dataEve.getSelectionModel().getSelectedItem().getId(),cb_Cat.getSelectionModel().getSelectedItem().getId(),cb_asso.getSelectionModel().getSelectedItem().getId(),tf_nounE.getText(),tf_descE.getText(),dt," ",dt2);
            SF.update(tmp);
            }
            catch(Exception ex){
                
            }
            
        }else{
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Input Invalide");
                alert.showAndWait();
        
        }
        updateEveData();
        
    }
    
    @FXML
    private void deleteE(){
        System.out.println("Hello");
        if( dataEve.getSelectionModel().getSelectedIndex()!=-1){
            try{
            Date dt=Date.valueOf(dp_dateE1.getValue());
            Date dt2=Date.valueOf(dp_dateE.getValue());
            ServiceEvent SF=new ServiceEvent();
            event tmp=new event(dataEve.getSelectionModel().getSelectedItem().getId(),cb_Cat.getSelectionModel().getSelectedItem().getId(),cb_asso.getSelectionModel().getSelectedItem().getId(),tf_nounE.getText(),tf_descE.getText(),dt," ",dt2);
            SF.delete(tmp);
            }
            catch(Exception ex){
                
            }
            
        }else{
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Input Invalide");
                alert.showAndWait();
        
        }
        updateEveData();
    }
    @FXML
    private void searchE(){
        try{
        ServiceEvent SF=new ServiceEvent();
        List<event> cls=SF.displayClause("WHERE noun LIKE'%"+tf_searchE.getText()+"%' OR date LIKE'%"+tf_searchE.getText()+"%' OR updated_at LIKE'%"+tf_searchE.getText()+"%'");
        ServiceE_Category SC=new ServiceE_Category();
        ServiceFoundation Sf=new ServiceFoundation();
        ObservableList<event> cls2 = FXCollections.observableArrayList();
        for(event tmp : cls){
           List<e_category> cls3=SC.displayClause("WHERE id='"+tmp.getId_Categorie()+"'");
           for(e_category tmp2 : cls3){
               List<foundation> cls4=Sf.displayClause("WHERE id='"+tmp.getId_Foundation()+"'");
               for(foundation tmp3 : cls4){
                   tmp.setCategorie(tmp2);
                   tmp.setFoundation(tmp3);
                    cls2.add(tmp);
               }
               
           }
           
        }
        colNoun.setCellValueFactory(new PropertyValueFactory <>("noun"));
        colCat.setCellValueFactory(new PropertyValueFactory <>("TypeCategorie"));
        colFou.setCellValueFactory(new PropertyValueFactory <>("NounFoundation"));
        dataEve.setItems(cls2); 
        }catch(Exception ex){
            
        }
    }
    @FXML
    private void print(){
        try{
        ServiceEvent SF=new ServiceEvent();
        List<event> cls=SF.readAll();
        ServiceE_Category SC=new ServiceE_Category();
        ServiceFoundation Sf=new ServiceFoundation();
        ObservableList<event> cls2 = FXCollections.observableArrayList();
        for(event tmp : cls){
           List<e_category> cls3=SC.displayClause("WHERE id='"+tmp.getId_Categorie()+"'");
           for(e_category tmp2 : cls3){
               List<foundation> cls4=Sf.displayClause("WHERE id='"+tmp.getId_Foundation()+"'");
               for(foundation tmp3 : cls4){
                   tmp.setCategorie(tmp2);
                   tmp.setFoundation(tmp3);
                    cls2.add(tmp);
               }
               
           }
           
        }
            System.out.println("bye");
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
                        File saveFile = fileChooser.showSaveDialog(dataEve.getScene().getWindow());
                        OutputStream file= new FileOutputStream(new File(saveFile.getAbsolutePath()));
                        Document document = new Document();
                        PdfWriter.getInstance(document,file);
                        document.open();
                        pdf.addMetaData2(document);
                        pdf.addTitlePage2(document, cls);
                        document.close(); 
                        file.close();
          }
        catch(Exception ex)
        {
            
        }
    }
}
