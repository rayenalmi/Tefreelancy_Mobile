/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Experience {
    private int id_experience; 
    private int id_freelancer; 
    //private int id_portfolio; 

    private String title;  
    private String description;     
    private String location;     
    private String duration;
    
    public enum Type {education, training, qualification}; 
    //private Type type; 
    
    private String type; 

    public Experience() {
    }

    public Experience(int id_freelancer, String title, String description, String location, String duration, String type) {
        this.id_freelancer = id_freelancer;
        this.title = title;
        this.description = description;
        this.location = location;
        this.duration = duration;
        this.type = type;
    }

    public Experience(int id_experience, int id_freelancer, String title, String description, String location, String duration, String type) {
        this.id_experience = id_experience;
        this.id_freelancer = id_freelancer;
        this.title = title;
        this.description = description;
        this.location = location;
        this.duration = duration;
        this.type = type;
    }

    public int getId_experience() {
        return id_experience;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public void setId_experience(int id_experience) {
        this.id_experience = id_experience;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_experience;
        hash = 79 * hash + this.id_freelancer;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.location);
        hash = 79 * hash + Objects.hashCode(this.duration);
        hash = 79 * hash + Objects.hashCode(this.type);
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
        final Experience other = (Experience) obj;
        if (this.id_experience != other.id_experience) {
            return false;
        }
        if (this.id_freelancer != other.id_freelancer) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return Objects.equals(this.type, other.type);
    }

    @Override
    public String toString() {
        return "Experience{" + "id_experience=" + id_experience + ", id_freelancer=" + id_freelancer + ", title=" + title + ", description=" + description + ", location=" + location + ", duration=" + duration + ", type=" + type + '}';
    }
    
    
    
}
