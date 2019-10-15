package top.docstorm.documentstormcommon.service.impl;


import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import lombok.Cleanup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.service.AbstractTransService;
import top.docstorm.documentstormcommon.utils.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: passer
 * @Date: 19-6-15 下午8:18
 * @Version 1.0
 */
@Service("wordToPDFService")
public class WordToPDFService extends AbstractTransService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordToPDFService.class);

    private static boolean getLicense() {
        boolean result = false;
        try {
            @Cleanup
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean transToPDF(String srcPath, String destPath) {
        System.out.println(srcPath);
        System.out.println(destPath);
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            // 新建一个空白pdf文档
            File file = new File(destPath);
            fos = new FileOutputStream(file);
            // Address是将要被转化的word文档
            Document doc = new Document(srcPath);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(fos, SaveFormat.PDF);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(String.format("srcPath: {}, destPath: {}, errorMsg: {}", srcPath, destPath, e.getMessage()));
            return false;
        } finally {
            CommonUtils.close(fos);
        }
    }

    @Override
    public boolean transCore(String srcPath, String destPath) {
        return transToPDF(srcPath, destPath);
    }
}
