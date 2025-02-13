package br.com.alura.screenmatch.principal;

import java.util.Scanner;

public class TelaEntrada {

    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu(){
        while (true) {
            System.out.println("\nDigite o nome da série para busca");
            var nomeSerie = leitura.nextLine();

            if (nomeSerie.equalsIgnoreCase("sair")) break;

            TelaSaida.imprimeSeries(nomeSerie);
        }
    }
}
