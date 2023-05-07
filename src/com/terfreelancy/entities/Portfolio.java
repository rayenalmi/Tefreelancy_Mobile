/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Portfolio {
    private int id_portfolio; 
    private int id_freelancer;
    
    private String intro; 
    private String about; 

    
    
    
    //private List<Contact> contacts = new ArrayList<Contact>();
    //public  List<Skills> skills;
    //private List<Experience> experiences ;

    public Portfolio() {
    }

    public Portfolio(int id_portfolio) {
        this.id_portfolio = id_portfolio;
    }

    public Portfolio(String intro, String about) {
        this.intro = intro;
        this.about = about;
    }

    public Portfolio(int id_freelancer, String intro, String about) {
        this.id_freelancer = id_freelancer;
        this.intro = intro;
        this.about = about;
    }
    
    
    public Portfolio(int id_portfolio, int id_freelancer, String intro, String about) {
        this.id_portfolio = id_portfolio;
        this.id_freelancer = id_freelancer;
        this.intro = intro;
        this.about = about;
    }

    public int getId_portfolio() {
        return id_portfolio;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public String getIntro() {
        return intro;
    }

    public String getAbout() {
        return about;
    }

    public void setId_portfolio(int id_portfolio) {
        this.id_portfolio = id_portfolio;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_portfolio;
        hash = 79 * hash + this.id_freelancer;
        hash = 79 * hash + Objects.hashCode(this.intro);
        hash = 79 * hash + Objects.hashCode(this.about);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Portfolio other = (Portfolio) obj;
        if (this.id_portfolio != other.id_portfolio) {
            return false;
        }
        if (this.id_freelancer != other.id_freelancer) {
            return false;
        }
        if (!Objects.equals(this.intro, other.intro)) {
            return false;
        }
        return Objects.equals(this.about, other.about);
    }
    
   
    
}
