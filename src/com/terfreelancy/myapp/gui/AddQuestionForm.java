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
public class AddQuestionForm extends Form{
    public AddQuestionForm(Form previous) {
        setTitle("Add a new test");
        setLayout(BoxLayout.y());
        
        TextField tfQuestion = new TextField("","Question");
        TextField tfChoice1 = new TextField("","Choice1");
        TextField tfChoice2 = new TextField("","Choice2");
        TextField tfChoice3 = new TextField("","Choice3");
        TextField tfResponse = new TextField("","Response");
        TextField tfTestId = new TextField("","testid");
        Button btnValider = new Button("Add test");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfQuestion.getText().length()==0) ||(tfChoice1.getText().length()==0 )||(tfChoice2.getText().length()==0 )||(tfChoice3.getText().length()==0 )||(tfResponse.getText().length()==0 )||(tfResponse.getText().length()==0 )||(tfTestId.getText().length()==0 ))
                     Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        Question t = new Question(tfQuestion.getText(),tfChoice1.getText(),tfChoice2.getText(),tfChoice3.getText(),tfResponse.getText(),Integer.parseInt(tfTestId.getText()));
                        if ( new ServiceQuestion().addQuestion(t))
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfQuestion,tfChoice1,tfChoice2,tfChoice3,tfResponse,tfTestId,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
