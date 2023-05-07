/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.tefreelancy.services.ServiceQuestion;
import com.terfreelancy.entities.Question;

/**
 *
 * @author yassi
 */
public class UpdateQuestionForm extends Form{
    public UpdateQuestionForm(Form previous, Question q,Form ListQuestionForm) {
        setTitle("modifier question");
        setLayout(BoxLayout.y());
        TextField tfQuest = new TextField(q.getQuest(), "Question");            
        TextField tfChoice1 = new TextField(q.getChoice1(), "Choice1");
        TextField tfChoice2 = new TextField(q.getChoice2(), "Choice2");            
        TextField tfChoice3 = new TextField(q.getChoice3(), "Choice3");
        TextField tfResponse = new TextField(q.getResponse(), "Response");            
        TextField tfIdTest = new TextField(String.valueOf(q.getId_test()), "idTest");
        Button btnValider = new Button("modifier question");
       btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfQuest.getText().length()==0) ||(tfChoice1.getText().length()==0 )||(tfChoice2.getText().length()==0 )||(tfChoice3.getText().length()==0 )||(tfResponse.getText().length()==0 )||(tfResponse.getText().length()==0 )||(tfIdTest.getText().length()==0 ))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        q.setId_question(q.getId_question());
                        q.setQuest(tfQuest.getText());
                        q.setChoice1(tfChoice1.getText());
                        q.setChoice2(tfChoice2.getText());
                        q.setChoice3(tfChoice3.getText());
                        q.setResponse(tfResponse.getText());
                        q.setId_test(Integer.parseInt(tfIdTest.getText()));
                        if ( new ServiceQuestion().getInstance().updateQuestion(q))
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfQuest,tfChoice1,tfChoice2,tfChoice3,tfResponse,tfIdTest,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
