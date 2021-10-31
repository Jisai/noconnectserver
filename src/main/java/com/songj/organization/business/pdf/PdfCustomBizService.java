package com.songj.organization.business.pdf;

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
        PdfWriter writer = null;
        try {
            writer =PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
            writer.setPageEvent(new Watermark("HELLO WORLD"));// 水印
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //2.3 写入数据之前要打开文档
        document.open();
        //2.4 向文档中添加内容
        try {

            document.addTitle("龙湖保证金收款收据");// 标题
            document.addAuthor("我是作者");// 作者
            document.addSubject("龙湖保证金收款收据");// 主题
            document.addCreator("我是创建者");// 创建者


            try {
                ClassPathResource resource = new ClassPathResource("src\\main\\resources\\image\\image1.jpeg");
                Image image = Image.getInstance(resource.getPath());
                image.setAlignment(Image.ALIGN_LEFT);
                image.scalePercent(5); //依照比例缩放
                document.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }


            // 段落
            Paragraph pg1 = new Paragraph("收款电子收据", titlefont);
            pg1.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
//            pg1.setIndentationLeft(12); //设置左缩进
//            pg1.setIndentationRight(12); //设置右缩进
//            pg1.setFirstLineIndent(24); //设置首行缩进
//            pg1.setLeading(20f); //行间距
            pg1.setSpacingAfter(100f); //设置段落下空白
            document.add(pg1);

            PdfPTable table2 = new PdfPTable(4);
            //设置表格宽度比例为%100
            table2.setWidthPercentage(100);
            // 设置表格默认为无边框
            table2.getDefaultCell().setBorder(0);
            //构建每个单元格
            PdfPCell t2_c1 = new PdfPCell(new Paragraph());
            t2_c1.setBorderColor(BaseColor.RED);
            // 设置无边框
            t2_c1.setBorder(Rectangle.NO_BORDER);
            table2.addCell(t2_c1);
            PdfPCell t2_c2 = new PdfPCell(new Paragraph());
            t2_c2.setBorderColor(BaseColor.RED);
            // 设置无边框
            t2_c2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(t2_c2);
            //
            PdfPCell t2_c3 = new PdfPCell(new Paragraph("开具日前：", textfont));
            // 设置无边框
            t2_c3.setBorder(Rectangle.NO_BORDER);
            t2_c3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.addCell(t2_c3);
            PdfPCell t2_c4 = new PdfPCell(new Paragraph("2020-09-11", textfont));
            // 设置无边框
            t2_c4.setBorder(Rectangle.NO_BORDER);
            t2_c4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(t2_c4);
            document.add(table2);


            PdfPTable table3 = new PdfPTable(4);
            //设置表格宽度比例为%100
            table3.setWidthPercentage(100);
            // 设置表格默认为无边框
            table3.getDefaultCell().setBorder(0);
            //构建每个单元格
            PdfPCell t3_c1 = new PdfPCell(new Paragraph());
            t3_c1.setBorderColor(BaseColor.RED);
            // 设置无边框
            t3_c1.setBorder(Rectangle.NO_BORDER);
            table3.addCell(t3_c1);
            PdfPCell t3_c2 = new PdfPCell(new Paragraph());
            t3_c2.setBorderColor(BaseColor.RED);
            // 设置无边框
            t3_c2.setBorder(Rectangle.NO_BORDER);
            table3.addCell(t3_c2);
            //
            PdfPCell t3_c3 = new PdfPCell(new Paragraph("收据编号：", textfont));
            // 设置无边框
            t3_c3.setBorder(Rectangle.NO_BORDER);
            t3_c3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(t3_c3);
            PdfPCell t3_c4 = new PdfPCell(new Paragraph("SK20200912111030", textfont));
            // 设置无边框
            t3_c4.setBorder(Rectangle.NO_BORDER);
            t3_c4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table3.addCell(t3_c4);
            table3.setSpacingAfter(100f);
            document.add(table3);


            Paragraph pg4 =new Paragraph("Billed To：",textfont);
            pg4.setAlignment(Element.ALIGN_LEFT);
            document.add(pg4);

            Paragraph pg5 =new Paragraph("姓名：",textfont);
            pg5.setAlignment(Element.ALIGN_LEFT);
            document.add(pg5);

            Paragraph pg6 =new Paragraph("公司名称：",textfont);
            pg6.setAlignment(Element.ALIGN_LEFT);
            document.add(pg6);

            Paragraph pg7 =new Paragraph("合同编号：" + "1233466hhfghk",textfont);
            pg7.setAlignment(Element.ALIGN_LEFT);
            document.add(pg7);

            Paragraph pg8 =new Paragraph("合同名称：" + "我是合同名称",textfont);
            pg8.setAlignment(Element.ALIGN_LEFT);
            document.add(pg8);

            // 直线
            Paragraph pg9 = new Paragraph();
            pg9.add(new Chunk(new LineSeparator()));
            document.add(pg9);


            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100); // 宽度100%填充
            PdfPCell cell;
            //第一行
            cell = new PdfPCell(new Phrase("费用名称", textfont));
//            cell.setMinimumHeight(10f); // 设置单元格高度
//            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("金额", textfont));
//            cell.setMinimumHeight(10f); // 设置单元格高度
//            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT); // 设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM); // 设置垂直居中
            cell.setBorder(0);
//            cell.setColspan(2);
            table.addCell(cell);
            table.setSpacingBefore(5f);
            document.add(table);

            // 直线
            Paragraph pg11 = new Paragraph();
            pg11.add(new Chunk(new LineSeparator()));
            document.add(pg11);




            PdfPTable table12 = new PdfPTable(3);
            table12.setWidthPercentage(100); // 宽度100%填充
            //第一行
            cell = new PdfPCell(new Phrase("履约保证金", textfont));
//            cell.setMinimumHeight(20); // 设置单元格高度
//            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
            cell.setBorder(0);
            table12.addCell(cell);

            cell = new PdfPCell(new Phrase("/", textfont));
//            cell.setMinimumHeight(20); // 设置单元格高度
//            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
            cell.setBorder(0);
//            cell.setBorderWidthTop(0);
//            cell.setBorderWidthLeft(0);
//            cell.setBorderWidthRight(0);
            table12.addCell(cell);

            cell = new PdfPCell(new Phrase("14572.11", textfont));
//            cell.setMinimumHeight(20); // 设置单元格高度
//            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT); // 设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM); // 设置垂直居中
            cell.setBorder(0);
//            cell.setColspan(2);
            table12.addCell(cell);
            table12.setSpacingAfter(100f);
            document.add(table12);


            PdfPTable table13 = new PdfPTable(4);
            //设置表格宽度比例为%100
            table13.setWidthPercentage(100);
            // 设置表格默认为无边框
            table13.getDefaultCell().setBorder(0);
            //构建每个单元格
            PdfPCell t13_c1 = new PdfPCell(new Paragraph());
            // 设置无边框
            t13_c1.setBorder(Rectangle.NO_BORDER);
            table13.addCell(t13_c1);
            PdfPCell t13_c2 = new PdfPCell(new Paragraph());
            // 设置无边框
            t13_c2.setBorder(Rectangle.NO_BORDER);
            table13.addCell(t13_c2);
            //
            PdfPCell t13_c3 = new PdfPCell(new Paragraph("小计：", textfont));
            // 设置无边框
            t13_c3.setBorder(Rectangle.NO_BORDER);
            t13_c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table13.addCell(t13_c3);
            PdfPCell t13_c4 = new PdfPCell(new Paragraph("￥" + "14572.11", textfont));
            // 设置无边框
            t13_c4.setBorder(Rectangle.NO_BORDER);
            t13_c4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table13.addCell(t13_c4);
            table13.setSpacingAfter(100f);
            document.add(table13);

            Paragraph pg14 = new Paragraph("小糊涂蛋有限责任公司", titlefont);
            pg14.setAlignment(Element.ALIGN_RIGHT); //设置文字居中 0靠左   1，居中     2，靠右
            document.add(pg14);

            PdfPTable table15 = new PdfPTable(3);
            //设置表格宽度比例为%100
            table15.setWidthPercentage(100);
            // 设置表格默认为无边框
            table15.getDefaultCell().setBorder(0);
            //构建每个单元格
            PdfPCell t15_c1 = new PdfPCell(new Paragraph());
            // 设置无边框
            t15_c1.setBorder(Rectangle.NO_BORDER);
            table15.addCell(t15_c1);
            PdfPCell t15_c2 = new PdfPCell(new Paragraph());
            // 设置无边框
            t15_c2.setBorder(Rectangle.NO_BORDER);
            table15.addCell(t15_c2);
            //
            PdfPCell t15_c3 = new PdfPCell(new Paragraph("此处加盖财务专用章", textfont));
            // 设置无边框
            t15_c3.setBorder(Rectangle.NO_BORDER);
            t15_c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table15.addCell(t15_c3);
            document.add(table15);

            document.addCreationDate();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            //5.关闭文档
            document.close();
        }
    }


}

