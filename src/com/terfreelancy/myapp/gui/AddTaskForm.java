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
import com.terfreelancy.entities.Task;
import com.tefreelancy.services.ServiceTask;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.DatePicker;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class AddTaskForm extends Form{

    public AddTaskForm(Form previous) {
    setTitle("Add a new task");
    setLayout(BoxLayout.y());

    TextField tfName = new TextField("", "Task Name");
    TextField tfDesc = new TextField("", "Task Description");
    PickerComponent tfDeadline = PickerComponent.createDate(new Date());
Date deadline = tfDeadline.getPicker().getDate();

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


   

    Button btnValider = new Button("Add task");

   btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((tfName.getText().length() == 0)) {
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        } else {
            try {
                // Get the selected date from the PickerComponent
                Date deadlineDate = tfDeadline.getPicker().getDate();
                // Convert date to string with the desired format
                String deadlineStr = sdf.format(deadlineDate);
                // Parse the string back to a date object
                Date deadline = sdf.parse(deadlineStr);
                Task t = new Task(tfName.getText(), tfDesc.getText(), deadline);
                if (ServiceTask.getInstance().addTask(t, 95)) {
                    Dialog.show("Success", "Task added successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
            } catch (ParseException e) {
                Dialog.show("ERROR", "Invalid date format", new Command("OK"));
            }
        }
    }
});


    addAll(tfName, tfDesc, tfDeadline, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

}

    
    
}
