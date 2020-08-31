package br.com.testeJava.bo;

import br.com.testeJava.dao.testeDesempenho.IDAO;
import br.com.testeJava.interfacesFuncionais.ITempoTestavel;

public abstract class BaseService implements IService {
	protected abstract IDAO getDAO();
	
	public void executarTesteDeTempo(ITempoTestavel metodo) {
		Long inicio = System.currentTimeMillis();
		metodo.executarTesteDeTempo();
		System.out.println(String.format("levou %d segundos", (System.currentTimeMillis() - inicio)/1000));
	}
}
