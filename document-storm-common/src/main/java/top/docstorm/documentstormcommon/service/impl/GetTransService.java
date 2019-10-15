package top.docstorm.documentstormcommon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.constant.FileInfoConstants;
import top.docstorm.documentstormcommon.domain.FileInfo;
import top.docstorm.documentstormcommon.exception.LogicException;
import top.docstorm.documentstormcommon.service.TransService;
import top.docstorm.documentstormcommon.service.impl.HTMLToPDFService;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/19
 */
@Service
public class GetTransService {
    @Resource(name = "htmlToPDFService")
    private TransService htmlToPDFService;

    @Resource(name = "markdownToHTMLService")
    private TransService markdownToHTMLService;

    @Resource(name = "markdownToPDFService")
    private TransService markdownToPDFService;

    @Resource(name = "pdfToWordService")
    private TransService pdfToWordService;

    @Resource(name = "wordToPDFService")
    private TransService wordToPDFService;

    @Resource(name = "wordTranslateService")
    private TransService wordTranslateService;

    public TransService getTransService(Byte fileTransType) {
        if (fileTransType == null) {
            throw new LogicException("fileTransType不能为空");
        } else if (FileInfoConstants.WORD_TO_PDF.equals(fileTransType)) {
            return wordToPDFService;
        } else if (FileInfoConstants.PDF_TO_WORD.equals(fileTransType)) {
            return pdfToWordService;
        } else if (FileInfoConstants.MARKDOWN_TO_PDF.equals(fileTransType)) {
            return markdownToPDFService;
        } else if (FileInfoConstants.MARKDOWN_TO_HTML.equals(fileTransType)) {
            return markdownToHTMLService;
        } else if (FileInfoConstants.WORD_TRANSLATE.equals(fileTransType)) {
            return wordTranslateService;
        } else if (FileInfoConstants.HTML_TO_PDF.equals(fileTransType)) {
            return htmlToPDFService;
        } else {
            throw new LogicException("没有提供对应的服务！");
        }
    }
}
