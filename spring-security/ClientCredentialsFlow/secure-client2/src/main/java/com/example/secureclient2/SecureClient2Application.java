package com.example.secureclient2;

import com.example.secureclient2.clients.SecureServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class SecureClient2Application implements CommandLineRunner {


	Logger logger = LoggerFactory.getLogger(SecureClient2Application.class);
	public static void main(String[] args) {
		SpringApplication.run(SecureClient2Application.class, args);
	}


	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

	@Autowired
	AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager;

	@Override
	public void run(String... args) throws Exception {
		try {
			ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("demo-client");
			logger.info("found: {}", clientRegistration);
			logger.info("found {}", oAuth2AuthorizedClientService);

			OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("demo-client")
					.principal("Demo Service")
					.build();

			OAuth2AuthorizedClient authorizedClient = this.authorizedClientServiceAndManager.authorize(authorizeRequest);

			OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
			logger.info("accessToken: {}", accessToken.getTokenValue());


			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + accessToken.getTokenValue());
			headers.add("x-client-id", "12345");
			HttpEntity request = new HttpEntity(headers);

			// Make the actual HTTP GET request
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(
					"http://localhost:8080/hello",
					HttpMethod.GET,
					request,
					String.class
			);

			String result = response.getBody();
			logger.info("Reply = " + result);
		} catch (Exception e) {
			logger.error("Error: ",e);
		}
	}


	@Autowired
	private SecureServiceClient secureServiceClient;
	@Scheduled(fixedRate = 10000L)
	public void periodicRun() {
		logger.info("Running...");
		String resp = secureServiceClient.getHello("100");
		logger.info("response: {}", resp);
	}
}
