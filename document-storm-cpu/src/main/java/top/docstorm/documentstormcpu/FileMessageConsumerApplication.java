package top.docstorm.documentstormcpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import top.docstorm.documentstormcommon.config.ActiveMqConfig;

@SpringBootApplication(scanBasePackages = "top.docstorm")
@Import(ActiveMqConfig.class)
@EnableJms
public class FileMessageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileMessageConsumerApplication.class, args);
    }

}
