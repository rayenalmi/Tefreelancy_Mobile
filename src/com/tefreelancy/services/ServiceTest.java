/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tefreelancy.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.tefreelancy.utils.Statics;
import com.terfreelancy.entities.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassi
 */
public class ServiceTest {
    public ArrayList<Test> tests;
    public static ServiceTest instance = null;
    public boolean resultOK;
    public ConnectionRequest req;
    
    
    public ServiceTest(){
        req = new ConnectionRequest();
             
    }
    public static ServiceTest getInstance() {
        if (instance == null) {
            instance = new ServiceTest();
        }
        return instance;
    }
    
    
    public boolean addTest(Test t) {

        String name = t.getName();
        String type =  t.getType();
        
        String url = Statics.BASE_URL + "/testJSON/new?name=" + t.getName() + "&type=" + t.getType();
        //String url = Statics.BASE_URL + "create/" + status + "/" + name;
        ConnectionRequest req = new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Test> parseTests(String jsonText) {
        try {
            tests = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> testsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) testsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Test t = new Test();
                float id = Float.parseFloat(obj.get("idTest").toString());
                t.setId_test((int) id);
                t.setName(obj.get("name").toString());
                t.setType(obj.get("type").toString());
                tests.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tests;
    }
    public ArrayList<Test> getAllTests() {
        String url = Statics.BASE_URL + "/testJSON/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tests = parseTests(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tests;
    }
     public boolean updateTest(Test t)
    {
        String url = Statics.BASE_URL+"/testJSON/edit/"+t.getId_test()+"?name="+t.getName()+"&type="+t.getType();
        req.setUrl(url);
    req.addResponseListener((e) -> {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        //System.out.println("data"+str);
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
    }
    public boolean deleteTest(int id) {
        String url = Statics.BASE_URL + "/testJSON/delete/" + id ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Test> searchTests(String query) {
        ArrayList<Test> searchResults = new ArrayList<>();
        for (Test t : tests) {
            if (t.getName().contains(query) || t.getType().contains(query)) {
                searchResults.add(t);
            }
        }
        return searchResults;
    }

}
