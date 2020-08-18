package br.com.testeJava.bo.testeExceptions.processo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.testeJava.bo.testeExceptions.services.EntityMockService;

public class PreencherId implements IProcesso {
	
	private EntityMockService service;
	private Logger LOG = Logger.getLogger(PreencherId.class.getName());
	
	public PreencherId(EntityMockService service) {
		super();
		this.service = service;
	}

	@Override
	public EntidadeMock processar(EntidadeMock entity) throws Exception {
		try {
			if(this.isAtivo(entity)) {
				return service.inserirId(entity);
			}
			return entity;
		}catch(Exception e) {
			LOG.log(Level.SEVERE, ExceptionUtils.getStackTrace(e));
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean isAtivo(EntidadeMock entity) {
		return true;
	}
	
}
