/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ROG
 */
public class ForamtionForm extends Form {

    public ForamtionForm(Resources theme) {
        setTitle("Formations");
        
    }

    public Container createFormationCard(String name, int hours, int lessons, String imagePath) {
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        // Create a container for the card
        Container card = new Container(new BorderLayout());

        // Create a container for the information
        Container info = new Container(new GridLayout(3, 1));

        // Add the name label to the info container
        Label nameLabel = new Label(name);
        nameLabel.setUIID("FormationCardNameLabel");
        info.add(nameLabel);

        // Add the hours label to the info container
        Label hoursLabel = new Label(hours + " hours");
        hoursLabel.setUIID("FormationCardHoursLabel");
        info.add(hoursLabel);

        // Add the lessons label to the info container
        Label lessonsLabel = new Label(lessons + " lessons");
        lessonsLabel.setUIID("FormationCardLessonsLabel");
        info.add(lessonsLabel);

        // Load the image from the path
        Image image = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(100, 100), true), imagePath, imagePath, URLImage.RESIZE_SCALE);

        // Add the image to the center of the card
        card.addComponent(BorderLayout.CENTER, new ScaleImageLabel(image));

        // Add the info container to the left of the card
        card.addComponent(BorderLayout.WEST, info);

        // Set the UIID for the card
        card.setUIID("FormationCard");
        C2.add(card);
        return C2;
    }
}
