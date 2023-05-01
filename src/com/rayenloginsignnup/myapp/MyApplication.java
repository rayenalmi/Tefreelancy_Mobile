package com.rayenloginsignnup.myapp;

import com.codename1.components.ImageViewer;
import static com.codename1.ui.CN.*;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.GroupConstraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;
    ConnectionRequest con;

    private Form home;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
            }
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    private void handleClick() {
        System.out.println("Button clicked!");
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        UIBuilder uIBuilder = new UIBuilder();
        Container c = uIBuilder.createContainer(theme, "Login");
        //Form f = (Form) uIBuilder.findByName("Login", c);
        Form f1 = (Form) c;
        TextField email = (TextField) uIBuilder.findByName("email", c);

        TextField password = (TextField) uIBuilder.findByName("password", c);
        Button btn = (Button) uIBuilder.findByName("login", c);

        Form f2 = new Form("Home", new FlowLayout(CENTER, CENTER));

        Form f3 = new Form("Offers", new FlowLayout(CENTER, CENTER));
        Form f33 = new Form("Candidacy", new FlowLayout(CENTER, CENTER));
        Form f4 = new Form("Foramtion", new FlowLayout(CENTER, CENTER));
        Form f5 = new Form("Favoris", new FlowLayout(CENTER, CENTER));
        Form f6 = new Form("Badges", new FlowLayout(CENTER, CENTER));
        Form f8 = new Form("Groups", new FlowLayout(CENTER, CENTER));
        Form f10 = new Form("Workspace", new FlowLayout(CENTER, CENTER));
        Form f11 = new Form("Profil", new FlowLayout(CENTER, CENTER));

        Label lb = new Label("nom : ");
        f2.add(lb);

        SpanLabel sp = new SpanLabel("Enseignante en informatique et membre de l'UP JAVA");
        f3.add(sp);
        // navbar Form 2
        f2.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f2.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f2.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f2.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f2.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f2.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f2.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f2.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f2.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f2.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // navvbbar Form 3
        f3.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f3.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f3.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f3.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f3.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f3.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f3.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f3.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f3.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f3.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // NAVIGATION NAVBAR FROM 33
        f33.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f33.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f33.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f33.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f33.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f33.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f33.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f33.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f33.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f33.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // Navigation navbar Form 4 
        f4.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f4.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f4.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f4.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f4.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f4.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f4.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f4.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f4.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f4.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // NAVIFATION FORM 5 
        f5.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f5.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f5.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f5.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f5.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f5.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f5.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f5.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f5.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f5.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // NAVIGATION FORM 6  
        f6.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f6.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f6.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f6.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f6.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f6.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f6.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f6.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f6.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f6.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // NAVIGATION FORM 8
        f8.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f8.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f8.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f8.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f8.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f8.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f8.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f8.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f8.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f8.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // NAVIGATION FORM 10 
        f10.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f10.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f10.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f10.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f10.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f10.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f10.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f10.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f10.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f10.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        // NOVIGAATION FORM 11 
        f11.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f2.show());
        f11.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> f3.show());
        f11.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> f33.show());
        f11.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> f4.show());
        f11.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> f5.show());
        f11.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> f6.show());
        f11.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> f8.show());
        f11.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> f10.show());
        f11.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> f11.show());
        f11.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> f1.show());
        
        
        btn.addActionListener(er -> {
            System.out.println(email.getText() + " " + password.getText());

            String url = "http://127.0.0.1:8000/user/signinmobile"; // replace with your API URL
            String requestBody = "{\"email\": \"" + email.getText() + "\" , \"password\": \"" + password.getText() + "\"}"; // replace with your login credentials as JSON
            System.out.println(requestBody);

            lb.setText("Welcome : " + email.getText());
            f2.show();
            /*ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    System.out.println("Response: " + response);
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
            NetworkManager.getInstance().addToQueue(request);*/

 /* String url = "http://127.0.0.1:8000/user/signinmobile";

            con = new ConnectionRequest(url);
            con.setUrl(url);
            con.setPost(true);
            con.addArgument("test", "cqsfsfqs");
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {

                    try {
                        String  ch = new String(con.getResponseData(), "utf-8");
                        System.out.println(con.getResponseData() + " ddd" );
                    } catch (UnsupportedEncodingException ex) {
                        System.out.println("eror");
                    }

                }
            });
             */
        });

        //f3.getToolbar().addMaterialCommandToOverflowMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, TOP, ev->Display.getInstance().exitApplication());
        f1.show();

    }

    private void showOKForm(String name) {
        Form f = new Form("Thanks", BoxLayout.y());
        f.add(new SpanLabel("Thanks " + name + " for your submission. You can press the back arrow and try again"));
        f.getToolbar().setBackCommand("", e -> home.showBack());
        f.show();
    }

    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

}
