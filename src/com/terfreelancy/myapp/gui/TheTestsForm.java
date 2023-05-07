/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.tefreelancy.services.ServiceQuestion;
import com.tefreelancy.services.ServiceTest;
import com.terfreelancy.entities.Question;
import com.terfreelancy.entities.Test;
import java.util.ArrayList;

/**
 *
 * @author yassi
 */
public class TheTestsForm extends Form {
    private ArrayList<Test> tests;
    private Form current;
    TextField searchField = new TextField("","chercher un test");
    Button searchButton = new Button("search");
    Container testContainer = new Container(BoxLayout.y());
   public TheTestsForm(Form previous) {
    setTitle("Liste des tests");
    setLayout(BoxLayout.y());

    searchButton.addActionListener(e -> {
        String query = searchField.getText();
        performSearch(query, previous, null);
    });
    add(searchField);
    add(searchButton);

    tests = ServiceTest.getInstance().getAllTests();

    Container list = new Container(BoxLayout.y());
    list.setScrollable(false); // Set this to false to prevent the test containers from being scrollable

    for (Test test : tests) {
        addTestContainer(test, previous, null);
    }

    list.add(testContainer); // Add the test container to the list container

    
    add(list); // Add the list container to the form

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
    private void addTestContainer(Test test, Form previous, Resources theme) {
        Container testContainer = new Container(BoxLayout.y());
        testContainer.setUIID("TheTestsForm_Container");

        Label nameLabel = new Label("Nom : " + test.getName());
        Label typeLabel = new Label("Type : " + test.getType());

        Button PassButton = new Button("Passer");
        PassButton.addActionListener((evt) -> {
            new InTheTestForm(this, test.getId_test()).show();
        });

        Container buttonsContainer = new Container(new GridLayout(1, 2));
        buttonsContainer.setUIID("TheTestsForm_ButtonsContainer");
        buttonsContainer.add(PassButton);

        testContainer.add(nameLabel);
        testContainer.add(typeLabel);
        testContainer.add(buttonsContainer);

        this.add(testContainer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void performSearch(String query, Form previous, Resources theme) {
        ArrayList<Test> searchResults = ServiceTest.getInstance().searchTests(query);
        removeAll();
        add(searchField);
        add(searchButton);

        if (searchResults.isEmpty()) {
            Label noResultsLabel = new Label("No results found");
            add(noResultsLabel);
        } else {
            for (Test t : searchResults) {
                addTestContainer(t, previous, theme);
            }
        }

        revalidate();
    }
    
    
}
