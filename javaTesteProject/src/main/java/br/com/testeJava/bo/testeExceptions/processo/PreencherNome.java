package br.com.testeJava.bo.testeExceptions.processo;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.testeJava.bo.testeExceptions.services.EntityMockService;

public class PreencherNome implements IProcesso {
	private EntityMockService service;
	private Logger LOG = Logger.getLogger(PreencherNome.class.getName());
	
	public PreencherNome(EntityMockService service) {
		super();
		this.service = service;
	}

	@Override
	public EntidadeMock processar(EntidadeMock entity) throws Exception {
		try {
			if(this.isAtivo(entity)) {
				return service.inserirNome(entity);
			}
			return entity;
		}catch(Exception e) {
			LOG.log(Level.SEVERE,ExceptionUtils.getStackTrace(e));
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean isAtivo(EntidadeMock entity) {
		return Objects.nonNull(entity) && Objects.nonNull(entity.getId());
	}

}
