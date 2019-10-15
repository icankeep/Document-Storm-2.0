package top.docstorm.documentstormcommon.service.impl;

/**
 * @Description: HTML转PDF的转换类<p>
 * @author: passer<p>
 * @version：2019年5月18日 上午11:51:00<p>
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.service.AbstractTransService;

import java.io.File;

@Service("htmlToPDFService")
public class HTMLToPDFService extends AbstractTransService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HTMLToPDFService.class);

    /**
     * HTML转PDF
     * @param srcPath HTML路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath PDF保存路径
     */
    private boolean transToPDF(String srcPath, String destPath) {
        File file = new File(destPath);
        // 拼凑命令，调用底层wkhtmltopdf
        StringBuilder cmd = new StringBuilder();
        cmd.append("wkhtmltopdf ");
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);
        try {
            // 调用子进程处理命令
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            proc.waitFor();
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
