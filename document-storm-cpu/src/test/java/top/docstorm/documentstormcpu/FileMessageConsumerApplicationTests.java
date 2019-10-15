package top.docstorm.documentstormcpu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.docstorm.documentstormcommon.service.impl.WordToPDFService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileMessageConsumerApplicationTests {

    @Autowired
    private WordToPDFService wordToPDFService;

    @Test
    public void contextLoads() {
        wordToPDFService.transCore("D:/Document_Storm_2.0_upload/cb1650fb-a339-498d-9963-4d0172a7f08c.docx", "D:/Document_Storm_2.0_upload/cb1650fb-a339-498d-9963-4d0172a7f08c.pdf");
    }

}
