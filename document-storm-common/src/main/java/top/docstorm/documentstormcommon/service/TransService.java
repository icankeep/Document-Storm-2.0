package top.docstorm.documentstormcommon.service;

import top.docstorm.documentstormcommon.domain.FileInfo;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/19
 */
public interface TransService {
    boolean transCore(String srcPath, String destPath);

    void trans(FileInfo fileInfo);
}
