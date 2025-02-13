package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.dto.ChaveOmdb;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.DadosConverter;

import java.util.ArrayList;
import java.util.List;

public class TelaSaida {
    private static ConsumoApi consumo = new ConsumoApi();
    private static DadosConverter conversor = new DadosConverter();

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=" + ChaveOmdb.getChave();

    public static void imprimeSeries(String nomeSerie) {
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        Integer totTemporadas = TelaSaida.imprimeDadosSerie(json);

        List<DadosTemporada> temporadas =
                TelaSaida.imprimeDadosTemporada(nomeSerie, json, totTemporadas);

        TelaSaida.imprimeTodosEpisodiosDaSeriePorTemporada(temporadas);
    }

    private static Integer imprimeDadosSerie(String json) {
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        return dados.totalTemporadas();
    }

    private static List<DadosTemporada> imprimeDadosTemporada(String nomeSerie, String json, Integer totalTemporadas) {

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i<=totalTemporadas; i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

        return temporadas;
    }

    private static void imprimeTodosEpisodiosDaSeriePorTemporada(List<DadosTemporada> temporadas) {

//        for(int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j< episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> {
            System.out.println("\nTemporada " + t.numero());
            t.episodios().forEach(e -> System.out.println(
                    e.numero() + " " +
                            e.titulo() + " " +
                            e.dataLancamento()));
        });

    }

}
