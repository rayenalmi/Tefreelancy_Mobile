package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Freelancer;
import com.terfreelancy.entities.PublicationWs;
import com.terfreelancy.entities.Task;
import com.terfreelancy.entities.Workspace;
import com.tefreelancy.services.ServicePostWs;
import com.tefreelancy.services.ServiceTask;
import com.tefreelancy.services.ServiceWorkspace;
import com.terfreelancy.entities.Freelancer;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.ArrayList;

public class ListHomeWorkspace extends Form {

    private ArrayList<Task> tasks;
    private ArrayList<PublicationWs> posts;
    private ArrayList<Freelancer> freelancers;
    private String message = "You have been added to workspace";
    private String accountSid = "AC41ddc35f4041bedc6be015a1570ecb81";
    private String authToken = "6f71de340e656603bd16af7c01905ce0";

    public ListHomeWorkspace(Form previous, int workspaceId, String userRole) {
        setTitle("Workspace Home");
        Button btnListTasks = new Button("List Tasks");
        Button btnListPosts = new Button("List Posts");
        //String userRole="Recruter";
        if ("Recruter".equals(userRole)) {
            Button editBtn = new Button("Edit");

            editBtn.addActionListener(e -> {
                //int workspaceId2 =95; // or whatever value you want to use for testing
                //  System.out.println("Workspace ID: " + workspaceId); // debugging statement
                new EditWorkspaceForm(this, ServiceWorkspace.getInstance().getWorkspaceById(workspaceId)).show();
            });

            Button delBtn = new Button("Delete");
            delBtn.addActionListener(e -> {
                if (ServiceWorkspace.getInstance().deleteWorkspace(workspaceId)) {
                    Dialog.show("Success", "Workspace Deleted successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });

            addAll(editBtn, delBtn);
        }

        addAll(btnListTasks, btnListPosts);

        btnListPosts.addActionListener(e -> new ListPostWsForm(this, workspaceId, userRole).show());
        btnListTasks.addActionListener(e -> new ListTasksForm(this, workspaceId, userRole).show());

        tasks = ServiceTask.getInstance().getAllTasks(workspaceId);
      //  SpanLabel sp1 = new SpanLabel();
        Button sp1 = new Button("**** In progress Tasks ****");
        add(sp1);

        int count = 0;
        for (Task obj : tasks) {
            if (obj.isCompleted() == false) {
                setLayout(BoxLayout.y());

                

                SpanLabel a = new SpanLabel("Title : " + obj.getTitle());
                SpanLabel sp3 = new SpanLabel("Description : " + obj.getDescription());
                SpanLabel sp5 = new SpanLabel("Deadline : " + obj.getDeadline());
                SpanLabel sp4 = new SpanLabel("--------------------------------------------------------");

                addAll(a, sp3, sp5,sp4);

                count++;
                if (count >= 3) {
                    break;
                }
            }
        }

      //  SpanLabel sp2 = new SpanLabel();
        Button sp2 = new Button("**** Recent Posts ****");
        add(sp2);
        int count2 = 0;
        posts = ServicePostWs.getInstance().getAllPublicationWss(workspaceId);
        for (PublicationWs obj : posts) {
            setLayout(BoxLayout.y());

            
           // SpanLabel sp = new SpanLabel();

            SpanLabel a = new SpanLabel("Name : " + obj.getTitle());
            SpanLabel sp3 = new SpanLabel("Description : " + obj.getContent());
             SpanLabel sp4 = new SpanLabel("--------------------------------------------------------");

            addAll(a, sp3,sp4);
            count2++;
            if (count2 >= 3) {
                break;
            }
        }

        /*--------------------------------------------------------------------------------------------------*/
      //  SpanLabel sp3 = new SpanLabel();
        
        Button sp3 = new Button("**** Freelancers ****");
        add(sp3);
        freelancers = ServiceWorkspace.getInstance().getAllFreelancers(workspaceId);
        for (Freelancer obj : freelancers) {
            setLayout(BoxLayout.y());

            SpanLabel sp7 = new SpanLabel("Nom : " + obj.getNom());
            SpanLabel sp5 = new SpanLabel("Prenom : " + obj.getPrenom());
         SpanLabel sp6 = new SpanLabel("--------------------------------------------------------");

            addAll(sp7, sp5,sp6);

        }
        TextField tfEmail = new TextField("", "Freelancer Email");
        Button addFreelancer = new Button("Add Freelancer");
        addFreelancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfEmail.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    if (ServiceWorkspace.getInstance().addFreelancerByEmail(workspaceId, tfEmail.getText())) {

                        Freelancer free = ServiceWorkspace.getInstance().getFreelancerByEmail(tfEmail.getText());
                        String freelancerNumber = "+216" + free.getTel();
                        Twilio.init(accountSid, authToken);
                        Message.creator(new PhoneNumber(freelancerNumber), new PhoneNumber("+13203628745"), message).create();
                        Dialog.show("Success", "Freelancer added to workspace", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfEmail, addFreelancer);
        /*--------------------------------------------------------------------------------------------------*/

        if ("Recruter".equals(userRole)) {
            Button btnAddTask = new Button("Add Task");
            Button btnAddPost = new Button("Add Post");
            addAll(btnAddTask, btnAddPost);

            btnAddTask.addActionListener(e -> new AddTaskForm(this).show());
            btnAddPost.addActionListener(e -> new AddPostForm(this).show());

        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
