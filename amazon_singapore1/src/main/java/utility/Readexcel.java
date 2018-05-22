
package utility;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import jxl.*;
import jxl.write.*;
import jxl.Cell;
import jxl.read.biff.BiffException;
import jxl.write.Boolean;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.CellValue;
import jxl.write.biff.RowsExceededException;

import java.io.File; 

import jxl.Cell; 
import jxl.Sheet;
import jxl.Workbook;
public class Readexcel {

	public  static void signin1() throws Exception{

	Properties props = new Properties();	
			
			InputStream in = null;
			
			FileInputStream fs1 = null;
			
			try {
				fs1 = new FileInputStream("C:\\Users\\pankaj\\workspace\\amazon_singapore1\\src\\main\\java\\utility\\TITID1.xlsx");
				System.out.print("pased");
			} catch (FileNotFoundException e11) {
				// TODO Auto-generated catch block
				e11.printStackTrace();
			}
			
					Workbook wb1 = null;
					try {
						wb1 = Workbook.getWorkbook(fs1);
					} catch (Exception e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
			
			Sheet sh1 = wb1.getSheet(0);
			
			
			try{
				int rows = sh1.getRows();

						int columns=sh1.getColumns();
						
									
						
								for(int k=0;k<columns;k++)
								{
									
									for(int j=0;j<rows;j++)

									{
										Cell a2=sh1.getCell(k,0);
										Cell a3=sh1.getCell(k,j+1);
										props.setProperty(""+a2.getContents(), ""+a3.getContents());

									    }

						
								}
			
						
						

			File f = new File("common.properties");
	        OutputStream out = new FileOutputStream( f );
	        props.store(out, "This is an optional header comment string");

			}
			catch(Exception e){
					  System.out.println("error:- " + e.getMessage());
			}
}
}