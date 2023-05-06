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
public class UpdateGroupPostForm extends Form {

    public UpdateGroupPostForm(Form previous, GroupPost grpPost,Groupe grp,Resources theme) {
        setTitle("update post");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        setLayout(BoxLayout.y());
        TextField tfDesc = new TextField(grpPost.getContenu(), "context");
        Button btnValider = new Button("Update post");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDesc.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        grpPost.setContenu(tfDesc.getText());
                        if (ServiceGroup.getInstance().updatePosts(grpPost, grpPost.getId())) {
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                             previous.showBack();
                        new ListGroupPost(previous,grp,theme).show();
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

        addAll(tfDesc, btnValider);
        //    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }

}
