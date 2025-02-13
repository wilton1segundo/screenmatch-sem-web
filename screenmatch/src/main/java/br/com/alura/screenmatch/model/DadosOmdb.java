package br.com.alura.screenmatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class DadosOmdb {

    private DadosSerie dadosSerie;

    private List<Episodio> episodios = new ArrayList<Episodio>();

    private List<DadosTemporada> temporadas = new ArrayList<DadosTemporada>();

}
