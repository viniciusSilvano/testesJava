package br.com.testeJava.entity.job;

import java.util.TimerTask;
import java.util.logging.Logger;

public abstract class JobBase extends TimerTask {
	
	public abstract Logger getLog();
}
