
package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.BoxLayout;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.tefreelancy.services.ServiceQuestion;
import com.terfreelancy.entities.Question;import java.io.OutputStream;
import java.util.ArrayList;

public class InTheTestForm extends Form {
    private int id_test;
    private ArrayList<Question> questions;
    private Form current;
    private ButtonGroup[] groups;

    public InTheTestForm(Form previous, int id_test) {
        questions = new ArrayList<>();
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
            //SpanLabel responseLabel = new SpanLabel("Réponse: " + question.getResponse());

            ButtonGroup group = new ButtonGroup();
            group.add(choice1Label);
            group.add(choice2Label);
            group.add(choice3Label);
            groups[i] = group;

            questionContainer.add(questLabel);
            questionContainer.add(choice1Label);
            questionContainer.add(choice2Label);
            questionContainer.add(choice3Label);
            //questionContainer.add(responseLabel);
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
        Button pdfBtn = new Button("Download PDF");
        pdfBtn.addActionListener(e -> {
            try {
                downloadPDF();
            } catch (Exception ex) {
                Dialog.show("ERROR", "PDF download failed", new Command("OK"));
            }
        });
        
        add(pdfBtn); // Add the button to the form

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
         private void downloadPDF() throws Exception {
    // Set the file path
    String filePath = FileSystemStorage.getInstance().getAppHomePath() + "tasks.pdf";
    // Create an output stream to write the PDF file
    OutputStream outputStream = FileSystemStorage.getInstance().openOutputStream(filePath);

    // Create a new document
    Document document = new Document();
    // Set the PDF writer to write to the output stream
    PdfWriter.getInstance(document, outputStream);

    // Add metadata to the document
    document.addAuthor("Your Name");
    document.addCreator("Your App");
    document.addSubject("Task Questions");
    document.addTitle("Task Questions");

    // Set the document margins
    document.setMargins(36, 36, 36, 36);

    // Open the document
    document.open();

    // Add the questions to the PDF document
    for (Question question : questions) {
        // Create a paragraph for the question
        Paragraph nameParagraph = new Paragraph(question.getQuest());
        // Set the font size and style for the question
        nameParagraph.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.NORMAL));
        // Add the question paragraph to the document
        document.add(nameParagraph);

        // Create a list for the choices
        List list = new List(List.ORDERED);
        // Set the font size and style for the list items
        Font listItemFont = FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL);
        // Add each choice to the list
        list.add(new ListItem(question.getChoice1(), listItemFont));
        list.add(new ListItem(question.getChoice2(), listItemFont));
        list.add(new ListItem(question.getChoice3(), listItemFont));
        // Add the list to the document
        document.add(list);

        // Create a paragraph for the response
        Paragraph responseParagraph = new Paragraph("Response: " + question.getResponse());
        // Set the font size and style for the response
        responseParagraph.setFont(FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL));
        // Add the response paragraph to the document
        document.add(responseParagraph);

        // Add some space between the questions
        document.add(new Paragraph(" "));
    }

    // Close the document
    document.close();

    // Flush and close the output stream
    outputStream.flush();
    outputStream.close();

    if (Dialog.show("PDF Downloaded", "PDF file was saved to " + filePath + ". Do you want to open it?", "Open", "Cancel")) {
        // Open the PDF file
        Display.getInstance().execute(filePath);
    }
}
}  

