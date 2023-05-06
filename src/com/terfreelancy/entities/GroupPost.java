/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import java.util.Objects;

/**
 *
 * @author ameni
 */
public class GroupPost {

    private int id_post;
    private String contenu;
    private int grp;

    public GroupPost(int id) {
        this.id_post = id;
    }

    public GroupPost(int id_post, String contenu, int grp) {
        this.id_post = id_post;
        this.contenu = contenu;
        this.grp = grp;
    }

    public GroupPost() {
    }

    public GroupPost(int id_post, String contenu) {
        this.id_post = id_post;
        this.contenu = contenu;
    }

    public int getId() {

        return id_post;
    }

    public void setId(int id) {
        this.id_post = id;
    }

    public String getContenu() {
        return contenu;
    }

    public int getGrp() {
        return grp;
    }

    public void setGrp(int grp) {
        this.grp = grp;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "GroupPost{" + ", contenu=" + contenu + ", date=" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id_post;
        hash = 37 * hash + Objects.hashCode(this.contenu);

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
        final GroupPost other = (GroupPost) obj;
        if (this.id_post != other.id_post) {
            return false;
        }
        if (this.grp != other.grp) {
            return false;
        }
        return Objects.equals(this.contenu, other.contenu);
    }

   
}
