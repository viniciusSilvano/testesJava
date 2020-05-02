package br.com.testeJava.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.testeJava.dto.LivroDto;
import br.com.testeJava.entity.Livro;

@Stateless
public class LivroService {

	public final static List<Livro> db = 
			new ArrayList<>(Arrays.asList(
					new Livro(0L, "teste"),
					new Livro(1L, "teste2")
			)
	);

	public List<LivroDto> consultarLivros() {
		return new ArrayList<LivroDto>(
				db.parallelStream().map(
						livro -> new LivroDto(livro.getNome())
				).collect(Collectors.toList())
		);
	}

	public LivroDto consultarLivroPorId(Long id) {
		if (id == null || id.longValue() < 0) {
			throw new IllegalArgumentException("id inválido");
		}
		Optional<Livro> livroOptional = db.parallelStream()
				.filter(livro -> livro.getId().longValue() == id.longValue())
				.findFirst();
		return new LivroDto(livroOptional.isPresent() ? livroOptional.get().getNome() : null);
	}
}
