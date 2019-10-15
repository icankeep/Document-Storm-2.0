package top.docstorm.documentstormcommon.service.impl;

/**
* @Description: Markdown文件转为HTML文件<p>
* @author: passer<p>
* @version：2019年5月18日 下午1:42:17<p>
*/

import lombok.Cleanup;
import org.apache.commons.io.FilenameUtils;
import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.service.AbstractTransService;

import java.io.*;
@Service("markdownToHTMLService")
public class MarkdownToHTMLService extends AbstractTransService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarkdownToHTMLService.class);

	private static void startDocument(StringBuilder buf, String mdFileName) throws IOException {
		buf.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"" + "\n"
				+ "\"http://www.w3.org/TR/html4/loose.dtd\">\n");
		buf.append("<html><head>");
		buf.append("<title>").append(FilenameUtils.getName(mdFileName)).append("</title>\n");
		buf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"UTF-8\">\n");
		buf.append("</head>\n");
		buf.append("<body>\n");
	}

	private static void endDocument(StringBuilder buf) {
		buf.append("</body></html>");
	}

	private boolean transToHtml(String srcPath, String destPath) {
		try {
			// 缓冲html文件
			StringBuilder buf = new StringBuilder();
			// html头部
			startDocument(buf, srcPath);

			@Cleanup
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(srcPath)), "UTF-8"));
			@Cleanup
			FileOutputStream fos = new FileOutputStream(new File(destPath), true);

			// 读入markdown文件
			String line = null;
			String mdContent = "";
			while ((line = br.readLine()) != null) {
				mdContent += line + "\r\n";
			}

			// markdown文件转换
			PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
			buf.append(pdp.markdownToHtml(mdContent));

			// 加入html尾部
			endDocument(buf);
			fos.write(buf.toString().getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(String.format("srcPath: {}, destPath: {}, errorMsg: {}", srcPath, destPath, e.getMessage()));
			return false;
		}
	}

	@Override
	public boolean transCore(String srcPath, String destPath) {
		return transToHtml(srcPath, destPath);
	}
}
