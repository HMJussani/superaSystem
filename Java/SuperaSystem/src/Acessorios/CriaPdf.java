/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acessorios;

import Bean.OrdServBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;

/**
 *
 * @author RMA
 */
public class CriaPdf {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);

    public void criaPdf(String arquivo, String path) {
        if (path.isEmpty()) {
            path = System.getProperty("user.home") + "\\Documents\\";
        }
        Document arquivoPdf = new Document();
        try {
            PdfWriter.getInstance(arquivoPdf, new FileOutputStream(path + arquivo + ".pdf"));
            arquivoPdf.open();
            arquivoPdf.setPageSize(PageSize.A4);
            metaDatos(arquivoPdf, arquivo);
            tituloPagina(arquivoPdf);
            corpoDoc1(arquivoPdf);
            rodaPe(arquivoPdf);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        arquivoPdf.close();
    }

    private void metaDatos(Document document, String title) throws DocumentException {
        document.addTitle(title);
        document.addSubject("Gerador de Relatórios PDF");
        document.addKeywords("www.hmjussani.com.br");
        document.addCreator("iText");
        document.addAuthor("Henrique Marega Jussani");
    }

    private static void tituloPagina(Document document) throws DocumentException {
        Paragraph paragrafo = new Paragraph();
        linhaBranco(1);
        paragrafo.add(new Paragraph("Supera RMA ", catFont));
        paragrafo.setAlignment(Element.ALIGN_LEFT);
        document.add(paragrafo);
    }

    private static void rodaPe(Document document) throws DocumentException {
        Paragraph paragrafo = new Paragraph();
        paragrafo.add(new Paragraph(
                "Relatório gerado por: " + System.getProperty("user.name") + ", " + new Date(), smallBold));
        paragrafo.setAlignment(Element.ALIGN_LEFT);
        paragrafo.add(new Paragraph(
                "HMJussani Soluções, desesnvolvido em 2023. Revisão 1.0", redFont));
        paragrafo.setAlignment(Element.ALIGN_CENTER);
        document.add(paragrafo);
    }

    private static void corpoDoc(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));
        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));
        // add a list
        // criarLista(subCatPart, );
        Paragraph paragraph = new Paragraph();
        linhaBranco(5);
        subCatPart.add(paragraph);
        // add a table
        //createTable(subCatPart);
        document.add(catPart);
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");
        catPart = new Chapter(new Paragraph(), 1);
        subPara = new Paragraph("Subcategory 1", subFont);
        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));
        document.add(catPart);
    }

    private static void corpoDoc1(Document document) throws DocumentException {
        document.add(new Paragraph(" "));
        criaTabela(document);
    }

    private static void criaTabela(Document document) //, ArrayList<OrdServBean> ordServ
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(4);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("Coluna 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Coluna 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Coluna 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Coluna 4"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4; i++) {
                table.addCell("linha " +(j+1));
            }
        }
        document.add(table);

    }

    private static void criarLista(Section subCatPart, ArrayList<String> lista) {
        List list = new List(true, false, 10);
        for (String itens : lista) {
            list.add(new ListItem(itens));
        }
        subCatPart.add(list);
    }

    private static void linhaBranco(int number) {
        Paragraph paragraph = new Paragraph();
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));

        }
    }

    private void teste(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph("This is right aligned text");
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        // Centered
        paragraph = new Paragraph("This is centered text");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        // Left
        paragraph = new Paragraph("This is left aligned text");
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        // Left with indentation
        paragraph = new Paragraph(
                "This is left aligned text with indentation");
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setIndentationLeft(50);
        document.add(paragraph);
        document.newPage();
    }
}
