import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.docstorm.documentstormcommon.service.AbstractTransService;
import top.docstorm.documentstormcommon.utils.CommonUtils;

/**
* @Description: 图像转PDF转换类<p>
* @author: passer<p>
* @version：2019年5月19日 下午10:22:15<p>
*/
public class ImageToPDFService extends AbstractTransService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageToPDFService.class);
	/**
	 * 实现图片到PDF文件的转换
	 * @param srcPath		图片的路径
	 * @param destPath		转换后PDF的路径，后缀必须为.pdf
	 */
	public void transToPDF(String srcPath, String destPath) {
		PDDocument doc = null;
		PDPageContentStream contents = null;
		try {
			doc = new PDDocument();
			PDPage page = new PDPage();
			doc.addPage(page);

			// createFromFile is the easiest way with an image file
			// if you already have the image in a BufferedImage,
			// call LosslessFactory.createFromImage() instead
			PDImageXObject pdImage = PDImageXObject.createFromFile(srcPath, doc);
			contents = new PDPageContentStream(doc, page);
			// draw the image at full size at (x=20, y=20)
			contents.drawImage(pdImage, 0, 0);
			// to draw the image at half size at (x=20, y=20) use
			// contents.drawImage(pdImage, 20, 20, pdImage.getWidth() / 2,
			// pdImage.getHeight() / 2);
			doc.save(destPath);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(String.format("srcPath: {}, destPath: {}, errorMsg: {}", srcPath, destPath, e.getMessage()));
		} finally {
			CommonUtils.close(doc);
			CommonUtils.close(contents);
		}
	}

	@Override
	public void transCore(String srcPath, String destPath) {
		transToPDF(srcPath, destPath);
	}
}
