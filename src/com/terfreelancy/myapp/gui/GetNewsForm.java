/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.tefreelancy.services.ServiceGroup;
import com.terfreelancy.entities.Article;
import java.util.List;

/**
 *
 * @author ameni
 */



public class GetNewsForm extends Form {
   private Resources theme;

    public GetNewsForm(Form previous, String groupName, Resources theme) {
        this.theme = theme;
        setTitle("News for " + groupName);
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        try {
            List<Article> articles = ServiceGroup.getInstance().getNews(groupName);
            for (Article article : articles) {
                Container cardContainer = createCard(article.getTitle(), article.getDescription());
                add(cardContainer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getToolbar().setBackCommand("", e -> previous.showBack());
    }

    private Container createCard(String title, String description) {
        Container cardContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cardContainer.setUIID("CardContainer"); // Set custom UIID for styling

        Label titleLabel = new Label(title);
        Label descriptionLabel = new Label(description);
        titleLabel.setUIID("CardTitle"); // Set custom UIID for styling
        descriptionLabel.setUIID("CardDescription"); // Set custom UIID for styling

        cardContainer.addAll(titleLabel, descriptionLabel);
        return cardContainer;
    }

    public void setNewsData(String groupName) {
        try {
            List<Article> articles = ServiceGroup.getInstance().getNews(groupName);
            for (Article article : articles) {
                Container cardContainer = createCard(article.getTitle(), article.getDescription());
                add(cardContainer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initComponent() {
        super.initComponent();
        Style cardContainerStyle = getUIManager().getComponentStyle("CardContainer");
        Style cardTitleStyle = getUIManager().getComponentStyle("CardTitle");
        Style cardDescriptionStyle = getUIManager().getComponentStyle("CardDescription");

        cardContainerStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        cardContainerStyle.setMargin(Component.BOTTOM, 10);

        cardTitleStyle.setFont(theme.getFont("title"));
        cardDescriptionStyle.setFont(theme.getFont("description"));

        cardTitleStyle.setFgColor(0x333333); // Custom color
        cardDescriptionStyle.setFgColor(0x666666); // Custom color

        for (Component cmp : getContentPane()) {
            if (cmp instanceof Container) {
                Container cardContainer = (Container) cmp;
                for (Component cardCmp : cardContainer) {
                    if (cardCmp instanceof Label) {
                        Label label = (Label) cardCmp;
                        if (label.getUIID().equals("CardDescription")) {
                            label.setEndsWith3Points(true);
                        }
                    }
                }
            }
        }
    }
}








