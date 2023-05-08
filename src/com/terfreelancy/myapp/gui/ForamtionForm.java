/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.tefreelancy.utils.SessionManager;
import com.terfreelancy.entities.Formation;
import com.terfreelancy.entities.Freelancer;
import com.terfreelancy.entities.Offre;
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
public class ForamtionForm extends Form {

    public ForamtionForm(Resources theme) {
        ArrayList<Formation> formations = new ArrayList<>();

        setTitle("Formations");

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

        String url = "http://127.0.0.1:8000/formation/getallmobile";
        //String requestBody = "{\"id\": " + f.getId() + "}"; // replace with your login credentials as JSON
        //System.out.println(requestBody);
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
        request.setPost(false);
        //request.setRequestBody(requestBody);
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
        Button btn = new Button("Add favoris");

        SessionManager session = SessionManager.getInstance();
        Freelancer ff = session.getCurrentUser();

        btn.addActionListener(l -> {
            //Dialog.show("error", "Login ou PWD invalide", "ok", "Cancel");
            String url = "http://127.0.0.1:8000/favoris/AddfavorisMobile"; // replace with your API URL
            String requestBody = "{\"idFormation\": " + f.getId() + " , \"id\": " + ff.getId() + "}"; // replace with your login credentials as JSON
            System.out.println(requestBody);

            //lb.setText("Welcome : " + email.getText());
            ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    System.out.println("Response: " + response);
                    Object successValue = response.get("success");
                    System.out.println("Success favoris" + successValue);

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

            Form currentForm = btn.getComponentForm();
            currentForm.removeComponent(btn);
            currentForm.revalidate();

        });

        // Set the UIID for the card
        card.setUIID("FormationCard");
        C2.add(card);
        C2.add(btn);
        return C2;
    }
}
