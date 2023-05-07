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
import com.tefreelancy.services.ServicePostWs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.DatePicker;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class EditPostForm extends Form {

    public EditPostForm(Form previous, PublicationWs PostToEdit) {
        setTitle("Edit a new Post");
        setLayout(BoxLayout.y());
        System.out.println(PostToEdit);
        TextField tfName = new TextField(PostToEdit.getTitle(), "Post title");
        TextField tfContent = new TextField(PostToEdit.getContent(), "Post Content");
        
        Label tfAuth = new Label("Author : "+PostToEdit.getAuthor());
        
        
        
        Label tfCreationDate = new Label("Creation Date : "+PostToEdit.getCreationDate());
        
       // TextField tfCreationDate = new TextField(PostToEdit.getCreationDate()+"", "Post CreationDate");

        Button btnValider = new Button("Edit Post");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                        PublicationWs editedPost = new PublicationWs(PostToEdit.getId(), tfName.getText(), tfContent.getText(),PostToEdit.getAuthor(),PostToEdit.getCreationDate());
                        if (ServicePostWs.getInstance().updatePost(editedPost, 95)) {
                            Dialog.show("Success", "Post Edited successfully", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    
                }
            }
        });

        addAll(tfName, tfContent,tfAuth,tfCreationDate, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
