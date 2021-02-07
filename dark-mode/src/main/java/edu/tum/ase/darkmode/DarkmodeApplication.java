package edu.tum.ase.darkmode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableOAuth2Client
@EnableResourceServer
public class DarkmodeApplication {
	private boolean isDarkModeOn = false;

	public static void main(String[] args) {
		SpringApplication.run(DarkmodeApplication.class, args);
	}

	@RequestMapping(path = "/dark-mode/toggle", method = RequestMethod.GET)
	public void toggleDarkMode() throws InterruptedException {
		isDarkModeOn = !isDarkModeOn;
		Thread.sleep(3000);
	}

	@RequestMapping(path = "/dark-mode", method = RequestMethod.GET)
	public boolean getDarkModeStatus() {
		return isDarkModeOn;
	}
}