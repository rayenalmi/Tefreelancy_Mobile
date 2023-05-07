/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import com.codename1.ui.ComboBox;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Skill {
     private int id_skill; 
    
    private int id_freelancer; 

    private String name;  
     
    public enum Level {Fundamental_Awareness, Novice , Intermediate ,Advanced ,Expert }; 
    //private Level level;
    
    private String level;
    
    //String[] items = {"Option 1", "Option 2", "Option 3"};
    //ComboBox<String> comboBox = new ComboBox<>(items);

    public Skill() {
    }

    public Skill(int id_skill) {
        this.id_skill = id_skill;
    }

    public Skill(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public Skill(int id_freelancer, String name, String level) {
        this.id_freelancer = id_freelancer;
        this.name = name;
        this.level = level;
    }

    public Skill(int id_skill, int id_freelancer, String name, String level) {
        this.id_skill = id_skill;
        this.id_freelancer = id_freelancer;
        this.name = name;
        this.level = level;
    }

    public int getId_skill() {
        return id_skill;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public void setId_skill(int id_skill) {
        this.id_skill = id_skill;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.id_skill;
        hash = 43 * hash + this.id_freelancer;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.level);
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
        final Skill other = (Skill) obj;
        if (this.id_skill != other.id_skill) {
            return false;
        }
        if (this.id_freelancer != other.id_freelancer) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return this.level == other.level;
    }

    @Override
    public String toString() {
        return "Skill{" + "id_skill=" + id_skill + ", id_freelancer=" + id_freelancer + ", name=" + name + ", level=" + level + '}';
    }

    
    
    
}
