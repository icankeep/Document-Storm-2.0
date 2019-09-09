package top.docstorm.documentstormcommon.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import top.docstorm.documentstormcommon.DocumentStormCommonApplication;
import top.docstorm.documentstormcommon.domain.FileInfo;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DocumentStormCommonApplication.class)
public class FileInfoDaoTest {
    @Autowired
    private FileInfoDao fileInfoDao;
    @Test
    public void testSelectByPrimaryKey() {
        FileInfo fileInfo = fileInfoDao.selectByPrimaryKey(1);
        Assert.notNull(fileInfo);
        System.out.println(fileInfo);
    }
}
