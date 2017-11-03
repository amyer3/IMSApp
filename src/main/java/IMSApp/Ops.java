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
    } // TODO: 11/2/17 every 24h backup to excel in different folder 

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
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        openFile(path);
    }

    static void createPDF(ResultSet rs) {
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
            contentStream.showText(spacer("ID", 12)+spacer("Description", 50)+spacer("COGS", 10)+spacer("Date" +
                    " Made", 15)+spacer("Sale Date", 15)+ spacer("Price", 10));
            contentStream.newLine();
            while (rs.next()) {
                String id = rs.getString("ID");
                String Desc = rs.getString("Desc");
                String COGS = rs.getString("COGS");
                String DM = Ops.scrubDate(rs.getDate("Date_Made"));
                String SD = Ops.scrubDate(rs.getDate("Sale_Date"));
                String SP = rs.getString("Sale_Price");
                contentStream.newLine();
                contentStream.showText(spacer(id, 12)+spacer(Desc, 50)+spacer(COGS, 10)+spacer(DM, 15)+spacer(SD,
                        15) +spacer(SP, 10));
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

    static String priceFormatter(String input){
        if(!input.contains(".")){
            return input.concat(".00");
        } else {
            return input;
        }
    }

    static void openFile(String location){
        try {
            Runtime.getRuntime().exec("open "+location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String spacer(String txt, int sChars){// TODO: 11/2/17 2017-11-02  Broken Here
        if(txt == null){txt = "N/A";}
        String space = " ";
        StringBuilder ret = new StringBuilder();
        ret.append(txt);
        int len = txt.length();
        int goal = sChars - len;
        for (int i = 0; i < goal+1; i++) {
            ret.append(space);
        }
        return ret.toString();
    }
}