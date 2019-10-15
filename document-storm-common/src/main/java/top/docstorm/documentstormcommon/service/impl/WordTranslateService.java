package top.docstorm.documentstormcommon.service.impl;

import lombok.Cleanup;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.service.AbstractTransService;
import top.docstorm.documentstormcommon.utils.crawl.GoogleTranslateUtil;
import top.docstorm.documentstormcommon.utils.crawl.MyJsonResolve;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;


/**
 * @Description:
 * @author: passer
 * @version：2019/9/19
 */
@Service("wordTranslateService")
public class WordTranslateService extends AbstractTransService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordTranslateService.class);

    @Value("${googleTranslateUrlPrefix}")
    private String urlPrefix;

    private boolean wordTranslate(String srcPath, String destPath) {
        try {
            String docString = FileUtils.readFileToString(new File(srcPath), "utf-8");
            String tk = "";
            tk = GoogleTranslateUtil.excuteJs(docString);
            String urlString = urlPrefix + tk + "&q=";
            docString = URLEncoder.encode(docString,"utf-8");
            urlString = urlString + docString;

            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
            @Cleanup
            InputStream inStream = conn.getInputStream();
            File jsonFile = new File("json.txt");
            jsonFile.delete();
            FileUtils.copyToFile(inStream, jsonFile);
            String jsonString = FileUtils.readFileToString(new File("json.txt"), "utf-8");
            String[] lines = MyJsonResolve.resolve(jsonString).split("\\],\\[");
            File translateFile = new File(destPath);
            @Cleanup
            FileOutputStream fos = new FileOutputStream(translateFile);
            for(String tmp : lines) {
                tmp = tmp.replaceAll("\\\\n", " ");
                tmp = tmp.substring(0, tmp.length() - 12) + "\n\n";
                System.out.println(tmp);
                fos.write(tmp.getBytes());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(String.format("srcPath: {}, destPath: {}, errorMsg: {}", srcPath, destPath, e.getMessage()));
            return false;
        }
    }

    @Override
    public boolean transCore(String srcPath, String destPath) {
        return wordTranslate(srcPath, destPath);
    }
}
