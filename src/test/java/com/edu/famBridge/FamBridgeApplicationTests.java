package com.edu.famBridge;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class FamBridgeApplicationTests {

	@Test
	void contextLoads() {
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
