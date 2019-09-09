package top.docstorm.documentstormcpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FileMessageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileMessageConsumerApplication.class, args);
    }

}
