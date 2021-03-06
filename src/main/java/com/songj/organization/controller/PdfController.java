package com.songj.organization.controller;

import com.songj.organization.util.pdf.itext.ITextPdfUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Classname PdfController
 * @Description TODO
 * @Date 2021/10/28 下午6:32
 * @Created by admin
 */
@RestController
@RequestMapping(value = "/pdf")
@Api(value = "PdfController", description = "pdf相关Api")
public class PdfController {




    @GetMapping("/test")
    public String test(@RequestParam(value = "pdfFilePath") String pdfFilePath){
        return pdfFilePath;
    }


    @GetMapping("/createPdfDemo")
    public String createPdfDemo(@RequestParam(value = "pdfFilePath") String pdfFilePath
            , @RequestParam(value = "pdfFileName")  String pdfFileName
            , HttpServletResponse response){

        return ITextPdfUtil.createPdfDemo(pdfFilePath, pdfFileName);

    }

}
