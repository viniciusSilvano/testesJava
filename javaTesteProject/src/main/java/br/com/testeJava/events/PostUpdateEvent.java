package br.com.testeJava.events;

import org.hibernate.event.internal.PostUpdateEventListenerStandardImpl;

import br.com.testeJava.entity.pessoa.Colaborador;

public class PostUpdateEvent extends PostUpdateEventListenerStandardImpl {

	@Override
	public void onPostUpdate(org.hibernate.event.spi.PostUpdateEvent event) {
		super.onPostUpdate(event);
		Object o = event.getEntity();
		if(o instanceof Colaborador) {
			System.out.println(o);
		}
	}
	
}
