package com.ecomm.services;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecomm.models.UserAccount;
import com.ecomm.repositories.OrderRepository;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

@Service
public class UserService {
	
	Logger log = LogManager.getLogger(UserService.class);
    
	@Value("${user_ms_name}")
	private  String user_ms_name;

    public UserAccount getUserAccountDetails(Long userId) {
			
		HttpClient httpClient = HttpClient.create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 5 seconds
				  .responseTimeout(Duration.ofMillis(5000))
				  .doOnConnected(conn -> 
				    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
				      .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
		
		UserAccount userAccount = getUserAccount(httpClient, userId);
			
		return userAccount;
	}
    
    private UserAccount getUserAccount(HttpClient httpClient, Long userId) {
    	WebClient webClient = WebClient.builder()
				.baseUrl(user_ms_name)
				.defaultCookie("cookieKey", "cookieValue")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				.defaultUriVariables(Collections.singletonMap("url", user_ms_name))
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
				
		return findByUserId(userId, webClient);
	}

	public UserAccount findByUserId(Long userId, WebClient webClient)
    {
    	return webClient.get()
    		.uri("/user/" + userId)
    		.retrieve()
    		/*.onStatus(httpStatus -&gt; HttpStatus.NOT_FOUND.equals(httpStatus),
                    clientResponse -&gt; Mono.empty())
               .onStatus(HttpStatus::is4xxClientError,
                      error -> Mono.error(new RuntimeException("API not found")))
               .onStatus(HttpStatus::is5xxServerError,
                      error -> Mono.error(new RuntimeException("Server is not responding")))*/
    		.bodyToMono(UserAccount.class)
    		//.retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(10)))
    		.block();
    }
    
}
