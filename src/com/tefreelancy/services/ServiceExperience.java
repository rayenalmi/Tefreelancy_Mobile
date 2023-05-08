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
import com.terfreelancy.entities.Experience;
import com.terfreelancy.entities.Portfolio;
import com.terfreelancy.entities.Skill;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceExperience {
    public ArrayList<Experience> exps;
    public Experience exp;
    public Experience expid;

    public static ServiceExperience instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceExperience() {
        req = new ConnectionRequest();
    }

    public static ServiceExperience getInstance() {
        if (instance == null) {
            instance = new ServiceExperience();
        }
        return instance;
    }

    public boolean addExp(Experience e) {

        String title = e.getTitle();
        String desc =  e.getDescription();
        String location = e.getLocation(); 
        String duration = e.getDuration();
        String type= e.getType(); 
                
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        //String url = Statics.BASE_URL + "create/" + status + "/" + name;
String url = GroupsUtils.BASE_URL+"/experienceJSON/new?title="+ title +"&description="+ desc +"&location=" + location +"&duration="+ duration +"&type="+type ;
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

    
    
    
    
    
    
    
    
    
    
    //update 
    
    public boolean updateExp(Experience e) {
    //String url = Statics.BASE_URL + "/workspace/" + t.getId() + "/editJson?name=" + t.getName()+ "&description=" + t.getDescription();
    String url = GroupsUtils.BASE_URL + "/experienceJSON/"+e.getId_experience()+"/edit?title="+e.getTitle()+"&description="+e.getDescription()+"&location="+e.getLocation()+"&duration="+e.getDuration()+"&type="+e.getType(); 
//       /experienceJSON/53/edit?title=00000000000000000000000&description=0&location=0&duration=0&type=education
//       /portfolioJSON/222/edit?intro="hello%20to%20you"&about=thisistheabout
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
  
   
   public boolean deleteExp(int id) {
        String url = GroupsUtils.BASE_URL + "/experienceJSON/delete/"+id;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Experience> parseExps(String jsonText) {
        try {
            exps = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> expsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) expsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Experience e = new Experience();
                
                float id = Float.parseFloat(obj.get("idExperience").toString());
                e.setId_experience((int) id);
                e.setId_freelancer(190);
                e.setTitle(obj.get("title").toString());
                e.setDescription(obj.get("description").toString());
                e.setLocation(obj.get("location").toString());
                e.setDuration(obj.get("duration").toString());
                e.setType(obj.get("type").toString());
                e.setTitle(obj.get("title").toString());
                e.setTitle(obj.get("title").toString());

                
                
               
                exps.add(e); 
                
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return exps;
    }

    public ArrayList<Experience> getAllExps() {
        String url = GroupsUtils.BASE_URL + "/experienceJSON/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                exps = parseExps(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return exps;
    }
    
    
    
    
    
    
    
    public Experience parse1Skill(String jsonText) {
        Experience exp = null;
        try {
            //
            //portfolios = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> skillsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) skillsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Experience e = new Experience();
                
                float id = Float.parseFloat(obj.get("idExperience").toString());
                e.setId_experience((int)id);
                e.setId_freelancer(190);
                e.setTitle(obj.get("title").toString());
                e.setDescription(obj.get("description").toString());
                e.setLocation(obj.get("location").toString());
                e.setDuration(obj.get("duration").toString());
                e.setType(obj.get("type").toString());
 
               
                /*
                if (obj.get("name") == null) {
                    s.setName("name");
                } else {
                    s.setName(obj.get("name").toString());
                }*/
                exps.add(e); 
                
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return exp;
    }
    
    public ArrayList<Experience> getExpById(int eId) {
         //Portfolio portfolioid;
    String url = GroupsUtils.BASE_URL + "/experienceJSON/f/"+eId+"/";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            exps = parseExps(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return exps;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//end 
