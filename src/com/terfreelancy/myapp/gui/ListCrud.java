/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author yassi
 */
public class ListCrud extends Form{
    public ListCrud(Form current){
        setTitle("ListCrud");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTest = new Button("Add Test");
        Button btnAddQuestion = new Button("Add Question");
        Button btnListTests = new Button("List Tests");
        Button btnListQuestions = new Button("List Questions");
        Button btnTheTests = new Button("Passer un Test");

        btnAddTest.addActionListener(e-> new AddTestForm(this).show());
        btnAddQuestion.addActionListener(e-> new AddQuestionForm(this).show());
        btnListTests.addActionListener(e-> new ListTestForm(this).show());
        btnListQuestions.addActionListener(e-> new ListQuestionForm(this).show());
        addAll(btnAddTest,btnListTests,btnAddQuestion,btnListQuestions,btnTheTests);
        
        
    
    }
    
}
