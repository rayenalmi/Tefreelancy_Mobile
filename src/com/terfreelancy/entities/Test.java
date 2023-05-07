/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import java.util.Objects;

/**
 *
 * @author yassi
 */
public class Test {
    private int id_test;
    private String name;
    private String type;
    
    public Test(){
        
    }

    public Test(String name) {
        this.name = name;
    }

    public Test(int id_test) {
        this.id_test = id_test;
    }

    public Test(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Test(int id_test, String name, String type) {
        this.id_test = id_test;
        this.name = name;
        this.type = type;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_test;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.type);
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
        final Test other = (Test) obj;
        if (this.id_test != other.id_test) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.type, other.type);
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id_test + ", nom=" + name + ", type=" + type +'}';
    }
    
}