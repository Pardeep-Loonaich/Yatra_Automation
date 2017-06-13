package com.Yatra.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;

public class ExcelUtils 

{
	String workBookName;
	String sheetName;
	HSSFSheet sheet;
	String testCaseId;
	private static final String PAGE_LOAD_TIME_FILE="";
	private static final String PAGE_PERFORMANCE_SHEET_NAME="";

	public HSSFSheet initiateExcelConnection(String workSheet, String workBookName, boolean doFilePathMapping) 
	{
		try {

			String filePath = "";
			if (doFilePathMapping)
				filePath = ".\\src\\main\\resources\\" + workBookName;
			else
				filePath = workBookName;

			filePath = filePath.replace("\\", File.separator);
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			sheet = wb.getSheet(workSheet);
		}

		catch (RuntimeException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return sheet;
	}

	public String getData(int row,int column)

	{
		String dataToBeReturn="";
		if(!(sheet.getRow(row).getCell(column).getCellType()==1))
		{
			sheet.getRow(row).getCell(column).setCellType(1);//1=string
		}
		dataToBeReturn=sheet.getRow(row).getCell(column).getStringCellValue();
		return dataToBeReturn;
	}


	/**
	 * @author harveer.singh
	 * @Description:
	 * @param: HSSFSheet Obj
	 * @param: test case id in String format
	 * Return: it will return a list of row No. of all available with same test case id
	 */

	public ArrayList<Integer> getRowNumbers(HSSFSheet sheet,String testCaseId)

	{ 
		ArrayList<Integer> listOfRowNumber = new ArrayList<Integer>();

		int rowCount=0;
		rowCount=sheet.getPhysicalNumberOfRows();
		for(int i=1;i<rowCount;i++)
		{
			String cValue= sheet.getRow(i).getCell(0).getStringCellValue();
			//System.out.println("Test Case: "+cValue);//for debugging

			if(cValue.trim().equalsIgnoreCase(testCaseId.trim()))
			{
				listOfRowNumber.add(i);
			}
		}

		return listOfRowNumber;

	}
	/**
	 * @author harveer.singh
	 * @param sheet
	 * @return: it will return all Name is a list
	 */
	public ArrayList<String> getHeaders(HSSFSheet sheet)

	{
		ArrayList<String> listOfHeaderName=new ArrayList<String>();
		int columnCount=0;

		columnCount=sheet.getRow(0).getPhysicalNumberOfCells();
		for(int i=0;i<columnCount;i++)
		{
			if(!(sheet.getRow(0).getCell(i).getStringCellValue()==null))
			{
				listOfHeaderName.add(sheet.getRow(0).getCell(i).getStringCellValue());
			}    
		}
		return listOfHeaderName;
	}//getHeaders

	public void setCellValue(Map<String,Integer> pageLoadTimeData)

	{
		try
		{
			File file=new File(PAGE_LOAD_TIME_FILE);
			HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(file));
			workbook.getSheet(PAGE_PERFORMANCE_SHEET_NAME);


		}
		catch(Exception e)
		{
			e.printStackTrace();


		}

	}

	private static void writePageLoadTimeToExcel(String filePath, double[] data) throws Exception
	{
		String sSheetName="Page Load Time";
		HSSFWorkbook workbook=null;
		HSSFSheet sheet=null;
		try {

			//check if file exist
			try {
				
				FileInputStream fileInputStreamObj = new FileInputStream(new File(filePath));
				workbook = new HSSFWorkbook(fileInputStreamObj);
				sheet = workbook.getSheet(sSheetName);
			} catch (FileNotFoundException e) 
			{
				System.out.println("File Not found...Create new file");
				Log.message("Excel File/Sheet not found, please check file name again");
			}catch(Exception e){
				System.out.println("Workbook/sheet not fetched");
			}


			//creating new file
			if(workbook==null){
				workbook = new HSSFWorkbook();
				sheet = workbook.createSheet("Load Time");

				HSSFCellStyle style = workbook.createCellStyle();
				style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				Row row = sheet.createRow(0);
				row.createCell(0).setCellValue("Time Stamp");
				row.getCell(0).setCellStyle(style);
				row.createCell(1).setCellValue("Result Page (in sec)");
				row.createCell(2).setCellValue("Pricing Page (in sec)");
				row.createCell(3).setCellValue("Passenger Page (in sec)");
				row.createCell(4).setCellValue("Payment Page (in sec)");

				row.getCell(1).setCellStyle(style);
				row.getCell(2).setCellStyle(style);
				row.getCell(3).setCellStyle(style);
				row.getCell(4).setCellStyle(style);
			}

			//write into file
			int rowNum = sheet.getPhysicalNumberOfRows();
			Row row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(getCurrentDate());
			sheet.autoSizeColumn(0);


			for(int i=0; i<data.length; i++){
				row.createCell(i+1).setCellValue(data[i]);
				sheet.autoSizeColumn(i+1);
				CellUtil.setAlignment(row.getCell(i+1), workbook, CellStyle.ALIGN_CENTER);
			}

			FileOutputStream fileOutputStremObj = new FileOutputStream(new File(filePath));
			workbook.write(fileOutputStremObj);
			System.out.println("File write completed");




		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getCurrentDate(){

		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss");
		//    	System.out.println(dateFormat.format(currentDate));

		return dateFormat.format(currentDate);
	}

}
