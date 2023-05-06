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
import com.terfreelancy.entities.GroupPost;
import com.terfreelancy.entities.Groupe;

/**
 *
 * @author ameni
 */
public class AddGroupPostForm extends Form {

    public AddGroupPostForm(Form previous, Groupe grp,Resources theme) {
        setTitle("Add a new Post");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        TextField DescriptionGrp = new TextField("", "Context");
        Button btnValiderGrp = new Button("Add Post");
        btnValiderGrp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((DescriptionGrp.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    GroupPost g = new GroupPost(1, DescriptionGrp.getText().toString(), 1);
                    if (ServiceGroup.getInstance().addPost(g, g.getId())) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                        previous.showBack();
                        new ListGroupPost(previous,grp,theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }

            }

        });

        addAll(DescriptionGrp, btnValiderGrp);

    }

}
