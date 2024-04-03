package Utility;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ReadExcelFile {

    FileInputStream fileInputStream;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;

    Object[][] arr ;

    public ReadExcelFile(String fileName){

        try{
            fileInputStream = new FileInputStream("C:\\Users\\rajat\\IdeaProjects\\ApiAutomationProject_RestAssured\\src\\test\\TestData\\"+fileName+".xlsx");
            workbook = new XSSFWorkbook(fileInputStream);
        }catch (Exception exception){

        }

    }

    public int totalRows( int SheetIndex ){

        return workbook.getSheetAt(SheetIndex).getLastRowNum()+1;
    }

    public int totalCells( int SheetIndex ){

        return workbook.getSheetAt(SheetIndex).getRow(0).getLastCellNum();

    }

    public Object[][] getExcelData(  int SheetIndex){


        int rows = totalRows(SheetIndex)-1;
        int cells = totalCells(SheetIndex);

        arr = new Object[rows][cells];

        for(int i = 1; i<=rows; i++){
            for(int j = 0; j<cells; j++){

                String cellValue = workbook.getSheetAt(SheetIndex).getRow(i).getCell(j).toString();

                arr[i-1][j] = cellValue;
            }
        }
         return arr ;

    }
}
