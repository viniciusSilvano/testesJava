package br.com.testeJava.bo.builder;


public abstract class BaseBuilder<T extends Buildable> {
	
	protected abstract BaseBuilder<T> getImplementedBuilder();
	
	protected abstract Class<T> getEntityClass();
	
	public abstract T build();
	
	public abstract BaseBuilder<T> copyValuesToBuilder(T obj);
	
	public abstract T clone(T alvoClonagem, T fonteDados);
	
	public abstract void reset();
}
