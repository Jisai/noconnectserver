package com.songj.organization.business.pdf;

import cn.hutool.core.io.IoUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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
public class ITextPdfService {

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
        createPdfDemo(pdfFile);
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
            ClassPathResource resource = new ClassPathResource("static/images/image1.jpeg");
            InputStream inputStreamImg = resource.getInputStream();
            byte[] imageByte = IoUtil.readBytes(inputStreamImg);
            Image image = Image.getInstance(imageByte);
            image.setAlignment(Image.ALIGN_LEFT);
            image.scalePercent(15);
            document.add(image);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

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



}

