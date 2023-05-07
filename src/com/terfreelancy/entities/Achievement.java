/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

/**
 *
 * @author HP
 */
public class Achievement {
    private int id ; 
    private int id_freelancer;
    private int id_offer;
    private float rate;

    public Achievement() {
    }

    
    
    public Achievement(int id_freelancer, int id_offer, float rate) {
        this.id_freelancer = id_freelancer;
        this.id_offer = id_offer;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    public int getId_offer() {
        return id_offer;
    }

    public void setId_offer(int id_offer) {
        this.id_offer = id_offer;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Achievement{" + "id=" + id + ", id_freelancer=" + id_freelancer + ", id_offer=" + id_offer + ", rate=" + rate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + this.id_freelancer;
        hash = 53 * hash + this.id_offer;
        hash = 53 * hash + Float.floatToIntBits(this.rate);
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
        final Achievement other = (Achievement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_freelancer != other.id_freelancer) {
            return false;
        }
        if (this.id_offer != other.id_offer) {
            return false;
        }
        return Float.floatToIntBits(this.rate) == Float.floatToIntBits(other.rate);
    }
    
    
    
   
}
