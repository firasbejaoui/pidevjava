/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.Entities;
import com.Savers.Entities.e_category;
import com.Savers.Entities.foundation;
import java.util.Date;

/**
 *
 * @author MrStealYourMom
 */
public class event {
    private int id;
    private e_category categorie;
    private foundation foundation;
    private String noun;
    private String Description;
    private Date date;
    private String image;
    private Date updated_at;

    public event() {
    }

    public event(int id, e_category categorie, foundation foundation, String noun, String Description, Date date, String image, Date updated_at) {
        this.id = id;
        this.categorie = categorie;
        this.foundation = foundation;
        this.noun = noun;
        this.Description = Description;
        this.date = date;
        this.image = image;
        this.updated_at = updated_at;
    }
    
     public event(int id, int categorie, int foundation, String noun, String Description, Date date, String image, Date updated_at) {
        this.id = id;
        this.categorie=new e_category(categorie,"");
        this.foundation = new foundation();
        this.foundation.setId(foundation);
        this.noun = noun;
        this.Description = Description;
        this.date = date;
        this.image = image;
        this.updated_at = updated_at;
    }
    
    

    public e_category getCategorie() {
        return categorie;
    }
    
    public int getId_Category(){
        return categorie.getId();
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return Description;
    }

    public foundation getFoundation() {
        return foundation;
    }
    
    public int getId_Foundation(){
        return foundation.getId();
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getNoun() {
        return noun;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setCategorie(e_category categorie) {
        this.categorie = categorie;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setFoundation(foundation foundation) {
        this.foundation = foundation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }
    
    public int getId_Categorie(){
        return categorie.getId();
    }
    public String getTypeCategorie(){
        return categorie.getType();
    }
    
    public String getNounFoundation(){
        return foundation.getNoun();
    }
    
    

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
}
