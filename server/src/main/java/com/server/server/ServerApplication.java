package com.server.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.server.server.controller.question.IQuestion;
import com.server.server.utils.Watcher;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		try {
			System.out.println(new File(IQuestion.FOLDER_QUESTION).exists());
			new Watcher(Paths.get(IQuestion.FOLDER_QUESTION)).processEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
