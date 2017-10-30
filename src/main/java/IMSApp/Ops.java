package IMSApp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Ops {

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
    }

    public static Boolean blankChecker(String[] values) {
        Boolean checked = true;
        for (String value : values) {
            if (value == null) {
                checked = false;
                break;
            }
        }
        return checked;
    }

    static String scrubDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } else {
            return null;
        }
    }

    static String[] updateArrayFactory(String[] oldValues, String[] newValues) {
        for (int i = 0; i < newValues.length; i++) {
            if (newValues[i] == null) {
                newValues[i] = oldValues[i];
            }
        }
        return newValues;
    }

    static void createExcel(ResultSet rs) {
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
                cell.setCellValue(rs.getFloat("COGS"));
                cell = row.createCell(3);
                cell.setCellValue(Ops.scrubDate(rs.getDate("Date_Made")));
                cell = row.createCell(4);
                cell.setCellValue(Ops.scrubDate(rs.getDate("Sale_Date")));
                cell = row.createCell(5);
                cell.setCellValue(rs.getFloat("Sale_Price"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String userhome = System.getProperty("user.home") + "/Desktop";
            FileOutputStream out = new FileOutputStream(new File(userhome, "Inv_on_" + Ops.todayDate() + ".xls"));
            workbook.write(out);
            out.close();
            Runtime.getRuntime().exec("open " + userhome + "Inv_on_" + Ops.todayDate() + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void createPDF(ResultSet rs) {
        String spacer = "           ";
        String path = System.getProperty("user.home") + "/Desktop/Inv_on_" + Ops.todayDate() + ".pdf";
        PDDocument doc = new PDDocument();
        PDPage inv = new PDPage();
        doc.addPage(inv);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(doc, inv);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(15, 750);
            contentStream.showText("Country Craftsman Inventory on " + todayDate());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("ID" + spacer + "Description" + spacer + "COGS" + spacer + "Date Made" + spacer + "Sale " + "Date" + spacer + "Sale Price");
            contentStream.newLine();
            while (rs.next()) {
                contentStream.newLine();
                contentStream.showText(rs.getString("ID") + spacer + rs.getString("Desc") + spacer + rs.getFloat("COGS") + spacer + Ops.scrubDate(rs.getDate("Date_Made")) + spacer + Ops.scrubDate(rs.getDate("Sale_Date")) + spacer + rs.getFloat("Sale_Price"));
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
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
}