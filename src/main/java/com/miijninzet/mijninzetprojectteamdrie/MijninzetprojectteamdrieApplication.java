package com.miijninzet.mijninzetprojectteamdrie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class MijninzetprojectteamdrieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MijninzetprojectteamdrieApplication.class, args);
    }

}
