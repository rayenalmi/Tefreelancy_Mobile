/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.terfreelancy.entities.PublicationWs;
import com.terfreelancy.entities.Workspace;
import com.tefreelancy.services.ServiceWorkspace;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.DatePicker;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class EditWorkspaceForm extends Form {

    public EditWorkspaceForm(Form previous, Workspace WorkspaceToEdit) {
        setTitle("Edit a new Workspace");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField(WorkspaceToEdit.getName(), "Workspace Name");
        TextField tfDescription = new TextField(WorkspaceToEdit.getDescription(), "Workspace Description");
        
       
        Button btnValider = new Button("Edit Workspace");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                        Workspace editedWorkspace = new Workspace(WorkspaceToEdit.getId(), tfName.getText(), tfDescription.getText());
                        if (ServiceWorkspace.getInstance().updateWorkspace(editedWorkspace)) {
                            Dialog.show("Success", "Workspace Edited successfully", new Command("OK"));
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
