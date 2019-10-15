package top.docstorm.documentstormcommon.service;

import top.docstorm.documentstormcommon.domain.FileInfo;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/17
 */
public interface FileInfoService {
    /**
     * 往数据库插入上传文件信息
     * @param fileInfo
     * @return
     */
    int insertUploadFileInfo(FileInfo fileInfo);

    /**
     * 更新文件状态
     * @param fileId
     * @param fileStatus
     * @return
     */
    int updateFileStatus(int fileId, byte fileStatus);

    /**
     * 根据信息插入数据
     * @param fileName
     * @param fileKey
     * @param opt
     * @return
     */
    FileInfo insertFromUploadInfo(String fileName, String fileKey, String opt, Integer userId);

    String generateFileExt(String opt);

    String generateUploadFileName(String fileName, String newFileName);

    String generateSaveFileName(String fileKey, String opt);

    String generateAfterTransFileName(FileInfo fileInfo);

    String generateBeforeTransFileName(FileInfo fileInfo);
}
