/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.tefreelancy.services.ServiceTest;
import com.terfreelancy.entities.Test;
import java.util.ArrayList;

/**
 *
 * @author yassi
 */
public class ListTestForm extends Form{
    
    public ArrayList<Test> tests;
    Form current;
    public ListTestForm(Form previous) {
        setTitle("Liste des tests"); 
         setLayout(BoxLayout.y());
        ArrayList<Test> tests = ServiceTest.getInstance().getAllTests();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Test test : tests) {
            
              
             MultiButton sp = new MultiButton(test.getName());
              sp.setTextLine1("Nom : "+test.getName());
              sp.setTextLine2("Type : "+test.getType());
                     list.add(sp);
                     
                     sp.addActionListener((evt) -> {
                           if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if(ServiceTest.getInstance().deleteTest(test.getId_test())){
                                                    {
                                                       Dialog.show("Success","Le test "+test.getName()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                                 new UpdateTestForm(previous,test,this).show();
                                        }
                     });
        }
        this.add(list); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
}