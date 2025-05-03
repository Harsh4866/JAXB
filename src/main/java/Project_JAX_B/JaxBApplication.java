package Project_JAX_B;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JaxBApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run
				= SpringApplication.run(JaxBApplication.class, args);
	}

}
