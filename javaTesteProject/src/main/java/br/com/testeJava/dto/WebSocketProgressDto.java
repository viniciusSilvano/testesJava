package br.com.testeJava.dto;

import br.com.testeJava.entity.enuns.websocket.WebSocketSessionManagerKey;

public class WebSocketProgressDto implements IDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2666375553852667416L;
	private String key;
	private Integer progress;

	public WebSocketProgressDto(WebSocketSessionManagerKey key, Integer progress) {
		this.key = key.name();
		this.progress = progress;
	}

}
