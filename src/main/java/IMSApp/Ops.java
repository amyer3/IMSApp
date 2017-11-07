package IMSApp;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

class Ops {

    public static void main(String[] args) {

    }

    static void firstRun() {
        File statusFile = new File("src/main/statusFileFolder/statusFile.txt");
        if (!statusFile.exists()) {
            try {
                statusFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DBHandler.createFullDB();
        }
        backupExcel();
    }

    static String scrubDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } else {
            return null;
        }
    }

    static String[] updateArrayFactory(String[] oldValues, String[] newValues) {
        for (int i = 1; i < newValues.length; i++) {
            if (newValues[i] == null || newValues[i].equals("")) {
                newValues[i] = oldValues[i];
            }
        }
        return newValues;
    }

    static void createExcel(ResultSet rs) {
        String path = System.getProperty("user.home") + "/Desktop/Inv_" + Ops.todayDate() + ".xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Inventory");
        // HEADERS
        HSSFRow row = spreadsheet.createRow(0);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("Description");
        cell = row.createCell(2);
        cell.setCellValue("COGS");
        cell = row.createCell(3);
        cell.setCellValue("Date Made");
        cell = row.createCell(4);
        cell.setCellValue("Sale Date");
        cell = row.createCell(5);
        cell.setCellValue("Sale Price");
        int i = 1;
        // CELL CONTENT
        try {
            while (rs.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(rs.getString("ID"));
                cell = row.createCell(1);
                cell.setCellValue(rs.getString("Desc"));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString("COGS"));
                cell = row.createCell(3);
                cell.setCellValue(Ops.scrubDate(rs.getDate("Date_Made")));
                cell = row.createCell(4);
                cell.setCellValue(Ops.scrubDate(rs.getDate("Sale_Date")));
                cell = row.createCell(5);
                cell.setCellValue(rs.getString("Sale_Price"));
                i++;
            }
            for (int j = 0; j <5 ; j++) {
                spreadsheet.autoSizeColumn(j);
            }
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        openFile(path);
    }

    static void createPDF(ResultSet rs) {
        String path = System.getProperty("user.home") + "/Desktop/Inv_on_"+todayDate()+".pdf";
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

    private static String todayDate() {
        return scrubDate(new Date());
    }

    static String datePicker(String input) {
        if (input.contains("made")) {
            return "Date_Made";
        } else {
            return "Sale_Date";
        }
    }

    static String priceFormatter(String input){
        if(input !=null){
            if(!input.contains(".")){
                return input.concat(".00");
            } else {
                return input;
            }
        } else {return null;}
    }

    private static void openFile(String location){
        try {
            Runtime.getRuntime().exec("open "+location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void backupExcel(){
        ResultSet rs = DBHandler.exportEverything();
        String path = "src/main/Backup_Folder/Inventory_Backup.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Inventory");
        // HEADERS ~/IMSApp/src/main/Backup Folder
        HSSFRow row = spreadsheet.createRow(0);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("Description");
        cell = row.createCell(2);
        cell.setCellValue("COGS");
        cell = row.createCell(3);
        cell.setCellValue("Date Made");
        cell = row.createCell(4);
        cell.setCellValue("Sale Date");
        cell = row.createCell(5);
        cell.setCellValue("Sale Price");
        int i = 1;
        // CELL CONTENT
        try {
            while (rs.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(rs.getString("ID"));
                cell = row.createCell(1);
                cell.setCellValue(rs.getString("Desc"));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString("COGS"));
                cell = row.createCell(3);
                cell.setCellValue(Ops.scrubDate(rs.getDate("Date_Made")));
                cell = row.createCell(4);
                cell.setCellValue(Ops.scrubDate(rs.getDate("Sale_Date")));
                cell = row.createCell(5);
                cell.setCellValue(rs.getString("Sale_Price"));
                i++;
            }
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
