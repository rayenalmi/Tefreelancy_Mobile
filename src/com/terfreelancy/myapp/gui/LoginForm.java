/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.tefreelancy.utils.SessionManager;
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
public class LoginForm extends Form {

    private Container c;

    public LoginForm(Resources theme) {
        super();

        //UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        //UIBuilder uIBuilder = new UIBuilder();
        //c = uIBuilder.createContainer(theme, "Login");
        // Add the "c" container to the LoginForm form
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

        UIBuilder uIBuilder = new UIBuilder();
        Container c = uIBuilder.createContainer(theme, "Login");
        Form f = (Form) c;

        TextField email = (TextField) uIBuilder.findByName("email", c);

        TextField password = (TextField) uIBuilder.findByName("password", c);
        Button loginbtn = (Button) uIBuilder.findByName("login", c);
        Button signupbtn = (Button) uIBuilder.findByName("signup", c);

        signupbtn.addActionListener(er -> {
            new SignupForm(theme).show();
        });

        /*f.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f.show());
        f.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> new OffersForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> new CandidacyForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> new ForamtionForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> new FavorisForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> new BadgeForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> new GroupsForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> new WorkspaceForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> new ProfilForm(theme).show());
        f.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f.show());*/
        loginbtn.addActionListener(er -> {
            AdminUserFrom adminI = new AdminUserFrom(theme);

            adminI.getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt) -> {
                new LoginForm(theme).showBack();
            });

            System.out.println(email.getText() + " " + password.getText());
            if (email.getText().equals("admin") && password.getText().equals("admin")) {

                // admin part get all users
                String url = "http://127.0.0.1:8000/user/getallusers";

                ConnectionRequest request = new ConnectionRequest() {
                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        JSONParser parser = new JSONParser();
                        Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                        System.out.println("Response: " + response);
                        Object successValue = response.get("root");
                        System.out.println("Success value: " + successValue);
                        ArrayList<Object> usersJSON = new ArrayList<>();
                        ArrayList<Freelancer> users = new ArrayList<>();

                        usersJSON = new ArrayList<>((Collection<? extends Object>) successValue);
                        System.out.println(usersJSON.size());

                        for (int i = 0; i < usersJSON.size(); i++) {
                            Map<String, Object> jsonObject = (Map<String, Object>) usersJSON.get(i);
                            String fname = (String) jsonObject.get("fname");
                            Object idd = jsonObject.get("id");
                            double d = (double) idd;
                            int id = (int) d;

                            Object phonee = jsonObject.get("phone");
                            double ds = (double) phonee;
                            int phone = (int) ds;

                            users.add(new Freelancer(id, (String) jsonObject.get("lname"), (String) jsonObject.get("fname"), phone, (String) jsonObject.get("email"), (String) jsonObject.get("photo")));

                        }

                        adminI.removeAll();
                        for (int i = 0; i < users.size(); i++) {

                            //Label lb = new Label(users.get(i).getEmail());
                            //adminI.add(designOneUser(users.get(i), adminI, theme));
                            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            Label email = new Label(users.get(i).getEmail());
                            Label name = new Label(users.get(i).getPrenom() + " " + users.get(i).getNom());
                            Button show = new Button("Show");
                            Button delete = new Button("Delete");
                            Freelancer f = users.get(i);
                            int index = i;
                            delete.addActionListener(er -> {

                                String url = "http://127.0.0.1:8000/user/deleteUser"; // replace with your API URL
                                String requestBody = "{\"id\": " + f.getId() + " }"; // replace with your login credentials as JSON
                                System.out.println(requestBody);

                                ConnectionRequest request = new ConnectionRequest() {
                                    @Override
                                    protected void readResponse(InputStream input) throws IOException {
                                        JSONParser parser = new JSONParser();
                                        Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                                        System.out.println("Response: " + response);
                                        Object successValue = response.get("succes");
                                        System.out.println("Success value: " + successValue);
                                        users.remove(index);
                                        adminI.refreshTheme();

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
                                adminI.refreshTheme();
                            });

                            show.addActionListener(er -> {
                                Form f2 = new Form("Details", BoxLayout.y());
                                Label nom = new Label("nom : " + f.getNom());
                                Label prenom = new Label("prenom : " + f.getPrenom());
                                Label xemail = new Label("Email : " + f.getEmail());
                                Label role = new Label("Role : Freelancer ");

                                f2.add(nom);
                                f2.add(prenom);
                                f2.add(xemail);
                                f2.add(role);

                                f2.getToolbar().addCommandToLeftBar("Back", null, ev -> new LoginForm(theme).show());
                                f2.show();
                            });

                            name.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
                                Dialog.show("User", "Nom : " + f.getNom() + " \n Tel : " + f.getTel(), "Ok", null);
                            });

                            C2.add(email);
                            C2.add(show);
                            C2.add(delete);
                            adminI.add(C2);
                            adminI.refreshTheme();
                        }
                        //adminI.add(createFormationCard("Java", 12, 30, "https://thumbs.dreamstime.com/b/banni%C3%A8re-plate-de-web-style-conception-pour-le-chemin-au-succ%C3%A8s-niveaux-d-enseignement-formation-du-personnel-sp%C3%A9cialisation-102229928.jpg"));
                        //adminI.add(createFormationCard("Java", 12, 30, "https://thumbs.dreamstime.com/b/banni%C3%A8re-plate-de-web-style-conception-pour-le-chemin-au-succ%C3%A8s-niveaux-d-enseignement-formation-du-personnel-sp%C3%A9cialisation-102229928.jpg"));

                        adminI.refreshTheme();

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

                adminI.show();
            } else {
                //Dialog.show("error", "Login ou PWD invalide", "ok", "Cancel");
                String url = "http://127.0.0.1:8000/user/signinmobile"; // replace with your API URL
                String requestBody = "{\"email\": \"" + email.getText() + "\" , \"password\": \"" + password.getText() + "\"}"; // replace with your login credentials as JSON
                System.out.println(requestBody);

                //lb.setText("Welcome : " + email.getText());
                ConnectionRequest request = new ConnectionRequest() {
                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        JSONParser parser = new JSONParser();
                        Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                        System.out.println("Response: " + response);
                        Object successValue = response.get("id");
                        System.out.println("Success value with id " + successValue);

                        if (successValue != null) {
                            Map<String, Object> jsonObject = (Map<String, Object>) response;
                            Object idd = jsonObject.get("id");
                            double d = (double) idd;
                            int id = (int) d;

                            Object phonee = jsonObject.get("phone");
                            double ds = (double) phonee;
                            int phone = (int) ds;
                            SessionManager session = SessionManager.getInstance();
                            Freelancer f = new Freelancer(id, (String) jsonObject.get("lname"), (String) jsonObject.get("fname"), phone, (String) jsonObject.get("email"), (String) jsonObject.get("photo"));
                            System.out.println(f);
                            session.setCurrentUser(f);
                            new OffersForm(theme).show();
                        } else {
                            Object errorValue = response.get("error");
                            Dialog.show("error", (String) errorValue, "ok", "Cancel");
                        }

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
            }

        });

        Button addFormation = new Button("Add Formation");
        
        addFormation.addActionListener(l -> {
        //Container c = uIBuilder1.createContainer(theme, "Login");
        } );
        
        this.add(addFormation);
        this.add(c);

    }

