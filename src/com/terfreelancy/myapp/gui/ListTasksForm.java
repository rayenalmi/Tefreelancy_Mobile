package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.terfreelancy.entities.Task;
import com.tefreelancy.services.ServiceTask;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;


public class ListTasksForm extends Form {

    private ArrayList<Task> tasks;
    private TextField searchField;

    public ListTasksForm(Form previous, int workspaceId,String userRole) {
        setTitle("Liste des Tasks");
        setLayout(BoxLayout.y());

        searchField = new TextField("", "Search");
        add(searchField);
        searchField.addDataChangeListener((i1, i2) -> {
            refreshForm(workspaceId,userRole);
        });

        tasks = ServiceTask.getInstance().getAllTasks(workspaceId);
        refreshForm(workspaceId,userRole);
       
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

    private void refreshForm(int workspaceId,String userRole) {
        removeAll();
        add(searchField);

        for (Task obj : tasks) {
            if (obj.getTitle().toLowerCase().contains(searchField.getText().toLowerCase())) {
                Button spTitle = new Button();
                SpanLabel a = new SpanLabel("Title : " + obj.getTitle());
                SpanLabel sp3 = new SpanLabel("Description : " + obj.getDescription());
                SpanLabel sp5 = new SpanLabel("Deadline : " + obj.getDeadline());
                
               
                
             
                addAll( a, sp3, sp5);
                // String userRole="Freelancer";
                if("Recruter".equals(userRole)){
                Button editBtn = new Button("Edit");
                editBtn.addActionListener(e -> {
                    new EditTaskForm(this, obj,workspaceId).show();
                });
                Button delBtn = new Button("Delete");
                delBtn.addActionListener(e -> {
                    if (ServiceTask.getInstance().deleteTask(obj.getId(), workspaceId)) {
                        Dialog.show("Success", "Task Deleted successfully", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                });
                
                addAll(editBtn,delBtn);
                 SpanLabel sp4 = new SpanLabel("--------------------------------------------------------");
                    add(sp4);
                }
            }
        }

        revalidate();
    }
    
  private void downloadPDF() throws Exception {
    String filePath = FileSystemStorage.getInstance().getAppHomePath() + "tasks.pdf";
    OutputStream outputStream = FileSystemStorage.getInstance().openOutputStream(filePath);

    Document document = new Document();
    PdfWriter.getInstance(document, outputStream);

    document.open();
    PdfPTable table = new PdfPTable(5);
    table.addCell("Title");
    table.addCell("Description");
    table.addCell("Deadline");
    table.addCell("Status");
    table.addCell("Remains Time");

   for (Task task : tasks) {
    table.addCell(task.getTitle());
    table.addCell(task.getDescription());

    // calculate remaining time
    long remainingDays = (task.getDeadline().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24);
    String remainingTime = remainingDays < 0 ? "Expired" : remainingDays + " days";

    table.addCell(task.getDeadline().toString());
    table.addCell(remainingTime);

    if(task.isCompleted()){
        table.addCell("Completed");
    } else {
        table.addCell("In Progress");
    }
}




    document.add(table);
    document.close();

    outputStream.flush();
    outputStream.close();

    if (Dialog.show("PDF Downloaded", "PDF file was saved to " + filePath + ". Do you want to open it?", "Open", "Cancel")) {
        Display.getInstance().execute(filePath);
    }
  }

    

}
