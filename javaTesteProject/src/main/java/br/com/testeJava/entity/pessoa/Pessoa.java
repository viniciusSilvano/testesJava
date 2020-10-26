package br.com.testeJava.entity.pessoa;

import br.com.testeJava.entity.IEntidade;

public interface Pessoa extends IEntidade{
	String getNome();
	void setNome(String nome);
}