    public static void showLoginForm(Resources theme) {
        LoginForm loginForm = new LoginForm(theme);
        loginForm.show();
    }

    public Container designOneUser(Freelancer f, Form fr, Resources theme) {
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label email = new Label(f.getEmail());
        Label name = new Label(f.getPrenom() + " " + f.getNom());
        Button show = new Button("Show");
        Button delete = new Button("Delete");

        delete.addActionListener(er -> {

            String url = "http://127.0.0.1:8000/user/deleteUser"; // replace with your API URL
            String requestBody = "{\"id\": " + f.getId() + " }"; // replace with your login credentials as JSON
            System.out.println(requestBody);

            ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    System.out.println("Response: " + response);
                    Object successValue = response.get("succes");
                    System.out.println("Success value: " + successValue);
                    fr.refreshTheme();

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
            fr.refreshTheme();
        });

        show.addActionListener(er -> {
            Form f2 = new Form("Details", BoxLayout.y());
            Label nom = new Label("nom : " + f.getNom());
            Label prenom = new Label("prenom : " + f.getPrenom());
            Label xemail = new Label("Email : " + f.getEmail());
            Label role = new Label("Role : Freelancer ");

            f2.add(nom);
            f2.add(prenom);
            f2.add(xemail);
            f2.add(role);

            f2.getToolbar().addCommandToLeftBar("Back", null, ev -> new LoginForm(theme).show());
            f2.show();
        });

        name.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            Dialog.show("User", "Nom : " + f.getNom() + " \n Tel : " + f.getTel(), "Ok", null);
        });

        C2.add(email);
        C2.add(show);
        C2.add(delete);

        //C2.setLeadComponent(name);
        return C2;
    }

    public Container createFormationCard(String name, int hours, int lessons, String imagePath) {
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

        return card;
    }
}
