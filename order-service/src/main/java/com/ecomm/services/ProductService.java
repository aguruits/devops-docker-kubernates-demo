package com.ecomm.services;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecomm.models.Product;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

@Service
public class ProductService {
	
	Logger log = LogManager.getLogger(ProductService.class);
    
	@Value("${product_ms_name}")
	private  String product_ms_name;

    public Product getProductDetails(Long productId) {
			
		HttpClient httpClient = HttpClient.create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 5 seconds
				  .responseTimeout(Duration.ofMillis(5000))
				  .doOnConnected(conn -> 
				    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
				      .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
		
		Product product = getProductDetails(httpClient, productId);
			
		return product;
	}
    
    private Product getProductDetails(HttpClient httpClient, Long productId) {
    	WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:8082")
				.defaultCookie("cookieKey", "cookieValue")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				.defaultUriVariables(Collections.singletonMap("url", product_ms_name))
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
				
		return findByProductId(productId, webClient);
	}

	public Product findByProductId(Long productId, WebClient webClient)
    {
    	return webClient.get()
    		.uri("/product/" + productId)
    		.retrieve()
    		/*.onStatus(httpStatus -&gt; HttpStatus.NOT_FOUND.equals(httpStatus),
                    clientResponse -&gt; Mono.empty())
               .onStatus(HttpStatus::is4xxClientError,
                      error -> Mono.error(new RuntimeException("API not found")))
               .onStatus(HttpStatus::is5xxServerError,
                      error -> Mono.error(new RuntimeException("Server is not responding")))*/
    		.bodyToMono(Product.class)
    		.retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(10)))
    		.block();
    }
    
}
