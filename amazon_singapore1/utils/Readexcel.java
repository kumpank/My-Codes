

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class TestIns {

	public static void main( String[] args ) throws BiffException, IOException, WriteException{
		

		FileInputStream fs1 = null;
				try {
					fs1 = new FileInputStream("D:\\Test_Instructions.xls");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileInputStream fs2 = null;
				try {
					fs2 = new FileInputStream("D:\\Test_Data.xls");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileInputStream fs3 = null;
				try {
					fs3 = new FileInputStream("D:\\TCD.xls");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

						Workbook wb1 = null;
						try {
							wb1 = Workbook.getWorkbook(fs1);
						} catch (BiffException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						WritableWorkbook copy = null;
						try {
							copy = Workbook.createWorkbook(new File("D:\\temp.xls"), wb1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}





						
				Workbook wb2 = null;
				try {
					wb2 = Workbook.getWorkbook(fs2);
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				WritableWorkbook copy2 =null;
				try {
					 copy2 = Workbook.createWorkbook(new File("D:\\temp2.xls"), wb2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Workbook wb3 = Workbook.getWorkbook(fs3);
				

				//WritableSheet  c2 =copy.createSheet("pankaj", 0);
				WritableSheet	c1= copy.getSheet(0);
				WritableSheet	c2= copy2.getSheet(0);
				//System.out.println(c1);
				//WritableCellFormat newFormat = new WritableCellFormat();

			//newFormat.setBackground(Colour.RED);
				Sheet sh1 = wb1.getSheet(0);
				Sheet sh2 = wb2.getSheet(0);
				Sheet sh3 = wb3.getSheet(0);
				
				
				
				
				try{
					int row=1;
					//
					 while (row <sh1.getRows() )
					{
						
						 //
						int row2 = 1;
						boolean flag1=false;
				        while (row2 <sh2.getRows() )
						{
				                
				        	if(sh1.getCell(0, row).getContents().trim().equalsIgnoreCase(sh2.getCell(0, row2).getContents().trim()))
				        	{
				        		 flag1=true;   
			                    //c1.setCellFormat(newFormat);
							//log.info sh1.getCell(0, row).getContents()+ "TI row "+row+" Matched with "+sh2.getCell(0, row2).getContents().trim()+"Test Data"+ row2;
				        		
				        		int row3 = 1;
				        		boolean flag= false;
				        		int foundAtTestData = row2;
				        		int foundAtRow3 = 0;
			     				while (row3 < sh3.getRows())
			     			{
			     					System.out.println(row3);
			     			//log.info sh3.getCell(1, row3).getContents()
			     					WritableCellFormat  cellFormat = new WritableCellFormat();
			     					WritableCellFormat  cellFormat2 = new WritableCellFormat();
			     					cellFormat.setBackground(Colour.YELLOW) ;
					     		      cellFormat.setWrap(true);
				     		      cellFormat.setBackground(Colour.YELLOW) ;
					     		      cellFormat2.setWrap(true);
			     		if(sh1.getCell(0, row).getContents().trim().equalsIgnoreCase(sh3.getCell(1, row3).getContents().trim())){
			     			System.out.println("Found " + sh1.getCell(0, row).getContents().trim() + " At " + row3);
			     			 flag=true;
			     			 //foundAtRow3 = row3;
			     			 //break;
			     			
		     				 if(sh3.getCell(2, row3).getContents().trim().length()>0)
		     				 {
		 				       Label mode = new Label(0,row,""+sh3.getCell(2 , row3).getContents().trim() + " matched in TCD " + (row3 + 1) + " ",cellFormat);
		 				       Label mode2 = new Label(0,row2,""+sh3.getCell(2 , row3).getContents().trim()+ " matched in TCD " + (row3 + 1) + " ",cellFormat2);
		     				       
			                    c1.addCell(mode); 
			                    c2.addCell(mode2);
		     				 }
			     		} /*else {
			     		     Label mode3 = new Label(0,row,""+sh1.getCell(0 , row).getContents().trim()+  " matched in TD " + row2 + " ");
			     		     Label mode4 = new Label(0,row2,""+sh2.getCell(0 , row2).getContents().trim()+  " matched in TCD " + row2 + " ");
			     		       
     	                    c1.addCell(mode3); 
     	                    c2.addCell(mode4);
			     		}*/
			     		
//			     			if(flag);
//			     		{
//			     				
//			     				 WritableCellFormat  cellFormat = new WritableCellFormat();
//			     			      //cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//			     			      cellFormat.setBackground(Colour.YELLOW) ;
//			     			      cellFormat.setWrap(true);	
//			     			      WritableCellFormat  cellFormat1 = new WritableCellFormat();
//			     			      //cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//			     			      cellFormat1.setBackground(Colour.YELLOW) ;
//			     			      cellFormat1.setWrap(true);	
//			     				        		
//			     				       Label mode = new Label(0,row,""+sh1.getCell(0 , row).getContents().trim()+row3,cellFormat);
//			     				       Label mode2 = new Label(0,row2,""+sh2.getCell(0 , row2).getContents().trim()+row3,cellFormat1);
//			     				       
//			     			                     c1.addCell(mode); 
//			     			                    c2.addCell(mode2); 
//			     			         //log.info row          
//			     			                    
//			     			}
			     			//if(row3==sh3.getRows() && flag == false)
			     			//{
			     				//int k=sh3.getRows();
			     			//if(row3==k)
			     			      
			     			//{
			     				//log.info "p"
			     				//WritableCellFormat  cellFormat3 = new WritableCellFormat();
			     		      //cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//			     		      cellFormat3.setBackground(Colour.RED) ;
//			     		      cellFormat3.setWrap(true);	
//			     		      WritableCellFormat  cellFormat4 = new WritableCellFormat();
//			     		      //cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//			     		      cellFormat4.setBackground(Colour.RED) ;
//			     		      cellFormat4.setWrap(true);	
//			     		     Label mode3 = new Label(0,row,""+sh1.getCell(0 , row).getContents().trim()+row,cellFormat3);
//			     		       Label mode4 = new Label(0,row2,""+sh2.getCell(0 , row2).getContents().trim()+row2,cellFormat4);
//			     		       
//			     	                     c1.addCell(mode3); 
//			     	                    c2.addCell(mode4);
			     	                     
			     			//}
			     			//}
			     
			     		row3=row3+1;
			     		
				        	}
			     				if (flag == false) {
			     					WritableCellFormat  cellFormat3 = new WritableCellFormat();
			     					WritableCellFormat  cellFormat4 = new WritableCellFormat();
			     					cellFormat3.setBackground(Colour.RED) ;
					     		      cellFormat3.setWrap(true);
				     		      cellFormat4.setBackground(Colour.RED) ;
					     		      cellFormat4.setWrap(true);
			     					Label mode3 = new Label(0,row,""+sh1.getCell(0 , row).getContents().trim() + " matched in TD ony  " + (row2 + 1) + " ",cellFormat3);
				 				    Label mode4 = new Label(0,row2,""+sh2.getCell(0 , row2).getContents().trim()+ " matched in TD only " + (row2 + 1) + " ",cellFormat4);
				     				       
					                    c1.addCell(mode3); 
					                    c2.addCell(mode4);
			     				}

			     				//System.out.println("Searching for " + sh1.getCell(0, row).getContents().trim());
			     				//System.out.println(" Found in test data at " + foundAtTestData);
			     				//System.out.println("Found in TCD at " + foundAtRow3);
			     				//System.out.println("======================================");
				        	}
				        
				        	row2=row2+1;
						}
				    	if(flag1 == false)
			        	{WritableCellFormat  cellFormat5 = new WritableCellFormat();
     					
		     		     cellFormat5.setBackground(Colour.BLUE) ;
		     		      cellFormat5.setWrap(true);
     					Label mode5 = new Label(0,row,""+sh1.getCell(0 , row).getContents().trim() + " not matched in TD   "  + " ",cellFormat5);
	 				    
		                    c1.addCell(mode5); 
			        	}
				       row=row+1;
					}


				

					 copy.write();
						copy.close();	
			copy2.write();
				copy2.close();


					}


				catch(Exception e){
						  System.out.println("error:- " + e.getMessage());
				}
			
	}

	private static void elseif(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
	