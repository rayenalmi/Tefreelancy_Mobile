/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.tefreelancy.services.ServiceGroup;
import com.terfreelancy.entities.Groupe;
import java.util.ArrayList;

/**
 *
 * @author ameni
 */
public class ListGroupForm extends Form {

    private Form current;
    private TextField searchField;
    private Button searchButton;

    public ListGroupForm(Form previous,Resources theme) {
        current = this;

//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        setTitle("List of Groups");
        searchField = new TextField("", "Search Query");
        searchButton = new Button("Search");

        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            performSearch(query, previous,theme);
        });

        add(searchField);
        add(searchButton);

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Button btnAddGroupPost = new Button("Add Group");
        btnAddGroupPost.addActionListener(e -> new AddGroupForm(current,theme).show());
        add(btnAddGroupPost);

        ArrayList<Groupe> groups = ServiceGroup.getInstance().getAllGroups();
        for (Groupe group : groups) {
            addElement(group, previous,theme);
        }

        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void performSearch(String query, Form previous,Resources theme) {
        ArrayList<Groupe> searchResults = ServiceGroup.getInstance().searchGroups(query);
        removeAll();
        add(searchField);
        add(searchButton);

        if (searchResults.isEmpty()) {
            Label noResultsLabel = new Label("No results found");
            add(noResultsLabel);
        } else {
            for (Groupe group : searchResults) {
                addElement(group, previous,theme);
            }
        }
        //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        revalidate();

    }

    public void addElement(Groupe groupe, Form previous,Resources theme) {
        Label lblGroupName = new Label(groupe.getNom());
        Label lblDescription = new Label(groupe.getDescription());

        Button btnDeleteGroup = new Button("Delete Group");

        btnDeleteGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceGroup.getInstance().deleteGroups(groupe.getId());
                Dialog.show("Group deleted successfully", "OK", null);
                previous.showBack();
                new ListGroupForm(previous,theme).show();
            }
        });
        Button btnEditGroup = new Button("Update Group");
        btnEditGroup.addActionListener(e -> new UpdateGroupForm(current, groupe,theme).show());

        Button btnShowGroupPosts = new Button("Show Group Posts");
        btnShowGroupPosts.addActionListener(e -> new ListGroupPost(current, groupe,theme).show());
        addAll(lblGroupName, lblDescription, btnDeleteGroup, btnEditGroup, btnShowGroupPosts);
    }
}
