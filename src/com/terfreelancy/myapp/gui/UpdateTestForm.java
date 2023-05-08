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
import com.tefreelancy.services.ServiceTest;
import com.terfreelancy.entities.Test;

/**
 *
 * @author yassi
 */
public class UpdateTestForm  extends Form{

    public UpdateTestForm(Form previous, Test test,Form ListTestForm) {
        setTitle("modifier test");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField(test.getName(), "Name");            
        TextField tfType = new TextField(test.getType(), "type");
        Button btnValider = new Button("modifier test");
       btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0) ||(tfType.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        test.setId_test(test.getId_test());
                        test.setName(tfName.getText());
                        test.setType(tfType.getText());
                        if ( new ServiceTest().getInstance().updateTest(test))
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }catch(Exception e){
                        Dialog.show("ERROR", "name and type must be string", new Command("Ok"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfType,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}