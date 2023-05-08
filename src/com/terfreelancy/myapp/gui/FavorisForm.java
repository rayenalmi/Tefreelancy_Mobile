/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.tefreelancy.utils.SessionManager;
import com.terfreelancy.entities.Formation;
import com.terfreelancy.entities.Freelancer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author ROG
 */
public class FavorisForm extends Form {

    public FavorisForm(Resources theme) {
        ArrayList<Formation> formations = new ArrayList<>();
        SessionManager session = SessionManager.getInstance();
        Freelancer f = session.getCurrentUser();

        setTitle("Favoris");

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> new OffersForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("My Offers", null, ev -> new MyOffersForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> new CandidacyForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> new ForamtionForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> new FavorisForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> new BadgeForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> new GroupsForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> new WorkspaceForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> new ProfilForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> new LoginForm(theme).show());

        String url = "http://127.0.0.1:8000/formation/getallmobilefav";
        String requestBody = "{\"id\": " + f.getId() + "}"; // replace with your login credentials as JSON
        System.out.println(requestBody);
        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                System.out.println("Response: " + response);
                Object successValue = response.get("root");
                System.out.println("Success value: " + successValue);
                ArrayList<Object> offreJSON = new ArrayList<>();
                offreJSON = new ArrayList<>((Collection<? extends Object>) successValue);

                //Offre o = new Offre(id, (String) jsonObject.get("name"), (String) jsonObject.get("decs"), (String) jsonObject.get("duration"), (String) jsonObject.get("keyword"),salaire , id_recruter);
                for (int i = 0; i < offreJSON.size(); i++) {
                    Map<String, Object> jsonObject = (Map<String, Object>) offreJSON.get(i);
                    Object idd = jsonObject.get("id");
                    double d = (double) idd;
                    int id = (int) d;

                    Object salJ = jsonObject.get("nbh");
                    double dsal = (double) salJ;
                    int nbh = (int) dsal;

                    Object idrecJ = jsonObject.get("nbl");
                    double idrec = (double) idrecJ;
                    int nbl = (int) idrec;

                    formations.add(new Formation(id, (String) jsonObject.get("name"), nbh, nbl));

                }

                for (int i = 0; i < formations.size(); i++) {
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    c3.add(new Label(formations.get(i).getName()));
                    c3.add(new Button("Update"));

                    c.add(createFormationCard(formations.get(i), "https://thumbs.dreamstime.com/b/banni%C3%A8re-plate-de-web-style-conception-pour-le-chemin-au-succ%C3%A8s-niveaux-d-enseignement-formation-du-personnel-sp%C3%A9cialisation-102229928.jpg"));
                }
                refreshTheme();

            }

            @Override
            protected void handleErrorResponseCode(int code, String message) {
                System.out.println("Error: " + message);
            }
        };
        request.setUrl(url);
        request.setPost(true);
        request.setRequestBody(requestBody);
        request.setContentType("application/json");
        NetworkManager.getInstance().addToQueue(request);

        this.add(c);
    }

    public Container createFormationCard(Formation f, String imagePath) {
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        // Create a container for the card
        Container card = new Container(new BorderLayout());

        // Create a container for the information
        Container info = new Container(new GridLayout(3, 1));

        // Add the name label to the info container
        Label nameLabel = new Label(f.getName());
        nameLabel.setUIID("FormationCardNameLabel");
        info.add(nameLabel);

        // Add the hours label to the info container
        Label hoursLabel = new Label(f.getNbH() + " hours");
        hoursLabel.setUIID("FormationCardHoursLabel");
        info.add(hoursLabel);

        // Add the lessons label to the info container
        Label lessonsLabel = new Label(f.getNbL() + " lessons");
        lessonsLabel.setUIID("FormationCardLessonsLabel");
        info.add(lessonsLabel);

        // Load the image from the path
        Image image = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(100, 100), true), imagePath, imagePath, URLImage.RESIZE_SCALE);

        // Add the image to the center of the card
        card.addComponent(BorderLayout.CENTER, new ScaleImageLabel(image));

        // Add the info container to the left of the card
        card.addComponent(BorderLayout.WEST, info);
        Button btn = new Button("Show More");

        SessionManager session = SessionManager.getInstance();
        Freelancer ff = session.getCurrentUser();

        btn.addActionListener(l -> {
            //Dialog.show("error", "Login ou PWD invalide", "ok", "Cancel");
            String url = "http://127.0.0.1:8000/formation/getallchptersmobile"; // replace with your API URL
            String requestBody = "{\"id\": " + f.getId() + "}"; // replace with your login credentials as JSON
            System.out.println(requestBody);

            //lb.setText("Welcome : " + email.getText());
            ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    System.out.println("Response:  get chpters" + response);
                    Object successValue = response.get("root");
                    System.out.println("Success get chapters" + successValue);

                    ArrayList<Object> chaptersJSON = new ArrayList<>();
                    chaptersJSON = new ArrayList<>((Collection<? extends Object>) successValue);

                    Form chaptersFrom = new Form(new BoxLayout(BoxLayout.Y_AXIS));

                    chaptersFrom.setTitle("Chapters");

                    for (int i = 0; i < chaptersJSON.size(); i++) {
                        Map<String, Object> jsonObject = (Map<String, Object>) chaptersJSON.get(i);

                        // Create a container for the card
                        Container cardContainer = new Container(new BorderLayout());

                        // Create a label for the name
                        Label nameLabel = new Label((String) jsonObject.get("name"));

                        // Create a label for the context
                        SpanLabel contextLabel = new SpanLabel((String) jsonObject.get("context"));
                        contextLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
                        contextLabel.getAllStyles().setFgColor(0x0000ff);
                        contextLabel.getAllStyles().setPadding(5, 5, 5, 5);
                        contextLabel.getAllStyles().setMargin(2, 2, 2, 2);
                        contextLabel.getAllStyles().setBorder(Border.createLineBorder(1, 0x0000ff));
                        contextLabel.getStyle().setAlignment(Component.CENTER);

                        // Load the image using HTTPS
                        String imageUrl = "https://eastgateacademy.ca/wp-content/uploads/2021/12/Learning.jpg";
                        EncodedImage placeholderImage = EncodedImage.createFromImage(FontImage.createMaterial(FontImage.MATERIAL_PLACE, "Place", 48), false);
                        URLImage image = URLImage.createToStorage(placeholderImage, "imageKey", imageUrl, URLImage.RESIZE_SCALE);

                        // Create a label for the image
                        Label imageLabel = new Label(image);

                        // Add the name, context, and image labels to the card container
                        cardContainer.add(BorderLayout.NORTH, nameLabel);
                        cardContainer.add(BorderLayout.CENTER, contextLabel);
                        cardContainer.add(BorderLayout.SOUTH, imageLabel);

                        // Show the card container
                        chaptersFrom.add(cardContainer);
                        chaptersFrom.refreshTheme();
                    }
                    chaptersFrom.getToolbar().addCommandToLeftBar("Back", null, ev -> show());
                    chaptersFrom.show();

                }

                @Override
                protected void handleErrorResponseCode(int code, String message) {
                    System.out.println("Error: " + message);
                }
            };
            request.setUrl(url);
            request.setPost(true);
            request.setRequestBody(requestBody);
            request.setContentType("application/json");
            NetworkManager.getInstance().addToQueue(request);

        });

        // Set the UIID for the card
        card.setUIID("FormationCard");
        C2.add(card);
        C2.add(btn);
        return C2;
    }
}
