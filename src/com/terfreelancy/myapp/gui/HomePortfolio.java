/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.terfreelancy.entities.Portfolio;

/**
 *
 * @author HP
 */
public class HomePortfolio extends Form {
    
    Form current; 
    
    
    
    
    public HomePortfolio(Form previous){
        current = this; 
        setTitle("Home Portfolio"); 
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddPortfolio = new Button("Add Portfolio"); 
        Button btnListPortfolio = new Button("List Portfolio");
        
        
        btnAddPortfolio.addActionListener(e-> new AddPortfolioForm(current).show()); 
        btnListPortfolio.addActionListener(e-> new ListPortfolioForm(current).show()); 
        
        
        /*Button btnUpdatePortfolio = new Button("Update Portfolio");
        Portfolio p = new Portfolio(190,"1","1");
        btnUpdatePortfolio.addActionListener(e-> new UpdatePortfolioForm(current,p).show()); 
        */
        
        
        
        
        
        
        
        
        
        
        Button btnAddSkill = new Button("Add skill");
        btnAddSkill.addActionListener(e-> new AddSkillForm(current).show()); 
        Button btnListSkills = new Button("List Skills");
        btnListSkills.addActionListener(e-> new ListSkillForm(current).show());
        
        Button btnAddExp = new Button("Add Experience");
        btnAddExp.addActionListener(e-> new AddExperienceForm(current).show()); 
        Button btnListExp = new Button("List Experience");
        btnListExp.addActionListener(e-> new ListExperienceForm(current).show());
        
        
        
        Button btnListACH = new Button("List your  achievements");
        btnListACH.addActionListener(e-> new ListAchievementForm(current).show()); 
        
        
        //Button btnListSkills = new Button("List Skills");
        //btnListSkills.addActionListener(e-> new ListSkillForm(current).show());
        

        
        addAll(btnAddPortfolio,btnListPortfolio, btnAddSkill,btnListSkills,btnAddExp,btnListExp,btnListACH); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    
    
}
