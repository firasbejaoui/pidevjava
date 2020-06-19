/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.GUI.Front;

import com.Savers.Entities.e_category;
import com.Savers.Entities.event;
import com.Savers.Entities.foundation;
import com.Savers.Services.ServiceE_Category;
import com.Savers.Services.ServiceEvent;
import com.Savers.Services.ServiceFoundation;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField tf_searchE;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateEveData();
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
