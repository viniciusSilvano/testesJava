package br.com.testeJava.teste_temp;

public class CasaService {
	public void construirCasa() {
		Casa casa = new Casa();
		casa.addTomadas(VoltagemEletrica._110V, 5);
		casa.addTomadas(VoltagemEletrica._120V, 5);
	}
}
