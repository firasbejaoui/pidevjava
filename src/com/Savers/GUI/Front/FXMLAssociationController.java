/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.GUI.Front;

import com.Savers.Entities.f_category;
import com.Savers.Entities.foundation;
import com.Savers.Services.ServiceF_Category;
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
import javafx.scene.control.ListView;
import com.jfoenix.controls.JFXListView;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import rsscreator.ui.main.FXMLDocumentController;
import static rsscreator.ui.main.FXMLDocumentController.createFeedListItem;
import rsscreator.ui.resourses.FeedMessage;
import rsscreator.ui.resourses.filesHandler;

/**
 * FXML Controller class
 *
 * @author MrStealYourMom
 */
public class FXMLAssociationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<foundation> data;
    @FXML
    private TableColumn<foundation, ?> colNoun;
    @FXML
    private TableColumn<foundation, ?> colDesc;
    @FXML
    private TextField tf_search;
    @FXML
    private JFXListView<TextFlow> listView;
    public static JFXListView<TextFlow> myList;
    
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        listView.setExpanded(true);
        listView.depthProperty().set(1);
        myList = listView;
        try {
                URL url = new URL("http://feeds.bbci.co.uk/news/world/rss.xml");
                filesHandler.importFrom(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        updateFouData();
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
        colDesc.setCellValueFactory(new PropertyValueFactory <>("Description"));
        data.setItems(cls2); 
        }catch(Exception ex){
            
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
                        File saveFile = fileChooser.showSaveDialog(data.getScene().getWindow());
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
    @FXML
    private void search(){
        try{
        ServiceFoundation SF=new ServiceFoundation();
        List<foundation> cls=SF.displayClause("WHERE noun LIKE '%"+tf_search.getText()+"%' OR updated_at LIKE '%"+tf_search.getText()+"%' ");
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
        colDesc.setCellValueFactory(new PropertyValueFactory <>("Description"));
        data.setItems(cls2); 
        }catch(Exception ex){
            
        }
    }
    public static void createList(){
        if (filesHandler.feed==null)return;
        myList.getItems().clear();
        for (FeedMessage msg:filesHandler.feed.getEntries()){
            myList.getItems().add(createFeedListItem(msg));
        }
    }
    
    
}
