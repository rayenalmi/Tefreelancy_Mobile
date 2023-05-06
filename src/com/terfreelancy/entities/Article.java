/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

/**
 *
 * @author ameni
 */
public class Article {
    
    private String title;
    private String description;

    public Article(String title, String description) {
        System.out.println("description"+description);
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        System.out.println("dessss"+description);
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" + "title=" + title + ", description=" + description + '}';
    }
    
    
    
}
