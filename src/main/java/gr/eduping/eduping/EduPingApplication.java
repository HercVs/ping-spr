package gr.eduping.eduping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EduPingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduPingApplication.class, args);
    }

}
