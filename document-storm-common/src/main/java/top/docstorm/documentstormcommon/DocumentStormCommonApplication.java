package top.docstorm.documentstormcommon;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = "top.docstorm.documentstormcommon.mapper")
public class DocumentStormCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocumentStormCommonApplication.class, args);
    }
}
