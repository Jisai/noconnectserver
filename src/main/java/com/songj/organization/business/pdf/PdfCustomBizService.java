package com.songj.organization.business.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @Classname PdfCustomBizService
 * @Description 自定义PDF生成
 * 【参考】https://blog.csdn.net/weixin_37848710/article/details/89522862；
 * https://blog.csdn.net/qq_16192007/article/details/101383966；
 * https://blog.csdn.net/JavaBuilt/article/details/101640673
 */
@Service
public class PdfCustomBizService {

    // 定义全局的字体静态变量
    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;
    // 最大宽度
    private static int maxWidth = 520;
    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 16, Font.BOLD);
            headfont = new Font(bfChinese, 14, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
            textfont = new Font(bfChinese, 10, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String createPdfDemo(String pdfFilePath, String pdfFileName){
        //1. 创建文件
        //1.1 判断文件路径是否存在，不存在则创建
        File dir = new File(pdfFilePath);
        if(! dir.exists()){
            dir.mkdirs();
        }
        //1.2 创建文件
        File pdfFile = null;
        try {
            pdfFile = File.createTempFile(pdfFileName + System.currentTimeMillis(), ".pdf", dir);
        } catch (IOException e) {
            System.out.println("创建临时打印文件失败!！");
            e.printStackTrace();
        }
        //2 创建PDF
        createPdfCustom(pdfFile);
        String pdfPath = pdfFile.getAbsolutePath();
        return pdfPath;
    }

    /**
     * 生成pdf 简单demo
     */
    private void createPdfDemo(File pdfFile){
        BaseFont bf = null;
        Font font = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);//创建字体
            font = new Font(bf, 12, Font.BOLD);//使用字体
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.1 指定页面大小为A4，且自定义页边距(marginLeft、marginRight、marginTop、marginBottom)
        Document document =new Document(PageSize.A4,50,50,30,20);
        //2.2 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。[生成PDF文件到磁盘]
        try {
            PdfWriter writer =PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //2.3 写入数据之前要打开文档
        document.open();
        //2.4 向文档中添加内容
        try {
            document.add(new Paragraph("任务编号：20190701        开始日期：20190701", font));
            document.add(new Paragraph("任务名称：常州武进1区     结束日期：20190701", font));
            document.add(new Paragraph("平均飞行高度：100m        平均飞行速度：100km/h", font));
            document.add(new Paragraph("任务面积：1000㎡      结束日期：20190701", font));
            document.add(new Paragraph("飞行总时长：1000㎡", font));
            document.addCreationDate();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            //5.关闭文档
            document.close();
        }
    }


    /**
     * 生成个性化pdf
     */
    private void createPdfCustom(File pdfFile) {
        BaseFont bf = null;
        Font font = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);//创建字体
            font = new Font(bf, 12, Font.BOLD);//使用字体
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.1 指定页面大小为A4，且自定义页边距(marginLeft、marginRight、marginTop、marginBottom)
        Document document =new Document(PageSize.A4,50,50,30,20);
        //2.2 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。[生成PDF文件到磁盘]
        try {
            PdfWriter writer =PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //2.3 写入数据之前要打开文档
        document.open();
        //2.4 向文档中添加内容
        try {

            document.addTitle("Title@PDF-Java");// 标题
            document.addAuthor("Author@umiz");// 作者
            document.addSubject("Subject@iText pdf sample");// 主题
            document.addKeywords("Keywords@iTextpdf");// 关键字
            document.addCreator("Creator@umiz`s");// 创建者

            // 段落
            Paragraph pg1 = new Paragraph("收款电子收据", titlefont);
            pg1.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
//            pg1.setIndentationLeft(12); //设置左缩进
//            pg1.setIndentationRight(12); //设置右缩进
//            pg1.setFirstLineIndent(24); //设置首行缩进
//            pg1.setLeading(20f); //行间距
            pg1.setSpacingBefore(200f); //设置段落上空白
//            pg1.setSpacingAfter(30); //设置段落下空白
            document.add(pg1);

            Paragraph pg2 =new Paragraph("开具日期：",textfont);
//            pg2.setFirstLineIndent(30);
//            pg2.setSpacingAfter(10); //设置段落下空白
            document.add(pg2);

            Paragraph pg3 =new Paragraph("收据编号：",textfont);
            pg3.setSpacingAfter(30);
            document.add(pg3);

            Paragraph pg4 =new Paragraph("Billed To：",textfont);
            pg4.setFirstLineIndent(20);
            document.add(pg4);

            Paragraph pg5 =new Paragraph("姓名：",textfont);
            pg5.setFirstLineIndent(20);
            document.add(pg5);

            Paragraph pg6 =new Paragraph("公司名称：",textfont);
            pg6.setFirstLineIndent(20);
            document.add(pg6);

            Paragraph pg7 =new Paragraph("合同编号：" + "1233466hhfghk",textfont);
            pg7.setFirstLineIndent(20);
            document.add(pg7);

            Paragraph pg8 =new Paragraph("合同名称：" + "我是合同名称",textfont);
            pg8.setFirstLineIndent(20);
            document.add(pg8);

            // 直线
            Paragraph pg9 = new Paragraph();
            pg9.add(new Chunk(new LineSeparator()));
            pg9.setSpacingAfter(30); //设置段落下空白
            document.add(pg9);


            Paragraph pg10 =new Paragraph("费用名称：" + "我是合同名称",textfont);
            pg10.setFirstLineIndent(20);
            document.add(pg10);

            // 直线
            Paragraph pg11 = new Paragraph();
            pg11.add(new Chunk(new LineSeparator()));
            document.add(pg11);

            PdfPTable table =new PdfPTable(3);//括号参数表示列

            PdfPCell cell_0_0 = new PdfPCell();
            cell_0_0.setPhrase(new Paragraph("some text"));
            
            table.addCell(cell_0_0);
            document.add(table);


            document.addCreationDate();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            //5.关闭文档
            document.close();
        }
    }


}

