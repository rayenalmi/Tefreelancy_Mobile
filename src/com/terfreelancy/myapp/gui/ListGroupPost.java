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
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.tefreelancy.services.ServiceGroup;
import com.terfreelancy.entities.GroupPost;
import com.terfreelancy.entities.Groupe;
import java.util.ArrayList;

/**
 *
 * @author ameni
 */
public class ListGroupPost extends Form {

    private Form current;

    public ListGroupPost(Form previous, Groupe grp, Resources theme) {
        current = this;
        Button btnGetNewsPost = new Button("Get News");
        btnGetNewsPost.addActionListener(e -> new GetNewsForm(current, grp.getNom(), theme).show());
        add(btnGetNewsPost);

        setTitle("List of Group Posts");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Button btnAddGroupPost = new Button("Add Group Post");
        btnAddGroupPost.addActionListener(e -> new AddGroupPostForm(current, grp, theme).show());

        add(btnAddGroupPost);

        ArrayList<GroupPost> posts = ServiceGroup.getInstance().getAllGroupsPosts(grp.getId());
        for (GroupPost post : posts) {
            addElement(post, grp, theme);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void addElement(GroupPost groupPost, Groupe grp, Resources theme) {
        Label lblPostContent = new Label(groupPost.getContenu());

        Button btnDeletePost = new Button("Delete Post");
        btnDeletePost.addActionListener(e -> {
            ServiceGroup.getInstance().deletePosts(groupPost.getId());
            Dialog.show("Done", "Post deleted successfully", new Command("ok"));
                     new ListGroupPost(current,grp,theme).show();

            current.showBack();
        });

        Button btnEditPost = new Button("Update Post");
        btnEditPost.addActionListener(e -> new UpdateGroupPostForm(current, groupPost, grp, theme).show());

        addAll(lblPostContent, btnDeletePost, btnEditPost);
    }
}