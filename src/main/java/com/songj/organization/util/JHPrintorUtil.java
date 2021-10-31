//package com.songj.organization.util;
//
//import org.apache.log4j.Logger;
//
//import java.awt.print.PrinterJob;
//import java.io.File;
//
///**
// * @Classname JHPrintorUtil
// * @Description 打印实现类
// * @Date 2021/10/29 下午4:13
// * @Created by admin
// */
//public final class JHPrintor {
//
//    /** 日志记录对象 */
//    private static Logger log = Logger.getLogger(JHPrintor.class);
//
//    /** 私有构造器 */
//    private JHPrintor() {
//
//    }
//
//
//    /**
//     * 打印pdf文件
//     *
//     * @param file 需打印的pdf文件
//     */
//    public static void print(File file) {
//
//        try {
//
//            PDDocument document = PDDocument.load(file);
//            // 加载成打印文件
//            PDFPrintable printable = new PDFPrintable(document,Scaling.ACTUAL_SIZE);
//            PrinterJob job = PrinterJob.getPrinterJob();
//            job.setPrintable(printable);
//            job.defaultPage();
//            job.print();
//        } catch (InvalidPasswordException e) {
//            log.error("打印异常：文档初始化密码失败!");
//            PromptUtil.error("初始化文档密码异常,打印失败!");
//            e.printStackTrace();
//        } catch (IOException e) {
//            log.error("打印异常！");
//            PromptUtil.error("打印异常,请关闭后重试!");
//            e.printStackTrace();
//        } catch (PrinterException e) {
//            log.error("未正常连接打印机");
//            PromptUtil.error("请检查打印机是否正常连接!");
//            e.printStackTrace();
//        }
//    }
//
//}
//
