/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class AddSkillForm extends Form{
    
    public AddSkillForm(Form previous) {
        setTitle("Add a new skill"); 
        setLayout(BoxLayout.y());
        
        Label nameLabel = new Label("Skill name :");
        
        /*nameLabel.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));;
nameLabel.setForeground(0x000000);
nameLabel.setBgColor(0xffffff);
nameLabel.setBgColor(0xffffff); */

        

        
        TextField tfName = new TextField("","Skill name");
        //TextField tfLevel = new TextField("","Skill level"); 
        
        Label levelLabel = new Label("Skill level :");
        
        String[] items = {"Fundamental_Awareness", "Novice" , "Intermediate" ,"Advanced" ,"Expert"};
        //String[] items = {"Option 1", "Option 2", "Option 3"};

        ComboBox<String> comboBox = new ComboBox<>(items);


        Button btnValider = new Button("Add Skill"); 
        
        
        comboBox.addActionListener(e -> {
                        String selectedItem = (String) comboBox.getSelectedItem();
                        System.out.println("Selected item: " + selectedItem);
                    });
        
        
        btnValider.addActionListener(new ActionListener () {
        
            @Override
            public void actionPerformed(ActionEvent evt){
            
                if( tfName.getText().length()==0  ){
                    
                    
                    

                
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try{
                        
                        
                        Skill s = new Skill(190,tfName.getText(), comboBox.getSelectedItem()); 
                        
                        
                        
                        //if(new ServicePortfolio().addPortfolio(p)){
                        if(new ServiceSkill().addSkill(s)){  
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
        
        
        

        addAll(nameLabel,tfName,levelLabel,comboBox,btnValider); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }
    
    
}
