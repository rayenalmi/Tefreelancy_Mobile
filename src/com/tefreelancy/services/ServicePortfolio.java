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
import com.terfreelancy.entities.Portfolio;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;


/**
 *
 * @author HP
 */
public class ServicePortfolio {
    
    
    public ArrayList<Portfolio> portfolios;
    public Portfolio portfolio;
    public Portfolio portfolioid;

    public static ServicePortfolio instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePortfolio() {
        req = new ConnectionRequest();
    }

    public static ServicePortfolio getInstance() {
        if (instance == null) {
            instance = new ServicePortfolio();
        }
        return instance;
    }

    public boolean addPortfolio(Portfolio p) {

        String intro = p.getIntro();
        String about =  p.getAbout();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        //String url = Statics.BASE_URL + "create/" + status + "/" + name;
String url = GroupsUtils.BASE_URL+"/portfolioJSON/new?intro="+ intro +"&about="+ about ;
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
    
    public boolean updatePortfolio(Portfolio p) {
    //String url = Statics.BASE_URL + "/workspace/" + t.getId() + "/editJson?name=" + t.getName()+ "&description=" + t.getDescription();
    String url = GroupsUtils.BASE_URL + "/portfolioJSON/"+p.getId_portfolio()+"/edit?intro="+ p.getIntro() +"&about="+ p.getAbout(); 
 System.out.println(p.getId_portfolio());
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
  
   
   public boolean deletePortfolio(int id) {
        String url = GroupsUtils.BASE_URL + "/portfolioJSON/"+id+"/delete";
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public ArrayList<Portfolio> parsePortfolio(String jsonText) {
        try {
            portfolios = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> PortfoliosListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) PortfoliosListJson.get("root");
            for (Map<String, Object> obj : list) {
                Portfolio p = new Portfolio();
                
                float id = Float.parseFloat(obj.get("idPortfolio").toString());
                p.setId_portfolio((int) id);
                p.setId_freelancer(190);
                p.setIntro(obj.get("intro").toString());
                p.setAbout(obj.get("about").toString());
                if (obj.get("intro") == null) {
                    p.setIntro("intro");
                } else {
                    p.setIntro(obj.get("intro").toString());
                }
                portfolios.add(p); 
                
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return portfolios;
    }

    public ArrayList<Portfolio> getAllPortfolios() {
        String url = GroupsUtils.BASE_URL + "/portfolioJSON/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                portfolios = parsePortfolio(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return portfolios;
    }
    
    
    public Portfolio parse1Portfolio(String jsonText) {
        Portfolio portfolio = null;
        try {
            //
            //portfolios = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> PortfoliosListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) PortfoliosListJson.get("root");
            for (Map<String, Object> obj : list) {
                Portfolio p = new Portfolio();
                
                float id = Float.parseFloat(obj.get("idPortfolio").toString());
                p.setId_portfolio((int) id);
                p.setId_freelancer(190);
                p.setIntro(obj.get("intro").toString());
                p.setAbout(obj.get("about").toString());
                if (obj.get("intro") == null) {
                    p.setIntro("intro");
                } else {
                    p.setIntro(obj.get("intro").toString());
                }
                portfolios.add(p); 
                
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return portfolio;
    }
    
    public ArrayList<Portfolio> getPortfolioById(int pId) {
         //Portfolio portfolioid;
    String url = GroupsUtils.BASE_URL + "/portfolioJSON/f/"+pId+"/";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            portfolios = parsePortfolio(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return portfolios;
}
    
    
    
    
}//end
