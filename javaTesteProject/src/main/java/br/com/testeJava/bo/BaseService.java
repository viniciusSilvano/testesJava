package br.com.testeJava.bo;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.interfaces.ITempoTestavel;

public abstract class BaseService<T> {
	protected abstract BaseDAO<T> getDAO();
	
	public Long executarTesteDeTempo(ITempoTestavel metodo) {
		Long inicio = System.currentTimeMillis();
		metodo.executarTesteDeTempo();
		Long tempoFim = (System.currentTimeMillis() - inicio)/1000;
		System.out.println( String.format("levou %d segundos", tempoFim));
		return tempoFim;
	}	
}
