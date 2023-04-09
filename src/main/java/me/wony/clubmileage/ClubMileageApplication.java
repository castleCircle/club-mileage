package me.wony.clubmileage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClubMileageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubMileageApplication.class, args);
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em){
		return new JPAQueryFactory(em);
	}

}
