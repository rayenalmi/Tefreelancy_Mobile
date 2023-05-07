/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.tefreelancy.services.ServiceQuestion;
import com.tefreelancy.services.ServiceTest;
import com.terfreelancy.entities.Question;
import com.terfreelancy.entities.Test;
import java.util.ArrayList;

/**
 *
 * @author yassi
 */
public class TheTestsForm extends Form {
    private ArrayList<Test> tests;
    private Form current;

    public TheTestsForm(Form previous) {
        setTitle("Liste des tests"); 
        setLayout(BoxLayout.y());
        

        tests = ServiceTest.getInstance().getAllTests();
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Test test : tests) {
            Container testContainer = new Container(BoxLayout.y());
            testContainer.setUIID("TheTestsForm_Container");

            Label nameLabel = new Label("Nom : " + test.getName());
            Label typeLabel = new Label("Type : " + test.getType());

           Button PassButton = new Button("Passer");
            PassButton.addActionListener((evt) -> {
                System.out.println(test.getId_test());
            new InTheTestForm(this,test.getId_test()).show();
                
            });
            


            Container buttonsContainer = new Container(new GridLayout(1, 2));
            buttonsContainer.setUIID("TheTestsForm_ButtonsContainer");
            buttonsContainer.add(PassButton);

            testContainer.add(nameLabel);
            testContainer.add(typeLabel);
            testContainer.add(buttonsContainer);

            list.add(testContainer);
        }
        this.add(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }    
}
