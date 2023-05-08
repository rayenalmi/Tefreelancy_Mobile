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
import com.tefreelancy.services.ServicePortfolio;

/**
 *
 * @author HP
 */
public class UpdatePortfolioForm extends Form{
    public UpdatePortfolioForm() {
    }
    
    
    
    public UpdatePortfolioForm(Form previous, Portfolio ptu) {
        setTitle("Update portfolio"); 
        setLayout(BoxLayout.y());
        
        TextField tfIntro = new TextField(ptu.getIntro(),"Portfolio introduction");
        TextField tfAbout = new TextField(ptu.getAbout(),"Portfolio about"); 

        Button btnValider = new Button("Update portfolio"); 
        
        btnValider.addActionListener(new ActionListener () {
        
            @Override
            public void actionPerformed(ActionEvent evt){
            
                if( (tfIntro.getText().length()==0 || tfAbout.getText().length()==0) ){
                
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try{
//                        Portfolio  updatedp = new Portfolio(ptu.getId_portfolio(),tfIntro.getText(),tfAbout.getText()); 
                        
                        Portfolio  updatedp = new Portfolio(ptu.getId_portfolio(), ptu.getId_freelancer(),tfIntro.getText(),tfAbout.getText()); 
                        
                        System.out.print("ptu.getId_portfolio() = ");
                         
                        System.out.println(ptu.getId_portfolio());
                        System.out.print("updated p id = ");
                         
                        System.out.println(updatedp.getId_portfolio());
                        //Portfolio p = new Portfolio(190,tfIntro.getText(),tfAbout.getText()); 
                        //if(new ServicePortfolio().addPortfolio(p)){
                        if(ServicePortfolio.getInstance().updatePortfolio(updatedp)){

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
