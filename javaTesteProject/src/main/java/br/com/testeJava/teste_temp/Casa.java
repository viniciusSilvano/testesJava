package br.com.testeJava.teste_temp;

import java.util.EnumMap;
import java.util.Map;

public class Casa {

	private final Map<VoltagemEletrica,Integer> tomadas = new EnumMap<>(VoltagemEletrica.class);
	
	public Map<VoltagemEletrica,Integer> getTomadas() {
		return tomadas;
	}

	public void addTomadas(VoltagemEletrica voltagem, Integer qtdTomadas) {
		tomadas.put(voltagem, qtdTomadas);
	}
	
}
