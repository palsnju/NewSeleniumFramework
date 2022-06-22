package com.test.genericlib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class FileUtils {
	
	public String getDir() {
		return "src\\test\\resource\\";
	}
	public Properties getPropertyFileObject() throws Throwable
	{
		FileInputStream fis = new FileInputStream(getDir()+"commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		return pObj;
	}
    public String getExcelData(String fileName, int sheetName, int rowName, int colName) throws Throwable
    {
		FileInputStream fis = new FileInputStream(getDir()+fileName+".xlsx");
    	Workbook wb = WorkbookFactory.create(fis);
    	Sheet sh = wb.getSheetAt(sheetName);	
    	Row row = sh.getRow(rowName);
    	String data = row.getCell(colName).getStringCellValue();
    	wb.close();
    	return data;
    }
    public void setExcelData(String fileName, int sheetName, int rowName, int colName, String data)throws Throwable
	{
		FileInputStream fis = new FileInputStream(getDir()+fileName+".xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheetAt(sheetName); 
        Row row = sh.getRow(rowName);
        Cell cel = row.createCell(rowName);
        cel.setCellValue(data);
		FileOutputStream fos = new FileOutputStream(getDir()+fileName+".xlsx");
		wb.write(fos);
		wb.close();
	}
	
	public int getExcelCell(String fileName, int sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream(getDir()+fileName+".xlsx");
    	Workbook wb = WorkbookFactory.create(fis);
    	Sheet sh = wb.getSheetAt(sheetName);
    	Row row = sh.getRow(0);
    	int data=row.getLastCellNum();
    	wb.close();
    	return data;	
	}
	public int getExcelRows(String fileName, int sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream(getDir()+fileName+".xlsx");
    	Workbook wb = WorkbookFactory.create(fis);
    	Sheet sh = wb.getSheetAt(sheetName);
    	int data= sh.getLastRowNum();
    	wb.close();
    	return data;	
	}
	
}
