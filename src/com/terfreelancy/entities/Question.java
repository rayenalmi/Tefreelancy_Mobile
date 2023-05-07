/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.entities;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Question {
    private int id_question;
    private String quest;
    private String choice1;
    private String choice2;
    private String choice3;
    private String response;
    private int id_test;

    public Question(String quest) {
        this.quest = quest;
    }

    public Question(int id_question) {
        this.id_question = id_question;
    }

    public Question(String quest, String choice1, String choice2, String choice3, String response, int id_test) {
        this.quest = quest;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.response = response;
        this.id_test = id_test;
    }

    public Question(int id_question, String quest, String choice1, String choice2, String choice3, String response, int id_test) {
        this.id_question = id_question;
        this.quest = quest;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.response = response;
        this.id_test = id_test;
    }

    public Question(int id, String text, String text0, String text1, String text2, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Question() {
       
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id_question;
        hash = 13 * hash + Objects.hashCode(this.quest);
        hash = 13 * hash + Objects.hashCode(this.choice1);
        hash = 13 * hash + Objects.hashCode(this.choice2);
        hash = 13 * hash + Objects.hashCode(this.choice3);
        hash = 13 * hash + Objects.hashCode(this.response);
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
        final Question other = (Question) obj;
        if (this.id_question != other.id_question) {
            return false;
        }
        if (!Objects.equals(this.quest, other.quest)) {
            return false;
        }
        if (!Objects.equals(this.choice1, other.choice1)) {
            return false;
        }
        if (!Objects.equals(this.choice2, other.choice2)) {
            return false;
        }
        if (!Objects.equals(this.choice3, other.choice3)) {
            return false;
        }
        return Objects.equals(this.response, other.response);
    }

    @Override
    public String toString() {
        return "Question{" + "id_question=" + id_question + ", quest=" + quest + ", choice1=" + choice1 + ", choice2=" + choice2 + ", choice3=" + choice3 + ", response=" + response + '}';
    }
    
}
