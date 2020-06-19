/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.Entities;

/**
 *
 * @author MrStealYourMom
 */
public class f_category {
    private int id;
    private String type;

    public f_category() {
    }

    public f_category(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.type; //To change body of generated methods, choose Tools | Templates.
    }
    
}
