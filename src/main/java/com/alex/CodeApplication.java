package com.alex;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName CodeApplication
 * @Description TODO
 * @Author Alex
 * @CreateDate 2019/9/6 16:20
 * @Version 1.0
 */
@SpringBootApplication()
public class CodeApplication {

    private final static Logger logger = LoggerFactory.getLogger(CodeApplication.class);

    public static void main(String[] args) {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://127.0.0.1:3306/codedb?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT", "root", "123456").load();

        // Start the migration
        flyway.migrate();
        SpringApplication.run(CodeApplication.class, args);
        logger.info(CodeApplication.class.getSimpleName() + " is success!");
    }
}
