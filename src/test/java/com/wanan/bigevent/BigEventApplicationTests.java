package com.wanan.bigevent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BigEventApplicationTests {

	@Value("${spring.endpoint}")
	private static String endpoint;
	@Value("${spring.accessKeyId}")
	private static String accessKeyId;
	@Value("${spring.secretAccessKey}")
	private static String secretAccessKey;
	@Value("${spring.bucketName}")
	private static String bucketName;

	@Test
	void contextLoads() {
		System.out.println(endpoint);
	}

}
