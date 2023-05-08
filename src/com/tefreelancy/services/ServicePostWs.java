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
import com.terfreelancy.entities.PublicationWs;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServicePostWs {
     public ArrayList<PublicationWs> PublicationWss;

    public static ServicePostWs instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePostWs() {
        req = new ConnectionRequest();
    }

    public static ServicePostWs getInstance() {
        if (instance == null) {
            instance = new ServicePostWs();
        }
        return instance;
    }

    public boolean addPublicationWs(PublicationWs t, int workspaceId) {
    String url = GroupsUtils.BASE_URL + "/publication/ws/addPostJSON/new/" + workspaceId + "?title=" + t.getTitle() + "&content=" + t.getContent()+"&author="+t.getAuthor();
  
    req.setUrl(url);

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

    public boolean updatePost(PublicationWs t, int workspaceId) {
    String url = GroupsUtils.BASE_URL + "/publication/ws/updatePostJSON/" + t.getId() + "/" + workspaceId + "?title=" + t.getTitle() + "&content=" + t.getContent()+"&author="+t.getAuthor();
    req.setUrl(url);
    req.setPost(false); // Set the request method to POST before adding arguments
    req.addArgument("title", t.getTitle());
    req.addArgument("content", t.getContent());
    req.addArgument("author", t.getAuthor());
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
    public ArrayList<PublicationWs> parsePublicationWss(String jsonText){
        try {
            PublicationWss=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)categListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
              try{
                //Création des tâches et récupération de leurs données
               PublicationWs t = new PublicationWs();
              
                float id = Float.parseFloat(obj.get("id").toString());
                t.setTitle(obj.get("title").toString());
                t.setId((int)id);
                t.setContent(obj.get("content").toString());
                t.setAuthor(obj.get("author").toString());
                String deadlineStr = obj.get("creationdate").toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date deadline = sdf.parse(deadlineStr);
                t.setCreationDate(deadline);
              PublicationWss.add(t);
                } catch (ParseException e) {
                e.printStackTrace();
            }


              
            }
            
            
        } catch (IOException ex) {
            
        }
          return PublicationWss;
    }

       public boolean deletePost(int id, int workspaceId) {
        String url = GroupsUtils.BASE_URL + "/publication/ws/deletePostJSON/" + id + "/" + workspaceId;
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
       
    public ArrayList<PublicationWs> getAllPublicationWss(int workspaceId) {
        String url = GroupsUtils.BASE_URL + "/publication/ws/AllPostsJson/"+workspaceId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PublicationWss = parsePublicationWss(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return PublicationWss;
    }
}
