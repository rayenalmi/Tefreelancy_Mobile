/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import java.util.Objects;

/**
 *
 * @author ROG
 */
public class Chapter {
    int id;
    String name;
    String context;
    int formation_id;

    public Chapter(String name, String context, int formation_id) {
        this.name = name;
        this.context = context;
        this.formation_id = formation_id;
    }
    
    public Chapter(int id, String name, String context, int formation_id) {
        this.id = id;
        this.name = name;
        this.context = context;
        this.formation_id = formation_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getFormation_id() {
        return formation_id;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    @Override
    public String toString() {
        return "Chapter{" + "id=" + id + ", name=" + name + ", context=" + context + ", formation_id=" + formation_id + '}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.context);
        hash = 29 * hash + this.formation_id;
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
        final Chapter other = (Chapter) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.formation_id != other.formation_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.context, other.context);
    }
    
    
}