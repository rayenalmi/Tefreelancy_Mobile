/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.tefreelancy.services.ServiceQuestion;
import com.terfreelancy.entities.Question;
import java.util.ArrayList;


/**
 *
 * @author yassi
 */
public class InTheTestForm extends Form {
    private int id_test;
    private ArrayList<Question> questions;
    private Form current;

    public InTheTestForm(Form previous,int id_test) {
        System.out.println("le test avec id "+id_test);
        
        setTitle("Liste des Questions"); 
        setLayout(BoxLayout.y());

        questions = ServiceQuestion.getInstance().getAllQuestionsById(id_test);

        for (Question question : questions) {
            Container questionContainer = new Container(BoxLayout.y());
            questionContainer.setUIID("ListQuestionForm_Container");

            SpanLabel questLabel = new SpanLabel("Question: " + question.getQuest());
            Label choice1Label = new Label("Choix 1: " + question.getChoice1());
            Label choice2Label = new Label("Choix 2: " + question.getChoice2());
            Label choice3Label = new Label("Choix 3: " + question.getChoice3());
            Label responseLabel = new Label("Réponse: " + question.getResponse());

            questionContainer.add(questLabel);
            questionContainer.add(choice1Label);
            questionContainer.add(choice2Label);
            questionContainer.add(choice3Label);
            questionContainer.add(responseLabel);
            add(questionContainer);

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
