/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.terfreelancy.entities.Experience;
import com.terfreelancy.entities.Portfolio;
import com.tefreelancy.services.ServiceExperience;
import com.tefreelancy.services.ServicePortfolio;

/**
 *
 * @author HP
 */
public class UpdateExperienceForm extends Form{
    public UpdateExperienceForm() {
    }
    
    
    
    public UpdateExperienceForm(Form previous, Experience etu) {
        setTitle("Update experience"); 
        setLayout(BoxLayout.y());
        
        
        Label nameLabel = new Label("Experience title :");
        
        TextField tfTitle = new TextField(etu.getTitle(),"Experience title");
        Label descLabel = new Label("Exp description :");
        TextField tfDesc = new TextField(etu.getDescription(),"Description"); 
        Label locationLabel = new Label("Exp location :");
        TextField tfLocation = new TextField(etu.getLocation(),"Experience location");
        Label durationLabel = new Label("Exp duration :");
        TextField tfDuration = new TextField(etu.getDuration(),"Duration"); 
        Label typeLabel = new Label("Exp type :");
        
        
        String[] items = {"education", "training", "qualification"};
        ComboBox<String> comboBox = new ComboBox<>(items);
       comboBox.setSelectedItem(etu.getType());

        Button btnValider = new Button("Update portfolio"); 
        
        btnValider.addActionListener(new ActionListener () {
        
            @Override
            public void actionPerformed(ActionEvent evt){
            
                if( (tfTitle.getText().length()==0 || tfDesc.getText().length()==0) ){
                
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try{
//                        Portfolio  updatedp = new Portfolio(ptu.getId_portfolio(),tfIntro.getText(),tfAbout.getText()); 
                        
                        Experience  updatede = new Experience(etu.getId_experience(), etu.getId_freelancer(),tfTitle.getText(),tfDesc.getText(), tfLocation.getText(), tfDuration.getText(), (String) comboBox.getSelectedItem()); 
                        
                        
                        if(ServiceExperience.getInstance().updateExp(updatede)){

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
        
        
        
        //addAll(tfIntro, tfAbout, btnValider); 
                addAll(nameLabel,tfTitle,descLabel,tfDesc ,locationLabel ,tfLocation,durationLabel, tfDuration, typeLabel,comboBox ,btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }
}
