package br.com.testeJava.entity.job.impl;

import java.util.logging.Logger;

import br.com.testeJava.entity.job.JobBase;

public class PrintConsoleJob extends JobBase {
	@Override
	public void run() {
		System.out.println("JOB printConsoleJob executado");
	}

	@Override
	public Logger getLog() {
		// TODO Auto-generated method stub
		return null;
	}

}
