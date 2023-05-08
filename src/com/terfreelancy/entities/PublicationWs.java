/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import java.util.Date;
/**
 *
 * @author USER
 */
public class PublicationWs {
    
     private int id;
    private String title;
    private String content;
    private String author;
    private Date creationDate;

    public PublicationWs(int id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PublicationWs(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PublicationWs(int id, String title, String content, String author, Date creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
    }

    public PublicationWs(String title, String content, String author, Date creationDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
    }



    public PublicationWs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "PublicationWs{" + "id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", creationDate=" + creationDate + '}';
    }
    
    
    
}
