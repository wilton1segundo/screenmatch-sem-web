package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class TelaSaida {

    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void imprimeDadosSerie(DadosSerie dados) {
        System.out.println("\nDados da Série: ");
        System.out.println(dados);
    }

    public static void imprimeDadosTemporada(List<DadosTemporada> temporadas) {
        System.out.println("\nDados Temporada");
        temporadas.forEach(System.out::println);
    }

    public static void imprimeTodosEpisodiosDaSeriePorTemporada(List<DadosTemporada> temporadas) {

        //for(int i = 0; i < dados.totalTemporadas(); i++){
        //     List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
        //     for(int j = 0; j< episodiosTemporada.size(); j++){
        //         System.out.println(episodiosTemporada.get(j).titulo());
        //     }
        // }

        System.out.println("\nEpisódios da série por temporada");
        temporadas.forEach(t -> {
            System.out.println("\nTemporada " + t.numero());
            t.episodios().forEach(e -> System.out.println(
                    e.numero() + " " +
                            e.titulo() + " " +
                            e.dataLancamento()));
        });

    }

    public static void imprimeTop5EpisodiosOld(List<DadosTemporada> temporadas){

        System.out.println("\nTop 5 episódios com Dados Episódio");

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .toList();

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);




    }

    public static void imprimeTop5Episodios(List<DadosTemporada> temporadas){

        System.out.println("\nTop 5 episódios com a classe Episódio");

        List<Episodio> episodios =  temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                        .map(d -> new Episodio(t.numero(), d))
                )
                .toList();

        episodios.stream()
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

    }

    public static void imprimeEpisodiosPorTemporada(List<Episodio> episodios){

        System.out.println("\nEpisódios por temporada");

        episodios.forEach(System.out::println);
    }

    public static void imprimeEpisodiosAPartirData(Integer ano, List<Episodio> episodios) {

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episódio: " + e.getNumero() + ". " + e.getTitulo() +
                                " Data Lançamento: " + e.getDataLancamento().format(formatador)
                ));


    }

}
