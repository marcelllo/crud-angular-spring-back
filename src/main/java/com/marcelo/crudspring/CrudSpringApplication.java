package com.marcelo.crudspring;

import com.marcelo.crudspring.enums.Category;
import com.marcelo.crudspring.model.Course;
import com.marcelo.crudspring.model.Lesson;
import com.marcelo.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
//			courseRepository.deleteAll();

			for (int i = 0; i < 20; i++) {
				Course c = new Course();
				c.setName("Angular " + i);
				c.setCategory(Category.BACK_END);

				Lesson l1 = new Lesson();
				l1.setName("45: Curso-Aulas: OneToMany");
				l1.setYoutubeLink("Nb4uxLxdvxo");
				l1.setCourse(c);

				c.getLessons().add(l1);

				Lesson l2 = new Lesson();
				l2.setName("46: Curso-Aulas: Listando Aulas");
				l2.setYoutubeLink("ZLpyS4qqEZE");
				l2.setCourse(c);

				c.getLessons().add(l2);

				courseRepository.save(c);
			}
		};
	}
}
