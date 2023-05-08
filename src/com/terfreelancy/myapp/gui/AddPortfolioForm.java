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
import com.tefreelancy.services.ServicePortfolio;
import com.terfreelancy.entities.Portfolio;
import com.tefreelancy.services.ServicePortfolio;

/**
 *
 * @author HP
 */
public class AddPortfolioForm extends Form{

    public AddPortfolioForm() {
    }
    
    
    
    public AddPortfolioForm(Form previous) {
        setTitle("Add a new portfolio"); 
        setLayout(BoxLayout.y());
        
        TextField tfIntro = new TextField("","Portfolio introduction");
        TextField tfAbout = new TextField("","Portfolio about"); 

        Button btnValider = new Button("Add portfolio"); 
        
        btnValider.addActionListener(new ActionListener () {
        
            @Override
            public void actionPerformed(ActionEvent evt){
            
                if( (tfIntro.getText().length()==0 || tfAbout.getText().length()==0) ){
                
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try{
                        Portfolio p = new Portfolio(190,tfIntro.getText(),tfAbout.getText()); 
                        //if(new ServicePortfolio().addPortfolio(p)){
                        if(ServicePortfolio.getInstance().addPortfolio(p)){

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
        
        
        
        
        addAll(tfIntro, tfAbout, btnValider); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }
}
