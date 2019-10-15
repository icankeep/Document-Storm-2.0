package top.docstorm.documentstormcommon.service.impl;

import com.qkyrie.markdown2pdf.Markdown2PdfConverter;
import com.qkyrie.markdown2pdf.internal.writing.SimpleFileMarkdown2PdfWriter;
import org.parboiled.common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.service.AbstractTransService;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
* @Description Markdown文件转成PDF<p>
* link:https://github.com/Qkyrie/Markdown2Pdf<p>
* @author passer<p>
* @version 2019年5月18日 下午3:49:44<p>
*/
@Service("markdownToPDFService")
public class MarkdownToPDFService extends AbstractTransService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarkdownToPDFService.class);
	/**
	 * Our API has been implemented in such a way, that you can use it as a oneliner.
	 * The above example can be rewritten, as shown in the this example
	 */
	private boolean transToPDF(String srcPath, String destPath) {
		try {
			// 读取markdown文件
			File mdFile = new File(srcPath);
			byte[] buf = FileUtils.readAllBytes(mdFile);

			File pdfFile = new File(destPath);

			// 转换格式到指定路径
			Markdown2PdfConverter.newConverter().readFrom(() -> {
				try {
					return new String(buf, "utf8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return destPath;
			}).writeTo(new SimpleFileMarkdown2PdfWriter(pdfFile)).doIt();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(String.format("srcPath: {}, destPath: {}, errorMsg: {}", srcPath, destPath, e.getMessage()));
			return false;
		}
	}

	@Override
	public boolean transCore(String srcPath, String destPath) {
		return transToPDF(srcPath, destPath);
	}
}
