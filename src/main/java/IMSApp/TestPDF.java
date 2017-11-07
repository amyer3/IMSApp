package IMSApp;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.sql.ResultSet;

public class TestPDF{

    public static void main(String[] args) {
        createPDF(DBHandler.exportEverything());
    }

    static void createPDF(ResultSet rs) {
        String path = System.getProperty("user.home") + "/Desktop/Inv_on_.pdf";
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        float margin = 15;
        float yStart = page.getMediaBox().getHeight() - (2* margin);
        float tableWidth = page.getMediaBox().getWidth() - (2*margin);
        float bottomMargin = 20;

        try {
            PDPageContentStream cs = new PDPageContentStream(doc, page);
            BaseTable table = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, doc, page, true,
                    true);
            Row<PDPage> headerRow = table.createRow(15f);
            headerRow.createCell(10, "Product ID").setAlign(HorizontalAlignment.CENTER);
            headerRow.createCell(50, "Description").setAlign(HorizontalAlignment.CENTER);
            headerRow.createCell(10, "Cost").setAlign(HorizontalAlignment.CENTER);
            headerRow.createCell(10, "Date Made").setAlign(HorizontalAlignment.CENTER);
            headerRow.createCell(10, "Date Sold").setAlign(HorizontalAlignment.CENTER);
            headerRow.createCell(10, "Sale Price").setAlign(HorizontalAlignment.CENTER);
            table.addHeaderRow(headerRow);

            while (rs.next()) {
                String id = rs.getString("ID");
                String desc = rs.getString("Desc");
                String cogs = Ops.priceFormatter(rs.getString("COGS"));
                String DM = Ops.scrubDate(rs.getDate("Date_Made"));
                String SD = Ops.scrubDate(rs.getDate("Sale_Date"));
                String SP = Ops.priceFormatter(rs.getString("Sale_Price"));
                Row<PDPage> row = table.createRow(10f);
                row.createCell(10, id);
                row.createCell(50, desc);
                row.createCell(10, cogs).setAlign(HorizontalAlignment.CENTER);
                row.createCell(10, DM).setAlign(HorizontalAlignment.CENTER);
                row.createCell(10, SD).setAlign(HorizontalAlignment.CENTER);
                row.createCell(10, SP).setAlign(HorizontalAlignment.CENTER);
            }
            doc.addPage(page);
            table.draw();
            cs.close();
            doc.save(path);
            doc.close();
            Runtime.getRuntime().exec("open " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
