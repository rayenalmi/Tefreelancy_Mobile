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
import com.terfreelancy.entities.Achievement;
import com.terfreelancy.entities.Experience;
import com.tefreelancy.services.ServiceAchievement;
import com.tefreelancy.services.ServiceExperience;

/**
 *
 * @author HP
 */
public class AddAchForm extends Form{
    public AddAchForm(Form previous) {
        
        setTitle("Add a new achievement"); 
        setLayout(BoxLayout.y());
        
        Label nameLabel = new Label("Offer id :");
       
        TextField tfOfferId = new TextField("","Offer id");
        Label rateLabel = new Label("Offer Rating :");
        TextField tfRate = new TextField("","Rate"); 
     
        //**********************
        
        
        setTitle("Add a new Experience"); 
        setLayout(BoxLayout.y());
        
       
        /*nameLabel.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));;
        ameLabel.setForeground(0x000000);
        nameLabel.setBgColor(0xffffff);
        nameLabel.setBgColor(0xffffff); */
        /*TextField tfTitle = new TextField("","Experience title");
        Label descLabel = new Label("Exp description :");
        TextField tfDesc = new TextField("","Description"); 
        Label locationLabel = new Label("Exp location :");
        TextField tfLocation = new TextField("","Experience location");
        Label durationLabel = new Label("Exp duration :");
        TextField tfDuration = new TextField("","Duration"); 
        Label typeLabel = new Label("Exp type :");
        
        
        String[] items = {"education", "training", "qualification"};
     

        ComboBox<String> comboBox = new ComboBox<>(items);*/

        


        Button btnValider = new Button("Add Exp"); 
        
        
    
        
        btnValider.addActionListener(new ActionListener () {
        
            @Override
            public void actionPerformed(ActionEvent evt){
            
                if( tfOfferId.getText().length()==0  ){
                    
                    
                    

                
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try{
                        
                        Achievement a = new Achievement(190 ,Integer.parseInt(tfOfferId.getText()), Integer.parseInt(tfRate.getText())); 
                        //Skill s = new Skill(190,tfTitle.getText(), comboBox.getSelectedItem()); 
                        
                        
                        
                        //if(new ServicePortfolio().addPortfolio(p)){
                        if(new ServiceAchievement().addAch(a)){  
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
        
        
        

        addAll(nameLabel,tfOfferId,rateLabel,tfRate ,btnValider);
        //addAll(nameLabel,tfName,levelLabel,comboBox,btnValider); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }
}
