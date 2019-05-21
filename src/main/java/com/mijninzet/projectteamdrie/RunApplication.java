package com.mijninzet.projectteamdrie;

import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class RunApplication {
private static UserSingleton userSingleton;

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
        userSingleton.getInstance();
        System.out.println("Singleton.getInstance() is aangeroepen in de main");

    }

}
