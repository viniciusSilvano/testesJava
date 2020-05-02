package br.com.testeJava.config;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class JobStartup {
	
	@Inject
	JobConfig jobConfig;
	
	private static final Logger LOG = Logger.getLogger(JobStartup.class.getName());
	
	@PostConstruct
	public void iniciarJobs() {
		/*LOG.info("----------------------------INICIANDO JOBS----------------------------");
		jobConfig.configTimer();*/
	}
}
