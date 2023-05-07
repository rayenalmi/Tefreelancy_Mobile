/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
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
public class OffersForm extends Form {

    public OffersForm(Resources theme) {
        //getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f.show());
        getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> new OffersForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("My Offers", null, ev -> new MyOffersForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> new CandidacyForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> new ForamtionForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> new FavorisForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> new BadgeForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> new GroupsForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> new WorkspaceForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> new ProfilForm(theme).show());
        getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> this.show());

        ArrayList<Offre> offres = new ArrayList<>();

        setTitle("Offers");
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        String url = "http://127.0.0.1:8000/start/offer/getoffersmobile";

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

                    Object salJ = jsonObject.get("salaire");
                    double dsal = (double) salJ;
                    float salaire = (float) dsal;

                    Object idrecJ = jsonObject.get("idrec");
                    double idrec = (double) idrecJ;
                    int id_recruter = (int) idrec;

                    offres.add(new Offre(id, (String) jsonObject.get("name"), (String) jsonObject.get("decs"), (String) jsonObject.get("duration"), (String) jsonObject.get("keyword"), salaire, id_recruter));
                }

                for (int i = 0; i < offres.size(); i++) {
                    c.add(new Label(offres.get(i).getNom()));
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
        request.setContentType("application/json");
        NetworkManager.getInstance().addToQueue(request);

        this.add(c);

    }
}
