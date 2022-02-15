package com.songj.organization.util.pdf.itext;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * @Classname PdfCustomBizService
 * @Description itextpdf方式  自定义PDF生成
 * 【参考】https://blog.csdn.net/weixin_37848710/article/details/89522862；
 * https://blog.csdn.net/qq_16192007/article/details/101383966；
 * https://blog.csdn.net/JavaBuilt/article/details/101640673
 */

@Slf4j
public class ITextPdfUtil {

    // 定义全局的字体静态变量
    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font remarkfont;
    private static Font textfont;
    private static Font numfont;
    // 最大宽度
    private static int maxWidth = 520;
    // 静态代码块
    static {
        try {
            /**
             * Chinese Simplified: STSong-Light and STSongStd-Light with the encodings UniGB-UCS2-H and UniGB-UCS2-V
             * Chinese Traditional: MHei-Medium, MSung-Light and MSungStd-Light with the encodings UniCNS-UCS2-H and UniCNS-UCS2-V
             * Japanese: HeiseiMin-W3, HeiseiKakuGo-W5 and KozMinPro-Regular with the encodings UniJIS-UCS2-H, UniJIS-UCS2-V, UniJIS-UCS2-HW-H and UniJIS-UCS2-HW-V
             * Korean: HYGoThic-Medium, HYSMyeongJo-Medium and HYSMyeongJoStd with the encodings UniKS-UCS2-H and UniKS-UCS2-V
             */
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
//            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont bfJapanese = BaseFont.createFont("HeiseiMin-W3", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 16, Font.BOLD);
            headfont = new Font(bfChinese, 14, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
            remarkfont = new Font(bfChinese, 10, Font.NORMAL);
            textfont = new Font(bfChinese, 13, Font.NORMAL);
            numfont = new Font(bfJapanese, 12, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String createPdfDemo(String pdfFilePath, String pdfFileName){
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
    private static void createPdfDemo(File pdfFile){

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
            document.add(new Paragraph("任务编号：20190701        开始日期：20190701", textfont));
            document.add(new Paragraph("任务名称：常州武进1区     结束日期：20190701", textfont));
            document.add(new Paragraph("平均飞行高度：100m        平均飞行速度：100km/h", textfont));
            document.add(new Paragraph("任务面积：1000㎡      结束日期：20190701", textfont));
            document.add(new Paragraph("飞行总时长：1000㎡", textfont));
            document.addCreationDate();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            //5.关闭文档
            document.close();
        }
    }


    public String encodeBase64(byte[] fileByte){
        String result = Base64.encode(fileByte);
        return result;
    }

    /**
     * 生成pdf
     */
    private  byte[] createPdfByCustom() {
        byte[] result=null;
        ByteArrayOutputStream baos = null;
        InputStream inputStreamImg = null;
        BaseFont bf = null;
        Document document =new Document(PageSize.A4,50,50,30,20);
        float paragraphSpacing = 8f;
        try {
            baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document ,baos);

            document.open();

            document.addTitle("我是标题");
            document.addAuthor("我是作者");
            document.addSubject("我是主题");
            document.addCreator("我是创建者");

            ClassPathResource resource = new ClassPathResource("static/image/longfor_logo_1.png");
            inputStreamImg = resource.getInputStream();
            byte[] imageByte = IoUtil.readBytes(inputStreamImg);
            Image image = Image.getInstance(imageByte);
            image.setAlignment(Image.ALIGN_LEFT);
            image.scalePercent(15);
            document.add(image);

            Paragraph block1 = new Paragraph("收款电子收据", textfont);
            block1.setAlignment(1);
            block1.setSpacingBefore(100f);
            block1.setSpacingAfter(70f);
            document.add(block1);
            PdfPTable block2 = new PdfPTable(4);
            block2.setWidthPercentage(100);
            PdfPCell block2C1 = new PdfPCell(new Paragraph());
            block2C1.setBorder(Rectangle.NO_BORDER);
            block2.addCell(block2C1);
            PdfPCell block2C2 = new PdfPCell(new Paragraph());
            block2C2.setBorder(Rectangle.NO_BORDER);
            block2.addCell(block2C2);
            PdfPCell block2C3 = new PdfPCell(new Paragraph("开具日期：", textfont));
            block2C3.setBorder(Rectangle.NO_BORDER);
            block2C3.setHorizontalAlignment(Element.ALIGN_LEFT);
            block2.addCell(block2C3);
            PdfPCell block2C4 = new PdfPCell(new Paragraph(DateUtil.format(new Date(), "yyyy- MM- dd"), numfont));
            block2C4.setBorder(Rectangle.NO_BORDER);
            block2C4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            block2.addCell(block2C4);
            block2.setSpacingAfter(paragraphSpacing);
            document.add(block2);


            PdfPTable block3 = new PdfPTable(4);
            block3.setWidthPercentage(100);
            PdfPCell block3C1 = new PdfPCell(new Paragraph());
            block3C1.setBorder(Rectangle.NO_BORDER);
            block3.addCell(block3C1);
            PdfPCell block3C2 = new PdfPCell(new Paragraph());
            block3C2.setBorder(Rectangle.NO_BORDER);
            block3.addCell(block3C2);
            PdfPCell block3C3 = new PdfPCell(new Paragraph("收据编号：", textfont));
            block3C3.setBorder(Rectangle.NO_BORDER);
            block3C3.setHorizontalAlignment(Element.ALIGN_LEFT);
            block3.addCell(block3C3);
            PdfPCell block3C4 = new PdfPCell(new Paragraph("HT2021111215302209", numfont));
            block3C4.setBorder(Rectangle.NO_BORDER);
            block3C4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            block3.addCell(block3C4);
            block3.setSpacingAfter(50f);
            document.add(block3);


            Paragraph block4 = new Paragraph("Billed To：", textfont);
            block4.setAlignment(Element.ALIGN_LEFT);
            document.add(block4);


            Paragraph block5 = new Paragraph("法人姓名张三", textfont);
            block5.setAlignment(Element.ALIGN_LEFT);
            block5.setSpacingBefore(paragraphSpacing);
            document.add(block5);

            Paragraph block6 = new Paragraph("店铺名称XXXX店", textfont);
            block6.setAlignment(Element.ALIGN_LEFT);
            block6.setSpacingBefore(paragraphSpacing);
            document.add(block6);

            Paragraph block7 = new Paragraph();
//            Paragraph block7 = new Paragraph("合同编号：" + "HT022021111802130001", textfont);

            block7.add(new Chunk("合同编号：", textfont));
            block7.add(new Chunk("HT022021111802130001", numfont));
            block7.setAlignment(Element.ALIGN_LEFT);
            block7.setSpacingBefore(paragraphSpacing);
            document.add(block7);

            Paragraph block8 = new Paragraph("合同名称：" + "合同名称XXXXXX合同", textfont);
            block8.setAlignment(Element.ALIGN_LEFT);
            block8.setSpacingBefore(paragraphSpacing);
            block8.setSpacingAfter(paragraphSpacing);
            document.add(block8);

            Paragraph block9 = new Paragraph();
            block9.add(new Chunk(new LineSeparator()));
            document.add(block9);


            PdfPTable block10 = new PdfPTable(2);
            block10.setWidthPercentage(100);
            PdfPCell block10C1 = new PdfPCell(new Phrase("费用名称", textfont));
            block10C1.setHorizontalAlignment(Element.ALIGN_LEFT);
            block10C1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            block10C1.setBorder(0);
            block10.addCell(block10C1);

            PdfPCell block10C2 = new PdfPCell(new Phrase("金额", textfont));
            block10C2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            block10C2.setVerticalAlignment(Element.ALIGN_BOTTOM);
            block10C2.setBorder(0);
            block10.addCell(block10C2);
            block10.setSpacingBefore(5f);
            document.add(block10);

            Paragraph block11 = new Paragraph();
            block11.add(new Chunk(new LineSeparator()));
            document.add(block11);

            PdfPTable block12 = new PdfPTable(3);
            block12.setWidthPercentage(100);
            PdfPCell block12C1 = new PdfPCell(new Phrase("履约保证金", textfont));
            block12C1.setHorizontalAlignment(Element.ALIGN_LEFT);
            block12C1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            block12C1.setBorder(0);
            block12.addCell(block12C1);

            PdfPCell block12C2 = new PdfPCell(new Phrase("/", textfont));
            block12C2.setHorizontalAlignment(Element.ALIGN_CENTER);
            block12C2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            block12C2.setBorder(0);
            block12.addCell(block12C2);

            PdfPCell block12C3 = new PdfPCell(new Phrase("2345.43", textfont));
            block12C3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            block12C3.setVerticalAlignment(Element.ALIGN_BOTTOM);
            block12C3.setBorder(0);
            block12.addCell(block12C3);
            block12.setSpacingAfter(50f);
            document.add(block12);


            PdfPTable block13 = new PdfPTable(4);
            block13.setWidthPercentage(100);
            PdfPCell block13C1 = new PdfPCell(new Paragraph());
            block13C1.setBorder(Rectangle.NO_BORDER);
            block13.addCell(block13C1);
            PdfPCell block13C2 = new PdfPCell(new Paragraph());
            block13C2.setBorder(Rectangle.NO_BORDER);
            block13.addCell(block13C2);
            PdfPCell block13C3 = new PdfPCell(new Paragraph("小计：", textfont));
            block13C3.setBorder(Rectangle.NO_BORDER);
            block13C3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            block13.addCell(block13C3);
            PdfPCell block13C4 = new PdfPCell(new Paragraph("￥" + "2345.43", textfont));
            block13C4.setBorder(Rectangle.NO_BORDER);
            block13C4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            block13.addCell(block13C4);
            block13.setSpacingAfter(70f);
            document.add(block13);

            Paragraph block14 = new Paragraph("公司名称XXXXX公司", titlefont);
            block14.setAlignment(Element.ALIGN_RIGHT);
            block14.setLeading(10f);
            block14.setSpacingAfter(paragraphSpacing);
            document.add(block14);

            PdfPTable block15 = new PdfPTable(3);
            block15.setWidthPercentage(100);
            PdfPCell block15C1 = new PdfPCell(new Paragraph());
            block15C1.setBorder(Rectangle.NO_BORDER);
            block15.addCell(block15C1);
            PdfPCell block15C2 = new PdfPCell(new Paragraph());
            block15C2.setBorder(Rectangle.NO_BORDER);
            block15.addCell(block15C2);
            PdfPCell block15C3 = new PdfPCell(new Paragraph("此处加盖财务专用章", remarkfont));
            block15C3.setBorder(Rectangle.NO_BORDER);
            block15C3.setVerticalAlignment(Element.ALIGN_CENTER);
            block15C3.setHorizontalAlignment(Element.ALIGN_CENTER);
            block15.addCell(block15C3);
            document.add(block15);

            document.addCreationDate();

            if(document != null){
                document.close();
            }
        } catch (IOException e) {
            log.error("PDF文档对象写入到流异常!!", e);
            throw new RuntimeException("PDF文档对象IO流异常!!");
        } catch (DocumentException e) {
            log.error("PDF文档对象写入到流异常!!!", e);
            throw new RuntimeException("PDF文档对象文档流异常!!!");
        } finally {
            if(inputStreamImg != null){
                try {
                    inputStreamImg.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("PDF图片对象流关闭异常!");
                }
            }

            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    log.error("PDF文档对象写入到流异常!!!!", e);
                    throw new RuntimeException("PDF文档对象流关闭异常!");
                }
            }
        }
        result = baos.toByteArray();
        return result;
    }


    public static void main(String[] args) {
        try {
            String filePath = "/Users/admin/Downloads/";
            String fileName = "测试生成" + DateUtil.format(new Date(), "yyyyMMdd-HHmmss") + ".pdf";
            ITextPdfUtil iTextPdfUtil = new ITextPdfUtil();
            byte[] fileBytes = iTextPdfUtil.createPdfByCustom();

            File file = new File(filePath + fileName);
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            out.write(fileBytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

