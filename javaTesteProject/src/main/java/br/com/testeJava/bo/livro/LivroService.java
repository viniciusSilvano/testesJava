package br.com.testeJava.bo.livro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.NotImplementedException;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.infinispan.cache.CacheLivro;
import br.com.testeJava.bo.infinispan.cache.CacheLivros;
import br.com.testeJava.bo.livro.qualifier.LivroServiceQualifier;
import br.com.testeJava.dao.IDAO;
import br.com.testeJava.dto.LivroDto;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.entity.Livro;

@Stateless
@LivroServiceQualifier
public class LivroService extends BaseService {

	@Inject
	private CacheLivro cacheLivro;

	@Inject
	private CacheLivros cacheLivros;
	
	public final static List<Livro> db = new ArrayList<>(Arrays.asList(new Livro(0L, "teste"), new Livro(1L, "teste1"),
			new Livro(2L, "teste2"), new Livro(3L, "teste3"), new Livro(4L, "teste4"), new Livro(5L, "teste5"),
			new Livro(6L, "teste6"), new Livro(7L, "teste7"), new Livro(8L, "teste8"), new Livro(9L, "teste9"),
			new Livro(10L, "teste10"), new Livro(11L, "teste11"), new Livro(12L, "teste12"), new Livro(13L, "teste13"),
			new Livro(14L, "teste14"), new Livro(15L, "teste15"), new Livro(16L, "teste16")));

	@Override
	protected IDAO getDAO() {
		return null;
	}
	
	public List<LivroDto> consultarLivros() {
		/*
		 * List<LivroDto> livros = new ArrayList<LivroDto>(); for(Livro livro: db) {
		 * if(Objects.isNull(cacheLivro.recuperar(livro.getId()))) { LivroDto oLivro =
		 * new LivroDto(livro.getId(),livro.getNome()); cacheLivro.inserir(oLivro);
		 * livros.add(oLivro); }else { livros.add(cacheLivro.recuperar(livro.getId()));
		 * } } return livros;
		 */

		List<LivroDto> livros = new ArrayList<LivroDto>();
		if (Objects.isNull(cacheLivros.recuperar(1l))) {
			for (Livro livro : db) {
				if (Objects.isNull(cacheLivro.recuperar(livro.getId()))) {
					LivroDto oLivro = new LivroDto(livro.getId(), livro.getNome());
					livros.add(oLivro);
				} else {
					livros.add(cacheLivro.recuperar(livro.getId()));
				}
			}
			cacheLivros.inserir(livros);
		}else {
			livros = cacheLivros.recuperar(1l);
		}
		return livros;
	}

	public LivroDto consultarLivroPorId(Long id) {
		if (id == null || id.longValue() < 0) {
			throw new IllegalArgumentException("id inválido");
		}
		Optional<LivroDto> livroDtoOptional;
		livroDtoOptional = Optional.ofNullable(cacheLivro.recuperar(id));
		if (!livroDtoOptional.isPresent()) {
			Optional<Livro> livroOptional = db.parallelStream()
					.filter(livro -> livro.getId().longValue() == id.longValue()).findFirst();
			return new LivroDto(livroOptional.isPresent() ? livroOptional.get().getNome() : null);
		} else {
			return livroDtoOptional.get();
		}
	}

	@Override
	public void inserir(IEntidade entidade) throws Exception {
		throw new NotImplementedException();
	}
}
