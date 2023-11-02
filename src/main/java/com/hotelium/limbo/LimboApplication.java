package com.hotelium.limbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hotelium.limbo.*")
public class LimboApplication {
	public static void main(String[] args) {
		SpringApplication.run(LimboApplication.class, args);
	}
}

/*
 * Mentoria de Java - 13/10
 * 
 * @ManyToOne(fetch = FetchType.LAZY)
 * 
 * @JoinColumn(name = "hotel_id")
 * private Hotel hotel;
 * 
 * Entidade Hotel:
 * 
 * @OneToMany(mappedBy = "hotel")
 * 
 * @NotExportable
 * private Collection<Quarto> quartos = new ArrayList<>();
 */