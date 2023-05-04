/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tefreelancy.utils;

import com.terfreelancy.enties.Freelancer;

/**
 *
 * @author ROG
 */
public class SessionManager {

    private static SessionManager instance;
    private Freelancer currentUser;

    private SessionManager() {
        // Private constructor to prevent instantiation outside of this class
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Freelancer getCurrentUser() {
        System.out.println( "---GET---" + currentUser);
        return currentUser;
    }

    public void setCurrentUser(Freelancer currentUser) {
        System.out.println( "---SET---" + currentUser);
        this.currentUser = currentUser;
    }

    public void endSession() {
        currentUser = null;
    }

}
