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
public class Offre {

    private int id_offre;
    private String nom;
    private String description;
    private String duree;
    private String motsCles;
    private float salaire;
    private int id_recruteur;
    private Boolean accepter;
    private float percent;

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
    
    

    public Offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public Offre(int id_offre, String nom, float salaire) {
        this.id_offre = id_offre;
        this.nom = nom;
        this.salaire = salaire;
    }

    public Offre(int id_offre, String nom, String description, String duree, String motsCles, float salaire, int id_recruteur) {
        this.id_offre = id_offre;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.motsCles = motsCles;
        this.salaire = salaire;
        this.id_recruteur = id_recruteur;
    }

    public Offre(String nom, String description, String duree, String motsCles, float salaire) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.motsCles = motsCles;
        this.salaire = salaire;
    }

    public Offre(String nom, String description, String duree, String motsCles, float salaire, int id_recruteur) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.motsCles = motsCles;
        this.salaire = salaire;
        this.id_recruteur = id_recruteur;
    }

    public Offre(String text, String text0, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Offre(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Boolean getAccepter() {
        return accepter;
    }

    public void setAccepter(Boolean accepter) {
        this.accepter = accepter;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(String motsCles) {
        this.motsCles = motsCles;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public int getId_recruteur() {
        return id_recruteur;
    }

    public void setId_recruteur(int id_recruteur) {
        this.id_recruteur = id_recruteur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nom);
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
        final Offre other = (Offre) obj;
        return Objects.equals(this.nom, other.nom);
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", nom=" + nom + ", description=" + description + ", duree=" + duree + ", motsCles=" + motsCles + ", salaire=" + salaire + ", id_recruteur=" + id_recruteur + '}';
    }



}