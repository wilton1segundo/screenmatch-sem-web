package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosOmdb;
import br.com.alura.screenmatch.service.DadosOmdbService;

import java.util.Scanner;

public class TelaEntrada {

    private final Scanner leitura = new Scanner(System.in);

    public void exibeMenu(){
        while (true) {
            System.out.println("\nDigite o nome da série para busca : (digite sair para finalizar o programa");
            var nomeSerie = leitura.nextLine();

            if (nomeSerie.equalsIgnoreCase("sair")) return;

            DadosOmdb omdb = DadosOmdbService.inicie(nomeSerie);

            this.exibeSubMenu(omdb);
        }
    }


    public void exibeSubMenu(DadosOmdb omdb) {

        if (omdb == null) return;

        var opcao = -1;
        while(opcao != 0) {
            String menuOpcao = """
                    \nEscolha uma opção:
                    0 - Volta menu anterior
                    1 - Imprime dados da série
                    2 - imprime dados da Temporada
                    3 - Imprime Todos Episodios Da Serie Por Temporada
                    4 - Imprime Episodios Por Temporada (versão 1)
                    5 - Imprime Top 5 Episodios Old
                    6 - Imprime Top 5 Episodios
                    7 - imprime epísodios a partir de uma Data
                    """;
            System.out.println(menuOpcao);
            opcao = leitura.nextInt();
            switch (opcao) {
                case 0 -> System.out.println("\nSaindo... ");
                case 1 -> TelaSaida.imprimeDadosSerie(omdb.getDadosSerie());
                case 2 -> TelaSaida.imprimeDadosTemporada(omdb.getTemporadas());
                case 3 -> TelaSaida.imprimeTodosEpisodiosDaSeriePorTemporada(omdb.getTemporadas());
                case 4 -> TelaSaida.imprimeEpisodiosPorTemporada(omdb.getEpisodios());
                case 5 -> TelaSaida.imprimeTop5EpisodiosOld(omdb.getTemporadas());
                case 6 -> TelaSaida.imprimeTop5Episodios(omdb.getTemporadas());
                case 7 -> TelaSaida.imprimeEpisodiosAPartirData(leitura, omdb.getEpisodios());
                default -> System.out.println("Opção Inválida");
            }
        }

    }
}
