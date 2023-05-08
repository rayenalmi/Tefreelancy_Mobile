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
import com.tefreelancy.utils.GroupsUtils;
import com.terfreelancy.entities.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassi
 */
public class ServiceQuestion {
    public ArrayList<Question> questions;
    public static ServiceQuestion instance = null;
    public boolean resultOK;
    public ConnectionRequest req;
    
    
    public ServiceQuestion(){
        req = new ConnectionRequest();
             
    }
    public static ServiceQuestion getInstance() {
        if (instance == null) {
            instance = new ServiceQuestion();
        }
        return instance;
    }
    
    
    public boolean addQuestion(Question q) {

        String question = q.getQuest();
        String choice1 =  q.getChoice1();
        String choice2 =  q.getChoice2();
        String choice3 =  q.getChoice3();
        String response =  q.getResponse();
        
        String url = GroupsUtils.BASE_URL + "/questionJSON/new?quest=" + q.getQuest()+ "&choice1=" + q.getChoice1()+ "&choice2=" + q.getChoice2()+ "&choice3=" + q.getChoice3()+ "&response=" + q.getResponse()+ "&idTest=" + q.getId_test();
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
    public ArrayList<Question> parseQuestions(String jsonText){
    try {
        if (jsonText == null) {
            // Handle null input
            return null;
        }
        questions=new ArrayList<>();
        JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
        Map<String,Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)categListJson.get("root");
        
        //Parcourir la liste des tâches Json
        for(Map<String,Object> obj : list){
        //Création des tâches et récupération de leurs données
        Question t = new Question();

        float id = obj.get("idQuestion") != null ? Float.parseFloat(obj.get("idQuestion").toString()) : 0.0f;
        t.setQuest(obj.get("quest") != null ? obj.get("quest").toString() : "");
        t.setId_question((int)id);
        t.setChoice1(obj.get("choice1") != null ? obj.get("choice1").toString() : "");
        t.setChoice2(obj.get("choice2") != null ? obj.get("choice2").toString() : "");
        t.setChoice3(obj.get("choice3") != null ? obj.get("choice3").toString() : "");
        t.setResponse(obj.get("response") != null ? obj.get("response").toString() : "");
        questions.add(t);
    }
    } catch (IOException ex) {
        // Handle exception
    }
    return questions;
}

//    public ArrayList<Question> parseQuestions(String jsonText) {
//        try {
//            questions = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> questionsListJson
//                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//            List<Map<String, Object>> list = (List<Map<String, Object>>) questionsListJson.get("root");
//            for (Map<String, Object> obj : list) {
//                Question q = new Question();
//                float id = Float.parseFloat(obj.get("idQuestion").toString());
//                float id1 = Float.parseFloat(obj.get("idTest").toString());
//                q.setId_question((int) id);
//                q.setQuest(obj.get("quest").toString());
//                q.setChoice1(obj.get("choice1").toString());
//                q.setChoice2(obj.get("choice2").toString());
//                q.setChoice3(obj.get("choice3").toString());
//                q.setResponse(obj.get("response").toString());
//                q.setId_test((int) id1);
//                questions.add(q);
//            }
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return questions;
//    }
    public ArrayList<Question> getAllQuestions() {
        String url = GroupsUtils.BASE_URL + "/questionJSON/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questions = parseQuestions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return questions;
    }
    public ArrayList<Question> getAllQuestionsById(int id) {
        String url = GroupsUtils.BASE_URL + "/testJSON/passtest/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questions = parseQuestions(new String(req.getResponseData()));
                System.out.println("questions" + questions);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return questions;
    }
     public boolean updateQuestion(Question t)
    {
        String url = GroupsUtils.BASE_URL+"/questionJSON/edit/"+t.getId_question()+"?quest="+t.getQuest()+"&choice1="+t.getChoice1()+"&choice2="+t.getChoice2()+"&choice3="+t.getChoice3()+"&response="+t.getResponse()+"&idTest="+t.getId_test();
        req.setUrl(url);
    req.addResponseListener((e) -> {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        //System.out.println("data"+str);
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
    }
    public boolean deleteQuestion(int id) {
        String url = GroupsUtils.BASE_URL + "/questionJSON/delete/" + id ;
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
   

    
    
}
