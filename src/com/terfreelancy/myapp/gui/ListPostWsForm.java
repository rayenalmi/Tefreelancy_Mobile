package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.tefreelancy.services.ServicePostWs;
import com.terfreelancy.entities.PublicationWs;
//import com.terfreelancy.myapp.gui.ServicePostWs;
import java.util.ArrayList;

public class ListPostWsForm extends Form {

    private ArrayList<PublicationWs> posts;
    private TextField searchField;
    
    public ListPostWsForm(Form previous, int workspaceId,String userRole) {
        setTitle("Liste des Posts");
        setLayout(BoxLayout.y());

        searchField = new TextField("", "Search");
        add(searchField);
        searchField.addDataChangeListener((i1, i2) -> {
            refreshForm(workspaceId,userRole);
        });

        posts = ServicePostWs.getInstance().getAllPublicationWss(workspaceId);
        refreshForm(workspaceId, userRole);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void refreshForm(int workspaceId,String userRole) {
        removeAll();
        add(searchField);

        for (PublicationWs obj : posts) {
            if (obj.getTitle().toLowerCase().contains(searchField.getText().toLowerCase())) {
                Button spTitle = new Button();
                SpanLabel sp = new SpanLabel();
                
               
                SpanLabel a = new SpanLabel("Name : " + obj.getTitle());
                SpanLabel sp3 = new SpanLabel("Description : " + obj.getContent());
             //   SpanLabel sp4 = new SpanLabel("--------------------------------------------------------");

                addAll( a, sp3);
                 //String userRole="Freelancer";
                if("Recruter".equals(userRole)){
                Button editBtn = new Button("Edit");
                editBtn.addActionListener(e -> {
                    new EditPostForm(this, obj).show();
                });
                Button delBtn = new Button("Delete");
                delBtn.addActionListener(e -> {
                    if (ServicePostWs.getInstance().deletePost(obj.getId(), workspaceId)) {
                        Dialog.show("Success", "Task Deleted successfully", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                });
                addAll(editBtn,delBtn);
                 SpanLabel sp4 = new SpanLabel("--------------------------------------------------------");
                    add(sp4);
                }
            }
        }

        revalidate();
    }

}
