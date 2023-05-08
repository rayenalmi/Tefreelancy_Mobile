/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tefreelancy.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.tefreelancy.utils.GroupsUtils;
//import com.terfreelancy.entities.Task;
import com.terfreelancy.entities.Task;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {
 public ArrayList<Task> Tasks;

    public static ServiceTask instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
        req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

//   public boolean addTask(Task t, int workspaceId) {
//
//    String name = t.getTitle();
//    String description = t.getDescription();
//    Date deadline = t.getDeadline();
//    String url = Statics.BASE_URL + "/task/addTaskJSON/new/" + workspaceId + "?title=" + name + "&description=" + description + "&deadline=" + deadline;
//
//    req.setUrl(url);
//    req.setPost(true);
//    System.out.println(t);
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            resultOK = req.getResponseCode() == 200;
//            req.removeResponseListener(this);
//        }
//    });
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    return resultOK;
//}
   public boolean addTask(Task t, int workspaceId) {
    String url = GroupsUtils.BASE_URL + "/task/addTaskJSON/new/" + workspaceId + "?title=" + t.getTitle() + "&description=" + t.getDescription();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String deadlineStr = sdf.format(t.getDeadline());
    url += "&deadline=" + deadlineStr;
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

 public boolean updateTask(Task t, int workspaceId) {
    String url = GroupsUtils.BASE_URL + "/task/updateTaskJSON/" + t.getId() + "/" + workspaceId + "?title=" + t.getTitle() + "&description=" + t.getDescription() + "&deadline=" + new SimpleDateFormat("yyyy-MM-dd").format(t.getDeadline()) + "&completed=" + (t.isCompleted() ? "1" : "0");
    req.setUrl(url);
    req.setPost(false); // Set the request method to POST before adding arguments
//    req.addArgument("title", t.getTitle());
//    req.addArgument("description", t.getDescription());
//    req.addArgument("deadline", new SimpleDateFormat("yyyy-MM-dd").format(t.getDeadline()));
//    req.addArgument("completed", t.isCompleted() ? "1" : "0");

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


 public ArrayList<Task> parseTasks(String jsonText) {
    try {
        Tasks = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) categListJson.get("root");

        for (Map<String, Object> obj : list) {
            try {
                Task t = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTitle(obj.get("title").toString());
                t.setDescription(obj.get("description").toString());
                String deadlineStr = obj.get("deadline").toString();
                t.setCompleted(Boolean.parseBoolean(obj.get("completed").toString()));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date deadline = sdf.parse(deadlineStr);
                t.setDeadline(deadline);
                Tasks.add(t);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    } catch (IOException ex) {

    }
    return Tasks;
}


   
   
   public boolean deleteTask(int id, int workspaceId) {
        String url = GroupsUtils.BASE_URL + "/task/deleteTaskJSON/" + id + "/" + workspaceId;
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


    public ArrayList<Task> getAllTasks(int workspaceId) {
        String url = GroupsUtils.BASE_URL + "/task/AllTasksJson/"+workspaceId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Tasks;
    }
}