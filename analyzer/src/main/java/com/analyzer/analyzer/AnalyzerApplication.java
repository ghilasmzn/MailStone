package com.analyzer.analyzer;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.analyzer.analyzer.controller.mail.ReceiveMail;

@SpringBootApplication
public class AnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyzerApplication.class, args);

		try {
			new ReceiveMail().receiveEmail();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}

}
