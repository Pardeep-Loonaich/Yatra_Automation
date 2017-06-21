package com.Yatra.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

/**
 * @Description: this class contain all method to related to write data into excel
 * @author harveer.singh
 * /**
 *
 */
public class WriteToExcel 
{

	private  static String EXCEL_FILE_PATH=".//src/main/resources//pageLoadTimeData.xls";
	private  static String EXCEL_SHEET_NAME="flight";

	/**
	 * * initiateExcelConnection function to establish an initial connection with</br>
	 * a work sheet
	 * @param workSheet(String)
	 * @param doFilePathMapping(boolean)
	 * @param workBookName(String)
	 * @return HSSFSheet (Work sheet)
	 * **/

	public  HSSFWorkbook getWorkBook(String sFilePath) {

		HSSFWorkbook wb=null;

		try {
			sFilePath = sFilePath.replace("\\", File.separator);
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(sFilePath));
			wb = new HSSFWorkbook(fs);

		}

		catch (RuntimeException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return wb;
	}

/**
 * @Description: write data to excel file
 * @Param:
 */
	public static void writePageLoadTimeToExcel__Test(List<String> data) throws Exception
	{
	
		HSSFSheet sheet=null;
		HSSFWorkbook workBook=null;
		FileOutputStream fos=null;
		Row row=null;
		WriteToExcel write=new WriteToExcel();
		try
		{
			workBook=write.getWorkBook(EXCEL_FILE_PATH);
			sheet = workBook.getSheet(EXCEL_SHEET_NAME);
			int rowNum=sheet.getPhysicalNumberOfRows();
			row = sheet.createRow(rowNum);
			int iColumn=sheet.getRow(0).getLastCellNum();
			
			row.createCell(0).setCellValue(getCurrentTimeStamp());

			
				for(int listIndex=0;listIndex<data.size();listIndex++)
				{
					
					Cell cell=row.createCell(listIndex+1);
					cell.setCellType(CellStyle.ALIGN_CENTER);
					
					cell.setCellValue(data.get(listIndex));
						
			}

			 fos=new FileOutputStream(EXCEL_FILE_PATH.replace("\\", File.separator));
			workBook.write(fos);
		}
		catch(Exception ex)
		{

			Log.message("could not able to write to excel file: "+EXCEL_SHEET_NAME+", visit your code again !!");
		}
		finally
		{
			fos.close();
			//close work book connection also
			workBook=null;//closed work book connection
			
		}
	}
		
		
		
		public static void writePageLoadTimeToExcel(Map<String,String> data) throws Exception
		{
			
			HSSFSheet sheet=null;
			HSSFWorkbook workBook=null;
			FileOutputStream fos=null;
			Row row=null;
			WriteToExcel write=new WriteToExcel();
			ReadFromExcel read=new ReadFromExcel();
			
			
			
			try
			{
				workBook=write.getWorkBook(EXCEL_FILE_PATH);
				sheet = workBook.getSheet(EXCEL_SHEET_NAME);
				int rowNum=sheet.getPhysicalNumberOfRows();
				List <String> headersName=read.getHeaders(sheet);
				row = sheet.createRow(rowNum);

				data.put(headersName.get(0),getCurrentTimeStamp());// to put time stamp at first column
				
					for(int listIndex=0;listIndex<headersName.size();listIndex++)
					{
						if(data.containsKey(headersName.get(listIndex).trim()))
						{
						String sValue=data.get(headersName.get(listIndex).trim());					
						Cell cell=row.createCell(listIndex);
						cell.setCellType(CellStyle.ALIGN_CENTER);			
						cell.setCellValue(sValue);
						}
							
				}

				 fos=new FileOutputStream(EXCEL_FILE_PATH.replace("\\", File.separator));
				workBook.write(fos);
			}
			catch(Exception ex)
			{

				Log.message("could not able to write to excel file: "+EXCEL_SHEET_NAME+", visit your code again !!");
			}
			finally
			{
				fos.close();
				//close work book connection also
				workBook=null;//closed work book connection
				
			}
		

	}
	/**
	 * Description: to generate current date time stamp
	 * @return
	 */
	private static String getCurrentTimeStamp()

	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	
	
	public static void main(String[] args) throws Exception 
	{
		Map<String,String> performanceData=new LinkedHashMap<String, String>();
		performanceData.put("HomePage", "1");
		performanceData.put("SRP Page", "2");
		performanceData.put("SRP URL", "test.com");	
		performanceData.put("Travllers Page", "23");
		//writePageLoadTimeToExcel_Test(performanceData);
		
	}
}
