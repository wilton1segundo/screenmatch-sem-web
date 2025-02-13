package br.com.alura.screenmatch;

import br.com.alura.screenmatch.principal.TelaEntrada;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		TelaEntrada console = new TelaEntrada();
		console.exibeMenu();
		System.out.println("Obrigado por usar o Screen Match!");
	}

}
