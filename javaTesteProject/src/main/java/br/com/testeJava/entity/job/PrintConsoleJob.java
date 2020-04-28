package br.com.testeJava.entity.job;

import java.util.TimerTask;

public class PrintConsoleJob extends TimerTask {
	@Override
	public void run() {
		System.out.println("JOB printConsoleJob executado");
	}
}
