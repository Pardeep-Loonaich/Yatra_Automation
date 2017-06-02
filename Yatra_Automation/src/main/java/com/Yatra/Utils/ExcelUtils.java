package com.Yatra.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;

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

}
