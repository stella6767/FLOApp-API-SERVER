package com.kang.FloApiServer;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kang.FloApiServer.domain.song.CategoryType;
import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.song.SongRepository;

@SpringBootApplication
public class FloApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloApiServerApplication.class, args);
	}
}
