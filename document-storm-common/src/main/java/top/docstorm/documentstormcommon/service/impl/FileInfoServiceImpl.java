package top.docstorm.documentstormcommon.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.parboiled.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.constant.FileInfoConstants;
import top.docstorm.documentstormcommon.domain.FileInfo;
import top.docstorm.documentstormcommon.domain.FileInfoExample;
import top.docstorm.documentstormcommon.exception.LogicException;
import top.docstorm.documentstormcommon.mapper.FileInfoDao;
import top.docstorm.documentstormcommon.service.FileInfoService;

import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/17
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;

    @Override
    public int insertUploadFileInfo(FileInfo fileInfo) {
        return fileInfoDao.insert(fileInfo);
    }

    @Override
    public int updateFileStatus(int fileId, byte fileStatus) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileStatus(fileStatus);
        FileInfoExample fileInfoExample = new FileInfoExample();
        fileInfoExample.createCriteria().andFileIdEqualTo(fileId);
        return fileInfoDao.updateByExample(fileInfo, fileInfoExample);
    }

    @Override
    public FileInfo insertFromUploadInfo(String fileName, String fileKey, String opt, Integer userId) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileKey(fileKey);
        fileInfo.setFileName(fileName);
        fileInfo.setUpdateLastTime(new Date());
        fileInfo.setCreateTime(new Date());
        fileInfo.setFileStatus(Byte.valueOf("0"));
        fileInfo.setFileFormatChangeType(FileInfoConstants.OPT_INT_MAP.get(opt));
        fileInfo.setUserId(userId);
        insertUploadFileInfo(fileInfo);
        return fileInfo;
    }

    @Override
    public String generateFileExt(String opt) {
        Map<String, String> suffixMap = FileInfoConstants.OPT_BEFORE_TRANS_SUFFIX_MAP;
        if (!suffixMap.containsKey(opt)) {
            throw new LogicException("非法的转换操作，请通过网页选择正确的转换操作！");
        }
        return suffixMap.get(opt);
    }

    @Override
    public String generateUploadFileName(String fileName, String newFileName) {
        String newFileBaseName = FilenameUtils.getBaseName(newFileName);
        if (!StringUtils.isEmpty(newFileBaseName.trim())) {
            return newFileBaseName;
        }
        newFileBaseName = FilenameUtils.getBaseName(fileName);
        if (!StringUtils.isEmpty(newFileBaseName.trim())) {
            return newFileBaseName;
        }
        throw new LogicException("文件名不能为空！");
    }

    @Override
    public String generateSaveFileName(String fileKey, String opt) {
        String ext = generateFileExt(opt);
        return fileKey + "." + ext;
    }

    @Override
    public String generateAfterTransFileName(FileInfo fileInfo) {
        Map<Byte, String> map = FileInfoConstants.OPT_INT_AFTER_TRANS_SUFFIX_MAP;
        if (!map.containsKey(fileInfo.getFileFormatChangeType())) {
            throw new LogicException("没有对应的后缀名！");
        }
        String ext = map.get(fileInfo.getFileFormatChangeType());
        return fileInfo.getFileKey() + "." + ext;
    }

    @Override
    public String generateBeforeTransFileName(FileInfo fileInfo) {
        Map<Byte, String> map = FileInfoConstants.OPT_INT_BEFORE_TRANS_SUFFIX_MAP;
        if (!map.containsKey(fileInfo.getFileFormatChangeType())) {
            throw new LogicException("没有对应的后缀名！");
        }
        String ext = map.get(fileInfo.getFileFormatChangeType());
        return fileInfo.getFileKey() + "." + ext;
    }
}
