/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ROG
 */
public class OffersForm extends Form{
    public OffersForm (Resources theme)
    {
        setTitle("Offers");
        //this.getToolbar().addCommandToLeftSideMenu("Home", null, ev -> f.show());
        this.getToolbar().addCommandToLeftSideMenu("Offers", null, ev -> new OffersForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Candidacy", null, ev -> new CandidacyForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Foramtion", null, ev -> new ForamtionForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Favoris", null, ev -> new FavorisForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Badges", null, ev -> new BadgeForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Groups", null, ev -> new GroupsForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Workspace", null, ev -> new WorkspaceForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Profil", null, ev -> new ProfilForm(theme).show());
        this.getToolbar().addCommandToLeftSideMenu("Logout", null, ev -> new LoginForm(theme).show());
    }
}
