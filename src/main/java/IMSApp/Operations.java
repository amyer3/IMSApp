package IMSApp;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
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


public class Operations {

    public static void main(String[] args) {

    }

    public static void firstRun() {
        File statusFile = new File("src/main/statusFileFolder/statusFile.txt");
        if(!statusFile.exists()){
            try {
                statusFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DBHandler.createFullDB();
        }
    }

    public static Boolean blankChecker(String[] values){
        Boolean checked = true;
        for (String value : values) {
            if (value == null) {
                checked = false;
                break;
            }
        }
        return checked;
    }

    public static String scrubDate(Date date){
        if (date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } else {
            return null;
        }
    }

    public static String[] updateArrayFactory(String[] oldValues, String[] newValues){
        for (int i = 0; i < newValues.length; i++) {
            if(newValues[i] == null){
                newValues[i] = oldValues[i];
            } // if statement
        } // for statement
        return newValues;
    }

    public static String statusString(Boolean made, Boolean sold){
        if (made && !sold){
            return "IS FALSE";
        } else if (!made && sold){
            return "IS TRUE";
        } else if (made && sold){
            return "IS FALSE AND TRUE"; //todo sql t&f
        } else return null;
    }

    public static void createExcel(ResultSet rs){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Inventory");
        // HEADERS
        HSSFRow row = spreadsheet.createRow(1);
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
        int i = 2;
        // CELL CONTENT
        try {
            while(rs.next()){
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(rs.getString("ID"));
                cell = row.createCell(1);
                cell.setCellValue(rs.getString("Desc"));
                cell = row.createCell(2);
                cell.setCellValue(rs.getFloat("COGS"));
                cell = row.createCell(3);
                cell.setCellValue(Operations.scrubDate(rs.getDate("Date_Made")));
                cell = row.createCell(4);
                cell.setCellValue(Operations.scrubDate(rs.getDate("Sale_Date")));
                cell = row.createCell(5);
                cell.setCellValue(rs.getFloat("Sale_Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String userhome = System.getProperty("user.home")+"/Desktop";
            FileOutputStream out = new FileOutputStream(new File(userhome,"Inv_on_"+Operations.todayDate()+".xls"));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createPDF(ResultSet rs){
        String path = System.getProperty("user.home")+"/Desktop/Inv_on_"+Operations.todayDate()+".pdf";
        PDDocument doc = new PDDocument();
        PDPage inv = new PDPage();
        doc.addPage(inv);


        try {
            doc.save(path);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static String todayDate(){
        return scrubDate(new Date());
    }

}
