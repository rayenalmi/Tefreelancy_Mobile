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
import com.codename1.ui.util.Resources;
import com.tefreelancy.services.ServiceGroup;
import com.terfreelancy.entities.Groupe;

/**
 *
 * @author ameni
 */
public class UpdateGroupForm extends Form {

    public UpdateGroupForm(Form previous, Groupe grp,Resources theme) {
        setTitle("modifier groupe");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        TextField tfName = new TextField(grp.getNom(), "Name");
        TextField tfDesc = new TextField(grp.getDescription(), "description");
        Button btnValider = new Button("Update group");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfDesc.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        grp.setNom(tfName.getText());
                        grp.setDescription(tfDesc.getText());
                        if (ServiceGroup.getInstance().updateGroup(grp, grp.getId())) {
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                            previous.showBack();
                            new ListGroupForm(previous,theme).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("ERROR", "name and type must be string", new Command("Ok"));
                    }

                }

            }
        }
        );

        addAll(tfName, tfDesc, btnValider);
        //    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
}
