package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosOmdb;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.DadosOmdbService;
import ch.qos.logback.core.util.StringUtil;

import java.util.List;
import java.util.Scanner;

public class TelaEntrada {

    private final Scanner leitura = new Scanner(System.in);

    public void exibeMenu(){
        String nomeSerie;
        while (true) {
            System.out.println("\nDigite o nome da série para busca : (0 p/ finalizar o programa)");
            nomeSerie = leitura.nextLine();

            if (nomeSerie.equalsIgnoreCase("0")) return;

            if(StringUtil.notNullNorEmpty(nomeSerie)) {
                DadosOmdb omdb = DadosOmdbService.inicie(nomeSerie);

                if(omdb == null) leitura.nextLine();

                this.exibeSubMenu(omdb);
            }
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

            TelaSaida.imprimeDadosSerie(omdb.getDadosSerie());

            System.out.println(menuOpcao);
            opcao = leitura.nextInt();
            switch (opcao) {
                case 0 -> leitura.nextLine();
                case 1 -> TelaSaida.imprimeDadosSerie(omdb.getDadosSerie());
                case 2 -> TelaSaida.imprimeDadosTemporada(omdb.getTemporadas());
                case 3 -> TelaSaida.imprimeTodosEpisodiosDaSeriePorTemporada(omdb.getTemporadas());
                case 4 -> TelaSaida.imprimeEpisodiosPorTemporada(omdb.getEpisodios());
                case 5 -> TelaSaida.imprimeTop5EpisodiosOld(omdb.getTemporadas());
                case 6 -> TelaSaida.imprimeTop5Episodios(omdb.getTemporadas());
                case 7 -> this.exibeSubMenuSelecioneData(omdb.getEpisodios());
                default -> System.out.println("Opção Inválida");
            }
        }

    }

    private void exibeSubMenuSelecioneData(List<Episodio> episodios) {

        System.out.println("\nA partir de que ano você deseja visualizar os episódios? ");
        var ano = leitura.nextInt();
        //leitura.nextLine();

        TelaSaida.imprimeEpisodiosAPartirData(ano, episodios);
    }
}
