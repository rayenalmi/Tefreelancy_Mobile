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
import com.terfreelancy.entities.Portfolio;
import com.terfreelancy.entities.Skill;
import com.tefreelancy.services.ServicePortfolio;
import com.tefreelancy.services.ServiceSkill;

/**
 *
 * @author HP
 */
public class UpdateSkillForm extends Form {
    public UpdateSkillForm() {
    }
    
    
    
    public UpdateSkillForm(Form previous, Skill stu) {
        setTitle("Update portfolio"); 
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField(stu.getName(),"Skill name");
        TextField tfLevel = new TextField(stu.getLevel(),"Skill level"); 

        Button btnValider = new Button("Update skill"); 
        
        btnValider.addActionListener(new ActionListener () {
        
            @Override
            public void actionPerformed(ActionEvent evt){
            
                if( (tfName.getText().length()==0 || tfLevel.getText().length()==0) ){
                
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try{
//                        Portfolio  updatedp = new Portfolio(ptu.getId_portfolio(),tfIntro.getText(),tfAbout.getText()); 
                        
                        Skill  updateds = new Skill(stu.getId_skill(), stu.getId_freelancer(),tfName.getText(),tfLevel.getText()); 
                        
                       
                       
                        if(ServiceSkill.getInstance().updateSkill(updateds)){

                        Dialog.show("Success", "Conncetion accepted", new Command("OK"));
                        }else {
                               Dialog.show("ERROR", "Server error", new Command("OK")); 
                        } 
                        
                    }catch (NumberFormatException e){
                    
                        Dialog.show("ERROR", "Status must be a number", new Command("OK")); 
                    }
                    
                    
                }
                
                
            }
            
        } ); 
        
        
        
        addAll(tfName, tfLevel, btnValider); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }
}
