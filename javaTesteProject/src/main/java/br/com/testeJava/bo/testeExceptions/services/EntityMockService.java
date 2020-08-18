package br.com.testeJava.bo.testeExceptions.services;

import java.sql.SQLException;

import br.com.testeJava.bo.testeExceptions.processo.EntidadeMock;

public class EntityMockService {
	private static long id = 0;
	public EntidadeMock inserirId(EntidadeMock entity) throws Exception {
		entity.setId(id);
		id++;
		throw new SQLException("erro de sql");
		//return entity;
	}
	
	public EntidadeMock inserirNome(EntidadeMock entity) throws SQLException {
		entity.setNome("entidade " + entity.getId());
		throw new SQLException("Uma exception");
		//return entity;
	}
}
