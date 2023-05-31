package com.example.secureclient;

import com.example.secureclient.clients.SecureServiceClient;
import com.example.secureclient.clients.UnsecureServiceClient;
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
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class SecureClientApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(SecureClientApplication.class);


	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

	@Autowired
	AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager;

	public static void main(String[] args) {
		SpringApplication.run(SecureClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
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
			logger.error("Error call hello", e);
		}
	}

	@Autowired
	public SecureServiceClient secureServiceClient;

	@Autowired
	public UnsecureServiceClient unsecureServiceClient;

	@Scheduled(fixedDelayString = "PT10S")
	public void periodicTask() {
		logger.info("start task");
		String resp = secureServiceClient.getHello("101");
		logger.info("SECURE CLIENT get resp: {}", resp);

		String resp2 = unsecureServiceClient.hello();
		logger.info("UNSECURE CLIENT get resp: {}", resp2);

	}
}
