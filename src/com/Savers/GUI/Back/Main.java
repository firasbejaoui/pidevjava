/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.GUI.Back;

import javafx.application.Application;
import static javafx.application.Application.launch;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


/**
 *
 * @author Mohamedhabib - pc
 */
public class Main extends Application {
    //define your offsets here
   /* private double xOffset = 0;
    private double yOffset = 0;*/

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLAssociation.fxml"));
            
            Scene scene = new Scene(root);
            
      
       
        primaryStage.setTitle("IngenSchool");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
