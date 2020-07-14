package br.com.testeJava.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.testeJava.bo.testeJackson.AFakeMarshaller;
import br.com.testeJava.entity.pessoa.Colaborador;
import br.com.testeJava.entity.profissoes.Profissao;
import br.com.testeJava.entity.profissoes.Programador;

@Path("marshallingTest")
public class MarshalllerRest {
	
	@Inject
	private AFakeMarshaller marshaller;
	
	@GET
	@Path("/execute")
	public void marshallingTeste() {
		List<Profissao> profissoes = new ArrayList<Profissao>();
		profissoes.add(new Programador("Java",30));
		try {
			String json = marshaller.objectToObjectStream(new Colaborador(1l, profissoes));
			System.out.println(String.format("Unmarshalling: %s", marshaller.objectFromObjectStream(json)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
