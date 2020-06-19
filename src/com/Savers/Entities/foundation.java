/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.Entities;
import com.Savers.Entities.f_category;
import java.sql.Date;

/**
 *
 * @author MrStealYourMom
 */
public class foundation {
    private int id;
    private f_category categorie;
    private String noun;
    private String Description;
    private String Image;
    private Date updated_at;

    public foundation() {
    }

    public foundation(int id, f_category categorie, String noun, String Description, String Image, Date updated_at) {
        this.id = id;
        this.categorie = categorie;
        this.noun = noun;
        this.Description = Description;
        this.Image = Image;
        this.updated_at = updated_at;
    }
    
    public foundation(int id, int categorie, String noun, String Description, String Image, Date updated_at) {
        this.id = id;
        this.categorie = new f_category();
        this.categorie.setId(categorie);
        this.noun = noun;
        this.Description = Description;
        this.Image = Image;
        this.updated_at = updated_at;
    }
    public int getId() {
        return id;
    }
    
    public f_category getCategorie() {
        return categorie;
    }
    
    public int getId_Categorie(){
        return categorie.getId();
    }
    public String getTypeCategorie(){
        return categorie.getType();
    }

    public String getNoun() {
        return noun;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategorie(f_category categorie) {
        this.categorie = categorie;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return noun; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
     
    
}
