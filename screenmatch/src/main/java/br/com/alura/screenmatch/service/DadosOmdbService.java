package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.ChaveOmdb;
import br.com.alura.screenmatch.model.DadosOmdb;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import ch.qos.logback.core.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DadosOmdbService {

    private static final ConsumoApi consumo = new ConsumoApi();
    private static final DadosConverter conversor = new DadosConverter();

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=" + ChaveOmdb.getChave();

    private static DadosOmdb dadosOmdb = new DadosOmdb();

    public static DadosOmdb inicie(String nomeSerie){
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        if (StringUtil.isNullOrEmpty(json)) {
            lanceMensagem("Não foi possível encontrar dados da série " + nomeSerie);
            return null;
        }

        recupereDadosSerie(json);

        int totTemporadas = 0;

        try {
            totTemporadas = Integer.parseInt(dadosOmdb.getDadosSerie().totalTemporadas());
        } catch (NumberFormatException e) {
            lanceMensagem("Não foi possível encontrar dados da série " + nomeSerie);
            return null;
        }

        DadosOmdbService.recupereDadosTemporada(nomeSerie, json, totTemporadas);

        DadosOmdbService.recupereEpisodiosPorTemporada();

        return dadosOmdb;
    }


    private static void  recupereDadosSerie(String json) {
        dadosOmdb.setDadosSerie( conversor.obterDados(json, DadosSerie.class));
    }
    private static void recupereDadosTemporada(String nomeSerie, String json, Integer totalTemporadas) {

        List<DadosTemporada> temporadas = new ArrayList<DadosTemporada>();

        for (int i = 1; i<=totalTemporadas; i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        dadosOmdb.setTemporadas(temporadas);
    }

    private static void recupereEpisodiosPorTemporada() {

        dadosOmdb.setEpisodios(
            dadosOmdb.getTemporadas()
                    .stream()
                        .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                        )
                   .toList()
        );
    }

    private static void lanceMensagem(String mensagem) {
        System.out.println(mensagem);
    }

}
