package grupo2.nogame_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NogameRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NogameRestApplication.class, args);
	}

}
