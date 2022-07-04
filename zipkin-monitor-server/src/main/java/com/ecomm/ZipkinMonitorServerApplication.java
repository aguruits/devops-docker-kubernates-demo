package com.ecomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinMonitorServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinMonitorServerApplication.class, args);
	}

}
