/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.terfreelancy.entities.Workspace;
import com.tefreelancy.services.ServiceWorkspace;
/**
 *
 * @author bhk
 */
public class AddWorkspaceForm extends Form{

    public AddWorkspaceForm(Form previous,int userId) {
    setTitle("Add a new Workspace");
    setLayout(BoxLayout.y());

    TextField tfName = new TextField("", "Workspace Name");
    TextField tfDescription = new TextField("", "Workspace Descritpion");

   

    Button btnValider = new Button("Add Workspace");

   btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((tfName.getText().length() == 0)) {
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        } else {
           
                Workspace t = new Workspace(tfName.getText(), tfDescription.getText());
                if (ServiceWorkspace.getInstance().addWorkspace(t,userId)) {
                    Dialog.show("Success", "Workspace added successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            
        }
    }
});


    addAll(tfName, tfDescription, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

}

    
    
}
