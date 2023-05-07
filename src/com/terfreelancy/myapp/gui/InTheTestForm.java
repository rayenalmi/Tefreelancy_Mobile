
package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.BoxLayout;
import com.tefreelancy.services.ServiceQuestion;
import com.terfreelancy.entities.Question;;
import java.util.ArrayList;

public class InTheTestForm extends Form {
    private int id_test;
    private ArrayList<Question> questions;
    private Form current;
    private ButtonGroup[] groups;

    public InTheTestForm(Form previous, int id_test) {
        setTitle("Liste des Questions");
        setLayout(BoxLayout.y());

        questions = ServiceQuestion.getInstance().getAllQuestionsById(id_test);
        groups = new ButtonGroup[questions.size()];

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            Container questionContainer = new Container(BoxLayout.y());
            questionContainer.setUIID("ListQuestionForm_Container");

            SpanLabel questLabel = new SpanLabel("Question: " + question.getQuest());
            RadioButton choice1Label = new RadioButton("Choix 1: " + question.getChoice1());
            RadioButton choice2Label = new RadioButton("Choix 2: " + question.getChoice2());
            RadioButton choice3Label = new RadioButton("Choix 3: " + question.getChoice3());
            SpanLabel responseLabel = new SpanLabel("Réponse: " + question.getResponse());

            ButtonGroup group = new ButtonGroup();
            group.add(choice1Label);
            group.add(choice2Label);
            group.add(choice3Label);
            groups[i] = group;

            questionContainer.add(questLabel);
            questionContainer.add(choice1Label);
            questionContainer.add(choice2Label);
            questionContainer.add(choice3Label);
            questionContainer.add(responseLabel);
            add(questionContainer);
        }

        // Create submit button
        Button submitBtn = new Button("Submit");
        submitBtn.addActionListener(e -> {
            int score = 0; // initialize the score
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                int selectedIndex = groups[i].getSelectedIndex();
                if (selectedIndex >= 0) {
                    RadioButton selectedRadioButton = groups[i].getRadioButton(selectedIndex);
                    String selectedChoice = selectedRadioButton.getText().substring(8);
                    if (selectedChoice.equals(question.getResponse())) {
                        score++; // increment the score if the selected choice is correct
                    }
                }
            }
            // display the score in a popup
            Dialog.show("Score", "Your score is " + score + "/" + questions.size(), "OK", null);
        });
        add(submitBtn);    

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
        
}  

