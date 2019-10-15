package top.docstorm.documentstormcommon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import top.docstorm.documentstormcommon.constant.FileInfoConstants;
import top.docstorm.documentstormcommon.domain.FileInfo;
import top.docstorm.documentstormcommon.mapper.FileInfoDao;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/19
 */
public abstract class AbstractTransService implements TransService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTransService.class);

    @Autowired
    private FileInfoService fileInfoService;

    private Long beginTime;

    private Long endTime;

    @Value("${uploadBasePath}")
    private String uploadBasePath;

    @Autowired
    private FileInfoDao fileInfoDao;

    private void beforeTrans(FileInfo fileInfo) {
        beginTime = System.currentTimeMillis();
        // 还可以做一些转换前的操作
    }

    @Override
    public abstract boolean transCore(String srcPath, String destPath);

    @Override
    public void trans(FileInfo fileInfo) {
        beforeTrans(fileInfo);
        String fileName = uploadBasePath + fileInfoService.generateBeforeTransFileName(fileInfo);
        String transFileName = uploadBasePath + fileInfoService.generateAfterTransFileName(fileInfo);
        boolean transResult = transCore(fileName, transFileName);
        afterTrans(fileInfo, transResult);
    }

    private void afterTrans(FileInfo fileInfo, boolean transResult) {
        endTime = System.currentTimeMillis();
        if (transResult) {
            // 还可以做一些转换后的操作
            FileInfo newFileInfo = new FileInfo();
            newFileInfo.setFileId(fileInfo.getFileId());
            newFileInfo.setFileStatus(FileInfoConstants.FIlE_SUCCESS);
            fileInfoDao.updateByPrimaryKeySelective(newFileInfo);
            LOGGER.info("FileId:" + fileInfo.getFileId() + " 文件转换成功，耗时: " + getTransTime() + "ms");
        } else {
            LOGGER.info(fileInfo.getFileId() + " 文件转换失败");
        }
    }

    private Long getTransTime() {
        return endTime - beginTime;
    }
}
