package br.com.testeJava.bo.testeDatas;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

public class ComparacaoSegundos implements TestExecuter {
	private Logger LOG = Logger.getLogger(ComparacaoSegundos.class.getName());
	
	@Override
	public void execute() {
		LocalDateTime horaChamada = LocalDateTime.of(2020,8,18,13,0,40);
		long diffBilheteExistente = ChronoUnit.SECONDS.between(horaChamada, LocalDateTime.of(2020,8,18,13,0,30));
		long diffBilheteNovo = ChronoUnit.SECONDS.between(horaChamada, LocalDateTime.of(2020,8,18,13,0,50));
		if (diffBilheteNovo < diffBilheteExistente) {
			LOG.info("diffBilheteNovo é menor");
		} else {
			LOG.info("diffBilheteExistente é menor");
		}
	}
}
