/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acessorios;

import Bean.EquipOSBean;
import Bean.OrdServBean;
import DAO.EquipOsDAO;
import DAO.OrdServDAO;
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
public class CriaOsPdf {

    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.RED);
    private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    OrdServDAO ordemServ = new OrdServDAO();
    EquipOsDAO equipDAO = new EquipOsDAO();
    Acessorios arquivo = new Acessorios();

    public void criaPdf(String osId, String path) {
        if (path.isEmpty()) {
            path = System.getProperty("user.home") + "\\Documents\\";
        }
        Document arquivoPdf = new Document();
        try {
            PdfWriter.getInstance(arquivoPdf, new FileOutputStream(path + osId + ".pdf"));
            arquivoPdf.open();
            arquivoPdf.setPageSize(PageSize.A4);
            metaDatos(arquivoPdf, osId);
            tituloPagina(arquivoPdf);
            listaOs(arquivoPdf, osId);
            listaEquip(arquivoPdf, osId);
            rodaPe(arquivoPdf);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        arquivoPdf.close();
    }

    public void relatorioOs(String nomeArquivo, String path, String opcao) {
        if (path.isEmpty()) {
            path = System.getProperty("user.home") + "\\Documents\\";
        }

        Document arquivoPdf = new Document();
        try {
            PdfWriter.getInstance(arquivoPdf, new FileOutputStream(path + nomeArquivo + ".pdf"));
            arquivoPdf.open();
            arquivoPdf.setPageSize(PageSize.A4);
            metaDatos(arquivoPdf, nomeArquivo);
            tituloPagina(arquivoPdf);
            listaOs(arquivoPdf);            
            rodaPe(arquivoPdf);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            arquivoPdf.close();
        }

    }

    private void metaDatos(Document document, String title) throws DocumentException {
        document.addTitle(title);
        document.addSubject("Gerador de Relatórios PDF");
        document.addKeywords("www.hmjussani.com.br");
        document.addCreator("iText");
        document.addAuthor("Henrique Marega Jussani");
    }

    private void tituloPagina(Document document) throws DocumentException {
        Paragraph paragrafo = new Paragraph();
        linhaBranco(1);
        paragrafo.add(new Paragraph("Supera RMA ", catFont));
        paragrafo.setAlignment(Element.ALIGN_LEFT);
        document.add(paragrafo);
    }

    private void rodaPe(Document document) throws DocumentException {
        Paragraph paragrafo = new Paragraph();
        paragrafo.add(new Paragraph(
                "Relatório gerado por: " + System.getProperty("user.name") + ", " + new Date(), smallBold));
        paragrafo.setAlignment(Element.ALIGN_LEFT);
        paragrafo.add(new Paragraph(
                "HMJussani Soluções, desesnvolvido em 2023. Revisão 1.0", redFont));
        paragrafo.setAlignment(Element.ALIGN_CENTER);
        document.add(paragrafo);
    }

    private void corpoDoc(Document document) throws DocumentException {
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

    private void listaOs(Document document, String osId) throws DocumentException {
        document.add(new Paragraph(" "));
        ArrayList<OrdServBean> ordServ = ordemServ.pesquisarOsbyIdOs(osId);
        if (!ordServ.isEmpty()) {
            osTabela(document, ordServ);
        }
    }

    private void listaOs(Document document) throws DocumentException {
        document.add(new Paragraph(" "));
        ArrayList<OrdServBean> ordServ = ordemServ.pesquisarOs();
        if (!ordServ.isEmpty()) {
            osTabela(document, ordServ);
        }
    }

    private void listaEquip(Document document, String osId) throws DocumentException{
        document.add(new Paragraph(" "));
        ArrayList<EquipOSBean> equip = equipDAO.pesquisarProdutoBy("idOrdServ", osId);
        if (!equip.isEmpty()) {
            equipTabela(document, equip);
        }
    }
    
    private void osTabela(Document document, ArrayList<OrdServBean> ordServ)
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(4);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("Ordem de Serviço"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Cliente"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Data de Abertura"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Estado"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (int j = 0; j < ordServ.size(); j++) {
            String status = "Aberta";
            table.addCell(ordServ.get(j).getIdOrdServ());
            table.addCell(ordServ.get(j).getNomeCli());
            table.addCell(ordServ.get(j).getDataAbertura().toString());
            if (!ordServ.get(j).getAberta()) {
                status = "Fechada";
            }
            table.addCell(status);

        }
        document.add(table);

    }

    private void equipTabela(Document document, ArrayList<EquipOSBean> equipOs)
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(4);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("Patrimônio"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Número de Serie"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Modelo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Estado"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (int j = 0; j < equipOs.size(); j++) {
            String status = "Analizando";
            table.addCell(equipOs.get(j).getPatEquip());
            table.addCell(equipOs.get(j).getNserie());
            table.addCell(equipOs.get(j).getModel());
            if(equipOs.get(j).getAnalizado())status="Resolvido";
            table.addCell(status);

        }
        document.add(table);

    }

    private void criarLista(Section subCatPart, ArrayList<String> lista) {
        List list = new List(true, false, 10);
        for (String itens : lista) {
            list.add(new ListItem(itens));
        }
        subCatPart.add(list);
    }

    private void linhaBranco(int number) {
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
