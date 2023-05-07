/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.terfreelancy.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.terfreelancy.entities.Portfolio;
import com.terfreelancy.entities.Skill;
import com.tefreelancy.services.ServicePortfolio;
import com.tefreelancy.services.ServiceSkill;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListSkillForm extends Form {
    public ArrayList<Skill> skills;
    Form current;
    public ListSkillForm(Form previous) {
        setTitle("Liste des skills"); 
        
        skills = ServiceSkill.getInstance().getSkillById(190); 
        for (Skill obj : skills) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
           
            SpanLabel a = new SpanLabel("Name : " + obj.getName());
            SpanLabel sp3 = new SpanLabel("Level : " + obj.getLevel());
            SpanLabel sp4 = new SpanLabel("------------------------------------------------ " );
         
            
            
            
            Button btnUpdatePortfolio = new Button("Update Skill");
        
        btnUpdatePortfolio.addActionListener(e-> new UpdateSkillForm(this,obj).show());       
            
            
            Button delBtn = new Button("Delete");
            delBtn.addActionListener(e -> {
                if (ServiceSkill.getInstance().deleteSkill(obj.getId_skill())) {
                    Dialog.show("Success", "Skill Deleted successfully", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });
                    /*
                     
                      
                     
                     */
                    
                    
                    
                    
                    
                    
                    
                            Button pdfBtn = new Button("Download PDF");
        pdfBtn.addActionListener(e -> {
            try {
                downloadPDF();
            } catch (Exception ex) {
                Dialog.show("ERROR", "PDF download failed", new Command("OK"));
            }
        });
        
        
        add(pdfBtn); // Add the button to the form


            addAll(spTitle,a,sp3,sp4,delBtn,btnUpdatePortfolio);
        }
        

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
    private void downloadPDF() throws Exception {
    String filePath = FileSystemStorage.getInstance().getAppHomePath() + "skills.pdf";
    OutputStream outputStream = FileSystemStorage.getInstance().openOutputStream(filePath);

    Document document = new Document();
    PdfWriter.getInstance(document, outputStream);

    document.open();
    PdfPTable table = new PdfPTable(2);
    table.addCell("Skill Name");
    table.addCell("Skill Level");




   for (Skill obj : skills) {
    table.addCell(obj.getName());
    table.addCell(obj.getLevel());

    
    
 



}




    document.add(table);
    document.close();

    outputStream.flush();
    outputStream.close();

    if (Dialog.show("PDF Downloaded", "PDF file was saved to " + filePath + ". Do you want to open it?", "Open", "Cancel")) {
        Display.getInstance().execute(filePath);
    }
  }
    
    
    
    
    
    
    
    
    
    
}//end
