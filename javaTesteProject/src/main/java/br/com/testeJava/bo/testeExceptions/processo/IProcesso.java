package br.com.testeJava.bo.testeExceptions.processo;

public interface IProcesso {
    public EntidadeMock processar(EntidadeMock entity) throws Exception;

    public boolean isAtivo(EntidadeMock entity);
}
