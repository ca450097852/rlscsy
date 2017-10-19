package com.hontek.comm.util;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFUtil{
	Document document = new Document();// 建立一个Document对象	
	
	public static Font headfont ;// 设置字体大小
	public static Font keyfont;// 设置字体大小
	public static Font textfont;// 设置字体大小
	int maxWidth = 580;

	static{
		BaseFont bfChinese;
		try {
			//bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			headfont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
			keyfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
			textfont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
		} catch (Exception e) {			
			e.printStackTrace();
		} 
	}
	
	
	public PDFUtil(File file) {		 
		 document.setPageSize(PageSize.A4);// 设置页面大小
		 try {
			PdfWriter.getInstance(document,new FileOutputStream(file));
			document.open(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	 public PdfPCell createCell(String value,com.lowagie.text.Font font,int align){
		 PdfPCell cell = new PdfPCell();
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);		 
		 cell.setHorizontalAlignment(align);	
		 cell.setPhrase(new Phrase(value,font));
		return cell;
	}
	
	 public PdfPCell createCell(String value,com.lowagie.text.Font font){
		 PdfPCell cell = new PdfPCell();
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 cell.setHorizontalAlignment(Element.ALIGN_CENTER);	
		 cell.setPhrase(new Phrase(value,font));
		return cell;
	}

	 public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan){
		 PdfPCell cell = new PdfPCell();
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 cell.setHorizontalAlignment(align);	
		 cell.setColspan(colspan);
		 cell.setPhrase(new Phrase(value,font));
		return cell;
	}
	 
	public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,boolean boderFlag){
		 PdfPCell cell = new PdfPCell();
		 cell.setVerticalAlignment(Element.ALIGN_TOP);
		 cell.setHorizontalAlignment(align);	
		 cell.setPhrase(new Phrase(value,font));
		 cell.setPadding(3.0f);
		 if(!boderFlag){
			 cell.setBorder(0);
			 cell.setPaddingTop(15.0f);
			 cell.setPaddingBottom(8.0f);
		 }
		return cell;
	}
	 
	public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan,boolean boderFlag){
		 PdfPCell cell = new PdfPCell();
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 cell.setHorizontalAlignment(align);	
		 cell.setColspan(colspan);
		 cell.setPhrase(new Phrase(value,font));
		 cell.setPadding(3.0f);
		 if(!boderFlag){
			 cell.setBorder(0);
			 cell.setPaddingTop(15.0f);
			 cell.setPaddingBottom(8.0f);
		 }
		return cell;
	}
	
	 public PdfPTable createTable(int colNumber){
		PdfPTable table = new PdfPTable(colNumber);
		try{
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);		
			table.getDefaultCell().setBorder(1);
		}catch(Exception e){
			e.printStackTrace();
		}
        return table;
	}
	 
	 public PdfPTable createTable(float[] widths){
			PdfPTable table = new PdfPTable(widths);
			try{
				table.setTotalWidth(maxWidth);
				table.setLockedWidth(true);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);		
				table.getDefaultCell().setBorder(1);
			}catch(Exception e){
				e.printStackTrace();
			}
	        return table;
		}
	
	 public PdfPTable createBlankTable(){
		 PdfPTable table = new PdfPTable(1);
		 table.getDefaultCell().setBorder(0);
		 table.addCell(createCell("", keyfont));		 
		 table.setSpacingAfter(20.0f);
		 table.setSpacingBefore(20.0f);
		 return table;
	 }
	 
	 public void generatePDF(PdfPTable table) throws Exception{		 
		document.add(table);		
		document.close();
	 }
	 
	 public static void main(String[] args) throws Exception {
		 File file = new File("D:\\test.pdf");
		 if(file.exists()){
			 file.delete();
		 }
		 file.createNewFile();

		 PDFUtil pdfReport =new PDFUtil(file);
			PdfPTable table = pdfReport.createTable(4);
			
			table.addCell(pdfReport.createCell("企业信息", headfont,Element.ALIGN_CENTER,4,false));			
			
			table.addCell(pdfReport.createCell("企业名称:", keyfont, Element.ALIGN_RIGHT,true));
			table.addCell(pdfReport.createCell("广州市天河区东圃镇审核测试企业", textfont, Element.ALIGN_LEFT,true));
			
			table.addCell(pdfReport.createCell("简称:", keyfont, Element.ALIGN_RIGHT,true));
			table.addCell(pdfReport.createCell("东圃镇", textfont, Element.ALIGN_LEFT,true));
			
			table.addCell(pdfReport.createCell("企业名称:", keyfont, Element.ALIGN_RIGHT,true));
			table.addCell(pdfReport.createCell("广州市天河区东圃镇审核测试企业", textfont, Element.ALIGN_LEFT,true));
			
			table.addCell(pdfReport.createCell("简称:", keyfont, Element.ALIGN_RIGHT,true));
			table.addCell(pdfReport.createCell("东圃镇", textfont, Element.ALIGN_LEFT,true));
			
			table.addCell(pdfReport.createCell("企业名称:", keyfont, Element.ALIGN_RIGHT,true));
			table.addCell(pdfReport.createCell("广州市天河区东圃镇审核测试企业", textfont, Element.ALIGN_LEFT,true));
			
			table.addCell(pdfReport.createCell("简称:", keyfont, Element.ALIGN_RIGHT,true));
			table.addCell(pdfReport.createCell("东圃镇", textfont, Element.ALIGN_LEFT,true));
			
			pdfReport.generatePDF(table);		
	}
	
	
}

