/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.terfreelancy.entities.Experience;
import com.tefreelancy.services.ServiceExperience;
import com.tefreelancy.services.ServicePortfolio;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListExperienceForm extends Form{
    
     public ArrayList<Experience> exps;
    Form current;
    public ListExperienceForm(Form previous) {
        setTitle("Liste des experiences"); 
        exps = ServiceExperience.getInstance().getExpById(190);
        for (Experience obj : exps) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
           
            SpanLabel a = new SpanLabel("title : " + obj.getTitle());
            SpanLabel sp3 = new SpanLabel("description : " + obj.getDescription());
            SpanLabel sp4 = new SpanLabel("location : " + obj.getLocation());
            SpanLabel sp5 = new SpanLabel("duration : " + obj.getDuration());
            SpanLabel sp6 = new SpanLabel("type : " + obj.getType());
            SpanLabel sp7 = new SpanLabel(" ------------------------------------- " );

            Button btnUpdateExp = new Button("Update experience");
        
            btnUpdateExp.addActionListener(e-> new UpdateExperienceForm(this,obj).show());       
            
            
            Button delBtn = new Button("Delete");
            delBtn.addActionListener(e -> {
                if (ServiceExperience.getInstance().deleteExp(obj.getId_experience())) {
                    Dialog.show("Success", "Experience Deleted successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });
                    

            
            addAll(spTitle,a,sp3,sp4,sp5, sp6,sp7,btnUpdateExp,delBtn );
        }
        

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
