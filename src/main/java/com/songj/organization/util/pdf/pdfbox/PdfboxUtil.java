package com.songj.organization.util.pdf.pdfbox;

//import java.io.File;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * @Classname PdfboxService
 * @Description pdfbox方式  自定义PDF生成
 * https://blog.csdn.net/wumingdu1234/article/details/104418074/
 * https://blog.csdn.net/qq_37022150/article/details/79486730 ;
 * https://developer.aliyun.com/article/671410?spm=a2c6h.13813017.content3.1.29bb2b026P68Ro
 */
public class PdfboxUtil {

    /**
     * 首先生成pdf有很多种方式：
     * iText，生成PDF文档，还支持将XML、Html文件转化为PDF文件；
     * Apache PDFBox，生成、合并PDF文档；
     * docx4j，生成docx、pptx、xlsx文档，支持转换为PDF格式。
     * 比较：
     * iText开源协议为AGPL，而其他两个框架协议均为Apache License v2.0。需要用画笔去花模板，很繁琐！
     * Apache PDFBox，生成、合并PDF文档；
     * docx4j，生成docx、pptx、xlsx文档，支持转换为PDF格式。
     */

    public static void main(String[] args) throws IOException{
        PdfboxUtil pdfboxUtil = new PdfboxUtil();
        String pdfFilePath = "/Users/admin/Downloads";
        String pdfFileName = "pdfbox测试";
        System.out.println(pdfboxUtil.createPdfDemo(pdfFilePath, pdfFileName));
    }

    public String createPdfDemo(String pdfFilePath, String pdfFileName) throws IOException{
        String fielAbsolutePath = pdfFilePath + File.separator + pdfFileName + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".pdf";
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

//		PDFont font = PDTrueTypeFont.loadTTF(document, new File("SIMSUN.TTC"));
        PDFont font = PDType1Font.HELVETICA_BOLD;

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(font, 14);
        contentStream.moveTextPositionByAmount(100, 700);
        contentStream.drawString("Hello World");
//		contentStream.drawString("中文");
        contentStream.endText();

        contentStream.close();

        try {
            document.save(fielAbsolutePath);
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
        document.close();

        return fielAbsolutePath;
    }


    public String createPdfDemo2(String pdfFilePath, String pdfFileName) throws IOException{
        String fielAbsolutePath = pdfFilePath + File.separator + pdfFileName + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".pdf";
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

//		PDFont font = PDTrueTypeFont.loadTTF(document, new File("SIMSUN.TTC"));
        PDFont font = PDType1Font.HELVETICA_BOLD;

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(font, 14);
        contentStream.moveTextPositionByAmount(100, 700);
        contentStream.drawString("Hello World");
//		contentStream.drawString("中文");
        contentStream.endText();

        contentStream.close();

        try {
            document.save(fielAbsolutePath);
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
        document.close();

        return fielAbsolutePath;
    }


}