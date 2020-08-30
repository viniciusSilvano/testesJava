package br.com.testeJava.bo;

import br.com.testeJava.dao.testeDesempenho.IDAO;

public abstract class BaseService implements IService {
	protected abstract IDAO getDAO();
}
