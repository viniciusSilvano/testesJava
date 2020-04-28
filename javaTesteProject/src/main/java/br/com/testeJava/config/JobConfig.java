package br.com.testeJava.config;

import java.util.Timer;

import javax.ejb.Singleton;

import br.com.testeJava.entity.job.PrintConsoleJob;

@Singleton
public class JobConfig {
	private static Timer timer;
	
	public void configTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new PrintConsoleJob(), 0l,definirTimerPorMinutos(1));
	}
	
	
	private long definirTimerPorHoras(long horas ) {
		return horas * 60 * 60 * 1000;
	}
	
	private long definirTimerPorMinutos(long minutos) {
		return minutos * 60 * 1000;
	}
	
	private long definirTimerPorSegundos(long segundos) {
		return segundos * 1000;
	}
}
