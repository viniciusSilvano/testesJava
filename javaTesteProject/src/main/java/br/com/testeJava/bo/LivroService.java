package br.com.testeJava.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.testeJava.dto.LivroDto;
import br.com.testeJava.entity.Livro;

public class LivroService {
	
	public final static List<Livro> db = new ArrayList<>(
			Arrays.asList(new Livro(0L,"teste")));
	
	public LivroDto consultarLivroPorId(Long id) {
		if(id == null || id.longValue() < 0) {
			throw new IllegalArgumentException("id inválido");
		}
		Optional<Livro> livroOptional = db.stream().filter(livro -> livro.getId().longValue() == id.longValue()).findFirst();
		return new LivroDto(livroOptional.isPresent() ? livroOptional.get().getNome() : null);
	}
}
