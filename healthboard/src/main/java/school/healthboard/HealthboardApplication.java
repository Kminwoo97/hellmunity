package school.healthboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HealthboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthboardApplication.class, args);
	}

}
