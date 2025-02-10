package br.com.alura.screenmatch.service;

public interface IDadosConverter {

    <T> T obterDados(String json, Class<T> classe);
}
