/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Savers.GUI.Back;

/**
 *
 * @author farouk
 */
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;
import java.util.List;
import com.Savers.Entities.foundation;
import com.Savers.Entities.event;


public class pdf {



private static String FILE = "/Users/MrStealYourMom/Desktop/Beta/pdf/pdf";


 private static Font basicFont = new Font(Font.FontFamily.TIMES_ROMAN, 26,
            Font.BOLD);

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    static void addMetaData(Document document) {
        document.addTitle("Foundation ");
        document.addSubject("Foundation");
        document.addKeywords("Foundation, PDF");
        document.addAuthor("Savers");
        document.addCreator("Savers");



    }
    static void addMetaData2(Document document) {
        document.addTitle("Event ");
        document.addSubject("Event");
        document.addKeywords("Event, PDF");
        document.addAuthor("Savers");
        document.addCreator("Savers");



    }
   static void addTitlePage(Document document, List<foundation> list)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        Paragraph title = new Paragraph("Savers", basicFont);
        title.setAlignment(Element.ALIGN_CENTER);
        preface.add(title);
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("List Foundations", catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Date :" + new Date(), redFont));

        Paragraph Info = new Paragraph("Programmation Stm32", smallBold);
        Info.setAlignment(Element.ALIGN_CENTER);
        preface.add(Info);

        addEmptyLine(preface, 3);

        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Fondation"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Categorie"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        
        table.setHeaderRows(1);

        list.stream().forEach((N) -> {
            System.out.println(N.toString());
            table.addCell(String.valueOf(N.getNoun()));
            table.addCell(String.valueOf(N.getTypeCategorie()));
            table.addCell(N.getUpdated_at().toString());
        });

        preface.add(table);

        document.add(preface);

    }
   static void addTitlePage2(Document document, List<event> list)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        Paragraph title = new Paragraph("Savers", basicFont);
        title.setAlignment(Element.ALIGN_CENTER);
        preface.add(title);
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("List Events", catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Date :" + new Date(), redFont));

        Paragraph Info = new Paragraph("Programmation Stm32", smallBold);
        Info.setAlignment(Element.ALIGN_CENTER);
        preface.add(Info);

        addEmptyLine(preface, 3);

        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Event"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Categorie"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Foundation"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        
        table.setHeaderRows(1);

        list.stream().forEach((N) -> {
            System.out.println(N.toString());
            table.addCell(String.valueOf(N.getNoun()));
            table.addCell(String.valueOf(N.getTypeCategorie()));
            table.addCell(N.getNounFoundation());
        });

        preface.add(table);

        document.add(preface);

    }
   
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
}
