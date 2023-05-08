/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tefreelancy.services;

import com.terfreelancy.entities.Offre;
import java.util.ArrayList;

/**
 *
 * @author yassi
 */
public class OffreService {
    
    public ArrayList<Offre> offres = new ArrayList<>();
    
    public ArrayList<Offre> searchTests(String query) {
    ArrayList<Offre> searchResults = new ArrayList<>();
    String lowercaseQuery = query.toLowerCase();
    for (Offre offre : offres) {
        String lowercaseName = offre.getNom().toLowerCase();
        String lowercaseType = offre.getMotsCles().toLowerCase();
        if (lowercaseName.contains(lowercaseQuery) || lowercaseType.contains(lowercaseQuery)) {
            searchResults.add(offre);
        }
    }
    return searchResults;
}
    
}
