/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.GUI.Back;

import com.Savers.Entities.f_category;
import com.Savers.Entities.foundation;
import com.Savers.Services.ServiceF_Category;
import com.Savers.Services.ServiceFoundation;
import com.Savers.Utils.DataBase;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import com.Savers.GUI.Back.pdf;
/**
 * FXML Controller class
 *
 */
public class FXMLAssociationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tf_nomAsso;
    @FXML
    private TextField tf_descAsso;
    @FXML
    private ComboBox<f_category> cb_Cat;
    @FXML
    private Label imgLabel;
    @FXML
    private Button openImg;
    @FXML
    private DatePicker dp_dateAsso;
    @FXML 
    private TextField tf_searchFoun;
    @FXML
    private TableView<foundation> dataFoun;
    @FXML
    private TableColumn<foundation, ?> colNoun;
    @FXML
    private TableColumn<foundation, ?> colCat;
    @FXML
    private TableColumn<foundation, ?> colDate;
    
    
    @FXML
    private TableView<f_category> dataCat;
    @FXML
    private TableColumn<f_category, ?> typeCat;
    
    @FXML 
    private TextField tf_catName;
    @FXML 
    private TextField tf_searchCat;
    private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataBase.getInstance().getConnection();
        updateFouData();
        updateCatData();
        updateCB();
    }
    
    
    
    @FXML
    private void addC(){
        if(tf_catName.getText()!=""){
        try{
            ServiceF_Category SC=new ServiceF_Category();
            f_category tmp=new f_category(0,tf_catName.getText());
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
    }
    
    private void updateCB(){
    try{
        ServiceF_Category SF=new ServiceF_Category();
        List<f_category> cls=SF.readAll();
        ObservableList<f_category> cls2 = FXCollections.observableArrayList();
        for(f_category tmp : cls){
           cls2.add(tmp);
            System.out.println(tmp.getType());
        }
        cb_Cat.setItems(cls2);
        }catch(Exception ex){
            
        }
    
    }
    
    @FXML
    private void deleteC(){
        if(dataCat.getSelectionModel().getSelectedIndex()!=-1){
        try{
            ServiceF_Category SC=new ServiceF_Category();
            f_category tmp=new f_category(dataCat.getSelectionModel().getSelectedItem().getId(),tf_catName.getText());
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
    }
    @FXML
    private void updateC(){
        if(dataCat.getSelectionModel().getSelectedIndex()!=-1 && tf_catName.getText()!=""){
        try{
            ServiceF_Category SC=new ServiceF_Category();
            f_category tmp=new f_category(dataCat.getSelectionModel().getSelectedItem().getId(),tf_catName.getText());
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
            ServiceF_Category SC=new ServiceF_Category();
            List<f_category> cls=SC.readAll();
            ObservableList<f_category> cls2 = FXCollections.observableArrayList();
            for(f_category tmp : cls){
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
        ServiceF_Category SF=new ServiceF_Category();
        List<f_category> cls=SF.displayClause("WHERE type LIKE '%"+tf_searchCat.getText()+"%'");
        ObservableList<f_category> cls2 = FXCollections.observableArrayList();
        for(f_category tmp : cls){
           cls2.add(tmp);
            System.out.println(tmp.getType());
        }
        dataCat.setItems(cls2);
        }catch(Exception ex){
            
        }
    
    }
    
    private void updateFouData(){
       try{
        ServiceFoundation SF=new ServiceFoundation();
        List<foundation> cls=SF.readAll();
        ServiceF_Category SC=new ServiceF_Category();
        ObservableList<foundation> cls2 = FXCollections.observableArrayList();
        for(foundation tmp : cls){
           List<f_category> cls3=SC.displayClause("WHERE id='"+tmp.getId_Categorie()+"'");
           for(f_category tmp2 : cls3){
               tmp.setCategorie(tmp2);
               cls2.add(tmp);
           }
           
        }
        colNoun.setCellValueFactory(new PropertyValueFactory <>("noun"));
        colCat.setCellValueFactory(new PropertyValueFactory <>("TypeCategorie"));
        colDate.setCellValueFactory(new PropertyValueFactory <>("Updated_at"));
        dataFoun.setItems(cls2); 
        }catch(Exception ex){
            
        }
    }
    
    @FXML
    private void addF(ActionEvent event){
        System.out.println("Hello");
        if(tf_nomAsso.getText()!="" && tf_descAsso.getText()!="" && imgLabel.getText()!="Choisir une Image" && dp_dateAsso.getValue().toString()!=""){
            try{
            Date dt=Date.valueOf(dp_dateAsso.getValue());
            ServiceFoundation SF=new ServiceFoundation();
            foundation tmp=new foundation(0,cb_Cat.getSelectionModel().getSelectedItem().getId(),tf_nomAsso.getText(),tf_descAsso.getText(),imgLabel.getText(),dt);
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
        updateFouData();
        
    }
    
    @FXML
    private void updateF(){
        System.out.println("Hello");
        if(tf_nomAsso.getText()!="" && tf_descAsso.getText()!="" && imgLabel.getText()!="xx" && dataFoun.getSelectionModel().getSelectedIndex()!=-1){
            try{
            Date dt=Date.valueOf(dp_dateAsso.getValue());
            ServiceFoundation SF=new ServiceFoundation();
            foundation tmp=new foundation(dataFoun.getSelectionModel().getSelectedItem().getId(),cb_Cat.getSelectionModel().getSelectedItem().getId(),tf_nomAsso.getText(),tf_descAsso.getText(),imgLabel.getText(),dt);
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
        updateFouData();
    }
    
    @FXML
    private void deleteF(){
        System.out.println("Hello");
        if( dataFoun.getSelectionModel().getSelectedIndex()!=-1){
            try{
            Date dt=Date.valueOf(dp_dateAsso.getValue());
            ServiceFoundation SF=new ServiceFoundation();
            foundation tmp=new foundation(dataFoun.getSelectionModel().getSelectedItem().getId(),cb_Cat.getSelectionModel().getSelectedItem().getId(),tf_nomAsso.getText(),tf_descAsso.getText(),imgLabel.getText(),dt);
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
        updateFouData();
    }
    
    @FXML
    private void searchF(){
        try{
        ServiceFoundation SF=new ServiceFoundation();
        List<foundation> cls=SF.displayClause("WHERE noun LIKE '%"+tf_searchFoun.getText()+"%' OR updated_at LIKE '%"+tf_searchFoun.getText()+"%' ");
        ServiceF_Category SC=new ServiceF_Category();
        ObservableList<foundation> cls2 = FXCollections.observableArrayList();
        for(foundation tmp : cls){
           List<f_category> cls3=SC.displayClause("WHERE id='"+tmp.getId_Categorie()+"'");
           for(f_category tmp2 : cls3){
               tmp.setCategorie(tmp2);
               cls2.add(tmp);
           }
           
        }
        dataFoun.setItems(cls2); 
        }catch(Exception ex){
            
        }
    }
    @FXML
    private void openImg(){
        try{
        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                        new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg"),
                        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png")
                        );
                        File saveFile = fileChooser.showOpenDialog(dataFoun.getScene().getWindow());
                        System.out.println(saveFile.getName());
                        imgLabel.setText(saveFile.getName());
                        File output = new File("./src/uploads/"+saveFile.getName());
                        Files.copy(saveFile.toPath(),output.toPath());
        }
                        catch(Exception ex)
        {
            
        }
    }
    
    @FXML
    private void print(){
        try{
        ServiceFoundation SF=new ServiceFoundation();
        List<foundation> cls=SF.readAll();
        ServiceF_Category SC=new ServiceF_Category();
        ObservableList<foundation> cls2 = FXCollections.observableArrayList();
        for(foundation tmp : cls){
           List<f_category> cls3=SC.displayClause("WHERE id='"+tmp.getId_Categorie()+"'");
           for(f_category tmp2 : cls3){
               tmp.setCategorie(tmp2);
               cls2.add(tmp);
           }
           
        }
            System.out.println("bye");
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
                        File saveFile = fileChooser.showSaveDialog(dataFoun.getScene().getWindow());
                        OutputStream file= new FileOutputStream(new File(saveFile.getAbsolutePath()));
                        Document document = new Document();
                        PdfWriter.getInstance(document,file);
                        document.open();
                        pdf.addMetaData(document);
                        pdf.addTitlePage(document, cls);
                        document.close(); 
                        file.close();
          }
        catch(Exception ex)
        {
            
        }
    }
    
    
}
    
    

