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
import com.terfreelancy.entities.Achievement;
import com.terfreelancy.entities.Experience;
import com.tefreelancy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceAchievement {
    public ArrayList<Achievement> achs;
    public Achievement ach;
    public Achievement achid;

    public static ServiceAchievement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAchievement() {
        req = new ConnectionRequest();
    }

    public static ServiceAchievement getInstance() {
        if (instance == null) {
            instance = new ServiceAchievement();
        }
        return instance;
    }

    public boolean addAch(Achievement a) {

        int idoffer = a.getId_offer();
        float rate =  a.getRate();
        
                
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        //String url = Statics.BASE_URL + "create/" + status + "/" + name;
String url = Statics.BASE_URL+"//jsonnew?idOffer="+idoffer+"&rate="+rate ;
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
    /*
    public boolean updateExp(Experience e) {
    //String url = Statics.BASE_URL + "/workspace/" + t.getId() + "/editJson?name=" + t.getName()+ "&description=" + t.getDescription();
    String url = Statics.BASE_URL + "/experienceJSON/"+e.getId_experience()+"/edit?title="+e.getTitle()+"&description="+e.getDescription()+"&location="+e.getLocation()+"&duration="+e.getDuration()+"&type="+e.getType(); 
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
}*/
  
   
   public boolean deleteExp(int id) {
        String url = Statics.BASE_URL + "/experienceJSON/delete/"+id;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Achievement> parseAch(String jsonText) {
        try {
            achs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> expsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) expsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Achievement a = new Achievement();
                
                float id = Float.parseFloat(obj.get("idAchivement").toString());
                a.setId((int) id);
                 float idOffer = Float.parseFloat(obj.get("idOffer").toString());
                 float rate = Float.parseFloat(obj.get("rate").toString());
                  
                a.setId_freelancer(190);
                a.setId_offer((int) idOffer);
                a.setRate((int) rate);
                achs.add(a);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return achs;
    }

    public ArrayList<Achievement> getAllAchs() {
        String url = Statics.BASE_URL + "/achievement/jsongetall";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                achs = parseAch(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return achs;
    }
    
    
    
    
    
    
    
    public Achievement parse1Ach(String jsonText) {
        Achievement ach = null;
        try {
            //
            //portfolios = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> skillsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) skillsListJson.get("root");
            for (Map<String, Object> obj : list) {
           
                
                Achievement a = new Achievement();
                
                float id = Float.parseFloat(obj.get("idAchivement").toString());
                a.setId((int) id);
                 float idOffer = Float.parseFloat(obj.get("idOffer").toString());
                 float rate = Float.parseFloat(obj.get("rate").toString());
                  
                a.setId_freelancer(190);
                a.setId_offer((int) idOffer);
                a.setRate((int) rate);
                achs.add(a);
 
          
                
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ach;
    }
    
    public ArrayList<Achievement> getAchById(int aId) {
         //Portfolio portfolioid;
    String url = Statics.BASE_URL + "/achievement/json/f/"+aId+"/";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            achs = parseAch(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return achs;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
