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
import com.terfreelancy.entities.Portfolio;
import com.terfreelancy.entities.Skill;
import com.tefreelancy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceSkill {
    public ArrayList<Skill> skills; 
    public Skill skill;
    public Skill skillid;
    
    public static ServiceSkill instance; 
    public boolean resultOK; 
    private ConnectionRequest req; 
    
    public ServiceSkill(){
        req = new ConnectionRequest(); 
    }
    
    
    public static ServiceSkill getInstance(){
        if(instance == null){
            instance = new ServiceSkill(); 
        }
        return instance; 
    }
    
    
    public boolean addSkill(Skill s){
        String url = Statics.BASE_URL+"/skillsJSON/new?name="+s.getName()+"&level="+s.getLevel().toString() ;
                ConnectionRequest req = new ConnectionRequest(url);

        req.setUrl(url); 
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                //req.removeResponseListener(this); 
            }
        
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    
        return resultOK;
    }

 
    
    //update 
    
    public boolean updateSkill(Skill s) {
    //String url = Statics.BASE_URL + "/workspace/" + t.getId() + "/editJson?name=" + t.getName()+ "&description=" + t.getDescription();
    String url = Statics.BASE_URL + "/skillsJSON/"+s.getId_skill()+"/edit?name="+s.getName()+"&level="+s.getLevel(); 

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
  
   
   public boolean deleteSkill(int id) {
        String url = Statics.BASE_URL + "/skillsJSON/delete/"+id;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Skill> parseSkills(String jsonText){
        try {
            skills = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PortfoliosListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>) PortfoliosListJson.get("root");
            for(Map<String,Object> obj : list){
                Skill s = new Skill();
                float id = Float.parseFloat(obj.get("idSkills").toString());
                s.setId_skill((int) id);
                s.setId_freelancer(190);
                s.setName(obj.get("name").toString());
                s.setLevel(obj.get("level").toString());
                skills.add(s); 
                
            }
             
            
        } catch (IOException ex) {
           
        }
        return skills;
    }
    
    
    public ArrayList<Skill> getAllSkills(){
        String url = Statics.BASE_URL+"/skillsJSON/" ;
        req.setUrl(url); 
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                skills = parseSkills(new String(req.getResponseData()));
                req.removeResponseListener(this); 
            }
        
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    
        return skills;
    }
    
    
    
    
    
    public Skill parse1Skill(String jsonText) {
        Skill skill = null;
        try {
            //
            //portfolios = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> skillsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) skillsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Skill s = new Skill();
                
                float id = Float.parseFloat(obj.get("idPortfolio").toString());
                s.setId_skill((int) id);
                s.setId_freelancer(190);
                s.setName(obj.get("name").toString());
                s.setLevel(obj.get("level").toString());
                if (obj.get("name") == null) {
                    s.setName("name");
                } else {
                    s.setName(obj.get("name").toString());
                }
                skills.add(s); 
                
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return skill;
    }
    
    public ArrayList<Skill> getSkillById(int sId) {
         //Portfolio portfolioid;
    String url = Statics.BASE_URL + "/skillsJSON/f/"+sId+"/";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            skills = parseSkills(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return skills;
}
    
    
    
    
    
    
    
    
    
    
    
}//end
