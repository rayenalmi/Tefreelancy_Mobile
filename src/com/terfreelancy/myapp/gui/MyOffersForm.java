/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.tefreelancy.utils.SessionManager;
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
public class MyOffersForm extends Form {

    public MyOffersForm(Resources theme) {
        ArrayList<Offre> offres = new ArrayList<>();

        SessionManager session = SessionManager.getInstance();
        Freelancer f = session.getCurrentUser();
        System.out.println("-------------" + f + " -------------");

        setTitle("Offers");

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

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        String url = "http://127.0.0.1:8000/start/offer/getoffersrecrutermobile";
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
                    // cast input json double to int
                    Object idd = jsonObject.get("id");
                    double d = (double) idd;
                    int id = (int) d;

                    Object salJ = jsonObject.get("salaire");
                    double dsal = (double) salJ;
                    float salaire = (float) dsal;

                    /*Object idrecJ = jsonObject.get("");
                    double idrec = (double) idrecJ;
                    int id_recruter = (int) idrec;*/
                    offres.add(new Offre(id, (String) jsonObject.get("name"), (String) jsonObject.get("decs"), (String) jsonObject.get("duration"), (String) jsonObject.get("keyword"), salaire, f.getId()));
                }

                for (int i = 0; i < offres.size(); i++) {
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    c3.add(new Label(offres.get(i).getNom()));
                    c3.add(new Button("Update"));
                    Button btnDelete = new Button("Delete");
                    int id = offres.get(i).getId_offre();
                    btnDelete.addActionListener(l -> {
                        deleteOffer(id);
                        refreshTheme();
                    });
                    refreshTheme();

                    c3.add(btnDelete);

                    c.add(c3);
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

        UIBuilder uIBuilder = new UIBuilder();
        Button bntcreate = new Button("Create Offer");
        bntcreate.addActionListener(e -> {
            Container c1 = uIBuilder.createContainer(theme, "CreateOffer");
            Form co = (Form) c1;
            co.show();
            co.getToolbar().addCommandToLeftBar("Back", null, ev -> this.show());

            TextField name = (TextField) uIBuilder.findByName("name", c1);
            TextField desc = (TextField) uIBuilder.findByName("desc", c1);
            TextField duration = (TextField) uIBuilder.findByName("duration", c1);
            TextField key = (TextField) uIBuilder.findByName("key", c1);
            TextField sal = (TextField) uIBuilder.findByName("sal", c1);

            Button bnt = (Button) uIBuilder.findByName("create", c1);

            bnt.addActionListener(l -> {
                String url1 = "http://127.0.0.1:8000/start/offer/newmobile";

                String requestBody1 = "{\"name\": \"" + name.getText() + "\" , \"desc\": \"" + desc.getText() + "\" , \"duration\": \"" + duration.getText() + "\" , \"key\": \"" + key.getText() + "\" , \"sal\": \"" + sal.getText() + "\" , \"id\": " + f.getId() + "  }"; // replace with your login credentials as JSON
                System.out.println(requestBody1);

                //lb.setText("Welcome : " + email.getText());
                ConnectionRequest request1 = new ConnectionRequest() {
                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        JSONParser parser = new JSONParser();
                        Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                        System.out.println("Response: add offer " + response);

                        new MyOffersForm(theme).show();
                    }

                    @Override
                    protected void handleErrorResponseCode(int code, String message) {
                        System.out.println("Error: " + message);
                    }
                };
                request1.setUrl(url1);
                request1.setPost(true);
                request1.setRequestBody(requestBody1);
                request1.setContentType("application/json");
                NetworkManager.getInstance().addToQueue(request1);

            });

        });
        c.add(bntcreate);
        this.add(c);
    }

    public void deleteOffer(int id) {
        String url = "http://127.0.0.1:8000/start/offer/deleteOffer"; // replace with your API URL
        String requestBody = "{\"id\": " + id + " }"; // replace with your login credentials as JSON
        System.out.println(requestBody);

        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                System.out.println("Response: " + response);
                Object successValue = response.get("succes");
                System.out.println("Success value: " + successValue);

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
        //fr.refreshTheme();
    }
}
