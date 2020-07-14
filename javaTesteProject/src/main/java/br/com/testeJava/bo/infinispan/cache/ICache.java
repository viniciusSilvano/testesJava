package br.com.testeJava.bo.infinispan.cache;

public interface ICache<T extends Cacheavel> {
	public void inserir(T entity);
	public T recuperar(Long id);
	public T atualizar(T entity);
	public void deletar(T entity);
}
