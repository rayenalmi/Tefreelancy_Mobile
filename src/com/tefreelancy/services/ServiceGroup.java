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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.tefreelancy.utils.GroupsUtils;
import com.terfreelancy.entities.Article;
import com.terfreelancy.entities.GroupPost;
import com.terfreelancy.entities.Groupe;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ameni
 */
public class ServiceGroup {

    public ArrayList<Groupe> groups;
    public ArrayList<GroupPost> posts;
    private final String apiKey = "062c1200a89c4e4bb2ea48b58cfc9459"; // Replace with your NewsAPI API key

    public static ServiceGroup instance = null;

    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceGroup() {
        req = new ConnectionRequest();
    }

    public static ServiceGroup getInstance() {
        if (instance == null) {
            instance = new ServiceGroup();
        }
        return instance;
    }
    
    public ArrayList<Groupe> searchGroups(String query) {
        ArrayList<Groupe> searchResults = new ArrayList<>();
        for (Groupe group : groups) {
            if (group.getNom().contains(query) || group.getDescription().contains(query)) {
                searchResults.add(group);
            }
        }
        return searchResults;
    }

    public boolean addGroup(Groupe g, int id) {
        String name = g.getNom();
        String desc = g.getDescription();
        String url = GroupsUtils.BASE_URL + "/json/community/new?name=" + name + "&desc=" + desc + "&id=" + id;
        req.setUrl(url);
        req.setPost(false);

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
     public boolean addPost(GroupPost gp, int id) {
        String grpContenu = gp.getContenu();
  
        String url = GroupsUtils.BASE_URL + "/json/grouppost/new/"+id+"?context="+grpContenu;
        req.setUrl(url);
        req.setPost(false);

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

    public ArrayList<GroupPost> parseGroupsPosts(String jsonText) throws IOException {
        ArrayList<GroupPost> posts = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Map<String, Object> jsonObject = (Map<String, Object>) parser.parse(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> groupsArray = (List<Map<String, Object>>) jsonObject.get("groupposts");
        for (Map<String, Object> obj : groupsArray) {
            GroupPost g = new GroupPost();
            float id = Float.parseFloat(obj.get("idGrouppost").toString());
            g.setId((int) id);
            if (obj.get("context") == null) {
                g.setContenu("null");
            } else {
                g.setContenu(obj.get("context").toString());
            }

            posts.add(g);

        }
        return posts;
    }

    public ArrayList<GroupPost> getAllGroupsPosts(int id) {
        String url = GroupsUtils.BASE_URL + "/json/community/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    posts = parseGroupsPosts(new String(req.getResponseData()));
                } catch (IOException ex) {
                }
                System.out.println("groupssss" + groups);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }

    public ArrayList<Groupe> parseGroups(String jsonText) {
        try {
            groups = new ArrayList<>();
            JSONParser j = new JSONParser();
            System.out.println(jsonText);

            Map<String, Object> groupsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) groupsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Groupe g = new Groupe();
                float id = Float.parseFloat(obj.get("idCommunity").toString());
                g.setId((int) id);
                if (obj.get("name") == null) {
                    g.setNom("null");
                } else {
                    g.setNom(obj.get("name").toString());
                }
                if (obj.get("description") == null) {
                    g.setNom("null");
                } else {
                    g.setDescription(obj.get("description").toString());
                }
                groups.add(g);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return groups;
    }

    public ArrayList<Groupe> getAllGroups() {
        String url = GroupsUtils.BASE_URL + "/json/community/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                groups = parseGroups(new String(req.getResponseData()));
                System.out.println("groupssss" + groups);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return groups;
    }

    public boolean deleteGroups(int id) {
        String url = GroupsUtils.BASE_URL + "/json/community/" + id + "/delete";

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }

    public boolean deletePosts(int id) {
        String url = GroupsUtils.BASE_URL + "/json/grouppost/" + id + "/delete";

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }

    public boolean updateGroup(Groupe g, int id) {
        String name = g.getNom();
        String desc = g.getDescription();
        String url = GroupsUtils.BASE_URL + "/json/community/" + id + "/edit?name=" + name + "&desc=" + desc + "&id=";
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            //System.out.println("data"+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updatePosts(GroupPost gp, int id) {
        String desc = gp.getContenu();
        String url = GroupsUtils.BASE_URL + "/json/grouppost/" + id + "/edit?context=" + desc;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            //System.out.println("data"+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
           public List<Article> getNews(String nomGroup) throws IOException {
        String apiKey = "062c1200a89c4e4bb2ea48b58cfc9459"; // Replace with your NewsAPI API key
        
        // Set up URL and connection
        String urlAPI = "https://newsapi.org/v2/everything?q=" + nomGroup + "&from=2023-05-01&sortBy=popularity&apiKey=" + apiKey;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(urlAPI);
        req.setPost(false);
        
        final List<Article> list = new ArrayList<>();
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    byte[] responseData = req.getResponseData();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(responseData);
                    JSONParser jsonParser = new JSONParser();
                    Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(byteArrayInputStream, "UTF-8"));
                    List<Map<String, Object>> articlesList = (List<Map<String, Object>>) response.get("articles");
                    for (Map<String, Object> articleMap : articlesList) {
                        String title = (String) articleMap.get("title");
                        String description = (String) articleMap.get("description");
                        Article article = new Article(title, description);
                        list.add(article);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return list;
    }
    

}
