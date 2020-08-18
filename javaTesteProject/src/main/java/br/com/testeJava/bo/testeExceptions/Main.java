package br.com.testeJava.bo.testeExceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.com.testeJava.bo.testeExceptions.processo.EntidadeMock;
import br.com.testeJava.bo.testeExceptions.processo.IProcesso;
import br.com.testeJava.bo.testeExceptions.processo.PreencherId;
import br.com.testeJava.bo.testeExceptions.processo.PreencherNome;
import br.com.testeJava.bo.testeExceptions.services.EntityMockService;

public class Main {
	public static void main(String[] args) {
			EntityMockService service = new EntityMockService();
			List<IProcesso> processos = new ArrayList<IProcesso>();
			processos.add(new PreencherId(service));
			processos.add(new PreencherNome(service));
			
			EntidadeMock entidade = new EntidadeMock();
			new Processador(processos,entidade).processar();
	}
}

class Processador{
	private List<IProcesso> processos;
	private EntidadeMock entidade;
	
	public Processador(List<IProcesso> processos, EntidadeMock entidade) {
		super();
		this.entidade = entidade;
		this.processos = processos;
	}

	public void processar() {
		try {
			for(IProcesso processo : processos) {
				try {
					processo.processar(entidade);
				}catch(Exception e) {
					throw new Exception(e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao processar " + e);
		}
	}
}
