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
import com.terfreelancy.entities.Portfolio;
import com.tefreelancy.services.ServicePortfolio;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListPortfolioForm extends Form{

    Form currentlist; 
    //public ListPortfolioForm() {
    //}
    /*public ListPortfolioForm(Form previous) {
        setTitle(" list portfolio"); 
        SpanLabel sp = new SpanLabel(); 
        sp.setText(ServicePortfolio.getInstance().getAllPortfolios().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }*/
    
    
    
    
     public ArrayList<Portfolio> portfolios;
    Form current;
    public ListPortfolioForm(Form previous) {
        setTitle("Liste des portfolios"); 
        portfolios = ServicePortfolio.getInstance().getPortfolioById(190);
        for (Portfolio obj : portfolios) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
           
            SpanLabel a = new SpanLabel("intro : " + obj.getIntro());
                    SpanLabel sp3 = new SpanLabel("about : " + obj.getAbout());
                    SpanLabel sp4 = new SpanLabel("------------------------------------- " );
                    
            
            /*Button updateBtn = new Button("Update");
            updateBtn.addActionListener(e -> {
                if (ServicePortfolio.getInstance().updatePortfolio(obj) {
                    Dialog.show("Success", "Workspace Deleted successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });
            
            */
            /*Button editBtn = new Button("Update");

            editBtn.addActionListener(e -> {
                //new UpdatePortfolioForm(this, ServicePortfolio.getInstance().getWorkspaceById(workspaceId)).show();
                new UpdatePortfolioForm(this, ServicePortfolio.getInstance().getPortfolioById(obj.getId_freelancer())).show();

            });*/
          
            Button btnUpdatePortfolio = new Button("Update Portfolio");
        
        btnUpdatePortfolio.addActionListener(e-> new UpdatePortfolioForm(this,obj).show());       
            
            
            Button delBtn = new Button("Delete");
            delBtn.addActionListener(e -> {
                if (ServicePortfolio.getInstance().deletePortfolio(obj.getId_portfolio())) {
                    Dialog.show("Success", "Portfolio Deleted successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });
                    

            addAll(spTitle,a,sp3,sp4,delBtn,btnUpdatePortfolio);
        }
        

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}

