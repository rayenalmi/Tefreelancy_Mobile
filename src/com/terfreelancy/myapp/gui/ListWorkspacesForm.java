/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.terfreelancy.entities.Task;
import com.terfreelancy.entities.Workspace;
//import com.terfreelancy.gui.ServicePostWs;
import com.tefreelancy.services.ServiceWorkspace;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ListWorkspacesForm extends Form  {
     public ArrayList<Workspace> categs;
    Form current;
    public ListWorkspacesForm(Form previous,int userId,String userRole) {
        setTitle("Liste des workspaces"); 
        categs = ServiceWorkspace.getInstance().getAllWorkspaces(userId);
        for (Workspace obj : categs) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
          //  SpanLabel sp = new SpanLabel();
            Button goBtn = new Button("Go");
            
            goBtn.addActionListener(e-> new ListHomeWorkspace(this,obj.getId(),userRole).show());
                   SpanLabel a = new SpanLabel("Name : " + obj.getName());
                    SpanLabel sp3 = new SpanLabel("Description : " + obj.getDescription());
              //      SpanLabel sp4 = new SpanLabel("---------------------------------------------------------------------" );

            addAll(a,sp3,goBtn);
        }
      
    if("Recruter".equals(userRole)){ 
  Button btnAddWorkspace = new Button("Add Workspace");
   btnAddWorkspace.addActionListener(e-> new AddWorkspaceForm(this,userId).show());
    
  add(btnAddWorkspace);
    }
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
