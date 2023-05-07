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
public class Formation {
    int id;
    String name;
    int nbH;
    int nbL;
    String path;

    public Formation(String name, int nbH, int nbL) {
        this.name = name;
        this.nbH = nbH;
        this.nbL = nbL;
    }
    
    public Formation(int id, String name, int nbH, int nbL) {
        this.id = id;
        this.name = name;
        this.nbH = nbH;
        this.nbL = nbL;
    }

    public Formation(int id, String name, int nbH, int nbL, String path) {
        this.id = id;
        this.name = name;
        this.nbH = nbH;
        this.nbL = nbL;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public int getNbH() {
        return nbH;
    }

    public void setNbH(int nbH) {
        this.nbH = nbH;
    }

    public int getNbL() {
        return nbL;
    }

    public void setNbL(int nbL) {
        this.nbL = nbL;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", name=" + name + ", nbH=" + nbH + ", nbL=" + nbL + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + this.nbH;
        hash = 71 * hash + this.nbL;
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
        final Formation other = (Formation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbH != other.nbH) {
            return false;
        }
        if (this.nbL != other.nbL) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    
}