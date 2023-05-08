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
public class AddPostForm extends Form{

    public AddPostForm(Form previous) {
    setTitle("Add a new Post");
    setLayout(BoxLayout.y());

    TextField tfTitle = new TextField("", "Post Title");
    TextField tfContent = new TextField("", "Post Content");
    TextField tfAuthor = new TextField("", "Post Author");

   

    Button btnValider = new Button("Add Post");

   btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((tfTitle.getText().length() == 0)) {
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        } else {
           
                PublicationWs t = new PublicationWs(tfTitle.getText(), tfContent.getText(), tfAuthor.getText());
                if (ServicePostWs.getInstance().addPublicationWs(t,95)) {
                    Dialog.show("Success", "Post added successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            
        }
    }
});


    addAll(tfTitle, tfContent, tfAuthor, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

}

    
    
}
