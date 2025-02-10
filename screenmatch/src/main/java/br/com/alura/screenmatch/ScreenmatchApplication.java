package br.com.alura.screenmatch;

import br.com.alura.screenmatch.dto.ChaveOmdb;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.DadosConverter;
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

		var uri = "https://www.omdbapi.com/?t=";
		var busca = "gilmore+girls"; //&Season=1
		var endereco = uri+busca+"&apikey="+ ChaveOmdb.getChave();

		var consumoApi = new ConsumoApi();

		var json = consumoApi.obterDados(endereco);
		System.out.println(json);

		DadosConverter conversor = new DadosConverter();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		//json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(json);


	}
}
