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
//import com.mycompany.myapp.entities.Freelancer;
import com.terfreelancy.entities.Workspace;
import com.terfreelancy.entities.Freelancer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServiceWorkspace {
 public ArrayList<Workspace> Workspaces;
 public ArrayList<Freelancer> Freelancers;
public Workspace workspace;
public Freelancer freelancer;

    public static ServiceWorkspace instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceWorkspace() {
        req = new ConnectionRequest();
    }

    public static ServiceWorkspace getInstance() {
        if (instance == null) {
            instance = new ServiceWorkspace();
        }
        return instance;
    }

   public boolean addWorkspace(Workspace t,int idUsr) {
    String url = GroupsUtils.BASE_URL + "/workspace/addWsJSON/new/"+idUsr + "?name=" + t.getName()+ "&description=" + t.getDescription();
  
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
  
   

   public boolean updateWorkspace(Workspace t) {
    String url = GroupsUtils.BASE_URL + "/workspace/" + t.getId() + "/editJson?name=" + t.getName()+ "&description=" + t.getDescription();
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
   
   public boolean deleteWorkspace(int id) {
        String url = GroupsUtils.BASE_URL + "/workspace/deleteWsJSON/" + id ;
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
   
    public ArrayList<Workspace> parseWorkspaces(String jsonText){
        try {
            Workspaces=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)categListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               Workspace t = new Workspace();
              
                float id = Float.parseFloat(obj.get("id").toString());
                t.setName(obj.get("name").toString());
                t.setId((int)id);
                t.setDescription(obj.get("description").toString());
                
           
              Workspaces.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
          return Workspaces;
    }

    
    public ArrayList<Freelancer> parseFreelancers(String jsonText){
        try {
            Freelancers=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)categListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               Freelancer t = new Freelancer();
              
                float id = Float.parseFloat(obj.get("idUser").toString());
                t.setNom(obj.get("lastName").toString());
                t.setId((int)id);
                t.setPrenom(obj.get("firstName").toString());
                
           
              Freelancers.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
          return Freelancers;
    }
   public Freelancer parseFreelancer(String jsonText) {
    try {
        freelancer = new Freelancer();
        JSONParser jsonParser = new JSONParser();
        Map<String, Object> wsJson = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        // System.out.println("wsJson: " + wsJson); // debugging statement
        Map<String, Object> wsMap = (Map<String, Object>) wsJson.get("root");
        //System.out.println("wsMap: " + wsMap); // debugging statement
        float id = Float.parseFloat(wsJson.get("idUser").toString());
        double phone = Double.parseDouble(wsJson.get("phone").toString());
        freelancer.setId((int) id);
        freelancer.setNom(wsJson.get("lastName").toString());
        freelancer.setPrenom(wsJson.get("firstName").toString());
        freelancer.setTel(Double.valueOf(phone).intValue());

    } catch (IOException ex) {
        System.err.println("Error parsing JSON data: " + ex.getMessage());
        freelancer = null;
    } catch (NumberFormatException ex) {
        System.err.println("Error parsing number: " + ex.getMessage());
        freelancer = null;
    }
    return freelancer;
}

    
    
    
    
    
    
    
    
    
    
    public ArrayList<Workspace> getAllWorkspaces(int userId) {
        String url = GroupsUtils.BASE_URL + "/workspace/AllWsForFreeJson/"+userId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Workspaces = parseWorkspaces(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Workspaces;
    }
    
    public ArrayList<Freelancer> getAllFreelancers(int workspaceId) {
        String url = GroupsUtils.BASE_URL + "/workspace/AllFreeForWsJson/"+workspaceId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Freelancers = parseFreelancers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Freelancers;
    }
    
    
 public Workspace getWorkspaceById(int workspaceId) {
    String url = GroupsUtils.BASE_URL + "/workspace/getWsById/" + workspaceId;
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            workspace = parseWorkspace(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return workspace;
}
  public Freelancer getFreelancerByEmail(String email) {
    String url = GroupsUtils.BASE_URL + "/workspace/getWsByEmail?email="+email;
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            freelancer = parseFreelancer(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return freelancer;
}
 
  public Boolean addFreelancerByEmail(int workspaceId,String email) {
    String url = GroupsUtils.BASE_URL + "/workspace/addFreeToWs/" + workspaceId+"?email="+email;
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

  
 
public Workspace parseWorkspace(String jsonText) {
    try {
        workspace = new Workspace();
        JSONParser jsonParser = new JSONParser();
        Map<String, Object> wsJson = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
       // System.out.println("wsJson: " + wsJson); // debugging statement
        Map<String, Object> wsMap = (Map<String, Object>) wsJson.get("root");
        //System.out.println("wsMap: " + wsMap); // debugging statement
        float id = Float.parseFloat(wsJson.get("id").toString());
        workspace.setId((int) id);
        workspace.setName(wsJson.get("name").toString());
        workspace.setDescription(wsJson.get("description").toString());

    } catch (IOException ex) {
        System.err.println("Error parsing JSON data: " + ex.getMessage());
        workspace = null;
    } catch (NumberFormatException ex) {
        System.err.println("Error parsing number: " + ex.getMessage());
        workspace = null;
    }
    return workspace;
}




}