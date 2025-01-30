package it.unipi.CardsGallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardsGalleryApplication {

	public static void main(String[] args) {

		MongoDb.connect();
		SpringApplication.run(CardsGalleryApplication.class, args);

	}

}
