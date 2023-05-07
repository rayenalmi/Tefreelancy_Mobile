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
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author ROG
 */
public class SignupForm extends Form {

    public SignupForm(Resources theme) {
        super();

        UIBuilder uIBuilder = new UIBuilder();
        Container c = uIBuilder.createContainer(theme, "Signup");
        Form f = (Form) c;

        Button signupbtnform = (Button) uIBuilder.findByName("signup", c);
        signupbtnform.addActionListener(l -> {
            TextField fname = (TextField) uIBuilder.findByName("fname", c);
            TextField lname = (TextField) uIBuilder.findByName("lname", c);
            TextField newemail = (TextField) uIBuilder.findByName("email", c);
            TextField newpassword = (TextField) uIBuilder.findByName("password", c);
            TextField phone = (TextField) uIBuilder.findByName("phone", c);

            String requestBody = "{\"email\": \"" + newemail.getText() + "\" , \"password\": \"" + newpassword.getText() + "\" , \"fname\": \"" + fname.getText() + "\" , \"lname\": \"" + lname.getText() + "\" , \"phone\": \"" + phone.getText() + "\"}"; // replace with your login credentials as JSON
            System.out.println(requestBody);

            String url = "http://127.0.0.1:8000/user/signupfreelancermobile";

            ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    System.out.println("Response: " + response);
                    Object successValue = response.get("succes");
                    System.out.println("Success value: " + successValue);
                    new LoginForm(theme).show();
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

        this.add(c);
    }

    public static void showSignupForm(Resources theme) {
        SignupForm signUpForm = new SignupForm(theme);
        signUpForm.show();
    }
}
