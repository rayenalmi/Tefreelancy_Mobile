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
import com.terfreelancy.entities.Achievement;
import com.terfreelancy.entities.Experience;
import com.tefreelancy.services.ServiceAchievement;
import com.tefreelancy.services.ServiceExperience;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListAchievementForm extends Form{
    public ArrayList<Achievement> achs;
    Form current;
    public ListAchievementForm(Form previous) {
        setTitle("Liste des achievement"); 
        achs = ServiceAchievement.getInstance().getAchById(190);
        
        float final_rating = 0.0f;
        float sumrating = 0.0f; 
        int nbrfratings = 0; 
        
        for (Achievement obj : achs) {
            nbrfratings=nbrfratings+1; 
            sumrating =sumrating +obj.getRate(); 
            
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
           
            SpanLabel a = new SpanLabel("id Offer : " + obj.getId_offer());
            SpanLabel sp3 = new SpanLabel("rate : " + obj.getRate());
            
            SpanLabel sp7 = new SpanLabel(" ------------------------------------- " );

            //Button btnUpdateExp = new Button("Update experience");
        
            //btnUpdateExp.addActionListener(e-> new UpdateExperienceForm(this,obj).show());       
            
            
            /*Button delBtn = new Button("Delete");
            delBtn.addActionListener(e -> {
                if (ServiceExperience.getInstance().deleteExp(obj.getId_experience())) {
                    Dialog.show("Success", "Achievement Deleted successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });*/
                    

            final_rating= sumrating/nbrfratings;
            SpanLabel r = new SpanLabel("your latest rating is : " +final_rating );
            addAll(spTitle,a,sp3,sp7, r );
        }
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
