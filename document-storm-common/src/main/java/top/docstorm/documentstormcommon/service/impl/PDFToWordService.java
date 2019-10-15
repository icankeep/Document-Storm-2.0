package top.docstorm.documentstormcommon.service.impl;

import lombok.Cleanup;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.service.AbstractTransService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
* @Description: PDF转Word<p>
* @author: passer<p>
* @version：2019年5月19日 下午9:46:12<p>
*/
@Service("pdfToWordService")
public class PDFToWordService extends AbstractTransService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PDFToWordService.class);
	/**
	 * 
	* <p>Description: PDF -- > DOC 文件转换 </p>
	* @param srcPath	文件绝对路径名 </p>
	* @return				返回修改之后文件的绝对路径 </p>	
	 */
	private boolean transToWord(String srcPath, String destPath) {
		try {
			@Cleanup
			PDDocument doc = PDDocument.load(new File(srcPath));
			// 获取总页数
			int pagenumber = doc.getNumberOfPages();
			@Cleanup
			FileOutputStream fos = new FileOutputStream(destPath);
			@Cleanup
			Writer writer = new OutputStreamWriter(fos, "UTF-8");// 文件按字节读取，然后按照UTF-8的格式编码显示
			PDFTextStripper stripper = new PDFTextStripper();
			// 排序
			stripper.setSortByPosition(true);
			// 设置转换的开始页
			stripper.setStartPage(1);
			// 设置转换的结束页
			stripper.setEndPage(pagenumber);
			stripper.writeText(doc, writer);
			return true;
		} catch (Exception e) {
			LOGGER.error(String.format("srcPath: {}, destPath: {}, errorMsg: {}", srcPath, destPath, e.getMessage()));
			return false;
		}
	}

	@Override
	public boolean transCore(String srcPath, String destPath) {
		return transToWord(srcPath, destPath);
	}
}
