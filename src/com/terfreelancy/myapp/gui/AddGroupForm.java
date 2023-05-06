/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
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
public class AddGroupForm extends Form {

    public AddGroupForm(Form previous,Resources theme) {
        setTitle("Add a New Group");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        TextField grpName = new TextField();
        grpName.setHint("Group Name");

        TextField descriptionGrp = new TextField();
        descriptionGrp.setHint("Description");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        Button btnValiderGrp = new Button("Add Group");
        btnValiderGrp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (grpName.getText().isEmpty() || descriptionGrp.getText().isEmpty()) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    Groupe g = new Groupe(grpName.getText(), descriptionGrp.getText(), 1);
                    if (ServiceGroup.getInstance().addGroup(g, g.getId())) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                        previous.showBack();
                        new ListGroupForm(previous,theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            }
        });

        addAll(grpName, descriptionGrp, btnValiderGrp);
    }
}
