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
import com.terfreelancy.entities.Skill;
import com.tefreelancy.services.ServiceExperience;
import com.tefreelancy.services.ServiceSkill;

/**
 *
 * @author HP
 */
public class AddExperienceForm extends Form {
    
    public AddExperienceForm(Form previous) {
        setTitle("Add a new Experience"); 
        setLayout(BoxLayout.y());
        
        Label nameLabel = new Label("Experience title :");
        /*nameLabel.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));;
        ameLabel.setForeground(0x000000);
        nameLabel.setBgColor(0xffffff);
        nameLabel.setBgColor(0xffffff); */
        TextField tfTitle = new TextField("","Experience title");
        Label descLabel = new Label("Exp description :");
        TextField tfDesc = new TextField("","Description"); 
        Label locationLabel = new Label("Exp location :");
        TextField tfLocation = new TextField("","Experience location");
        Label durationLabel = new Label("Exp duration :");
        TextField tfDuration = new TextField("","Duration"); 
        Label typeLabel = new Label("Exp type :");
        
        
        String[] items = {"education", "training", "qualification"};
     
   Button btnValider = new Button("Add Exp"); 
        ComboBox<String> comboBox = new ComboBox<>(items);

        

   
        comboBox.addActionListener(e -> {
                        String selectedItem = (String) comboBox.getSelectedItem();
                        System.out.println("Selected item: " + selectedItem);
                    });
        
        
        
        btnValider.addActionListener(new ActionListener () {
        
            @Override
            public void actionPerformed(ActionEvent evt){
            
                if( tfTitle.getText().length()==0  ){
                    
                    
                    

                
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try{
                        
                        Experience e = new Experience(190, tfTitle.getText(), tfDesc.getText(), tfLocation.getText(), tfDuration.getText(),  comboBox.getSelectedItem()); 
                        //Skill s = new Skill(190,tfTitle.getText(), comboBox.getSelectedItem()); 
                        
                        
                        
                        //if(new ServicePortfolio().addPortfolio(p)){
                        if(new ServiceExperience().addExp(e)){  
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
        
        
        

        addAll(nameLabel,tfTitle,descLabel,tfDesc ,locationLabel ,tfLocation,durationLabel, tfDuration, typeLabel,comboBox ,btnValider);
        //addAll(nameLabel,tfName,levelLabel,comboBox,btnValider); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }
}
