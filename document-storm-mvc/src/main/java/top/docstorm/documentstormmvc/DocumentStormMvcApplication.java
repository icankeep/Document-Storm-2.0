package top.docstorm.documentstormmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"top.docstorm"})
public class DocumentStormMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentStormMvcApplication.class, args);
    }

}
