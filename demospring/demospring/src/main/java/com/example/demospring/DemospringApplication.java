package com.example.demospring;

import com.example.demospring.model.Author;
import com.example.demospring.model.Book;
import com.example.demospring.repository.AuthorRepository;
import com.example.demospring.repository.BookRepository;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


import java.util.List;

@SpringBootApplication
public class DemospringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemospringApplication.class, args);
	}
	@Bean
	ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository){
		return args -> {
			Author shabarish = authorRepository.save(new Author(null, "puli shabarish"));
			Author himani = authorRepository.save(new Author(null, "himani arebelly"));
			Author abhi = authorRepository.save(new Author(null, "abhinav"));
			bookRepository.saveAll(List.of(
					new Book(1L,"React", "self" ,shabarish),
					new Book(2L,"cloud","self",himani),
					new Book(3L, "aws","self",abhi)
			));
		};
	}

}
