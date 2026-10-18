package br.com.testeJava.interceptor;

import br.com.testeJava.bo.testeExceptions.processo.PreencherId;
import org.apache.commons.lang3.time.StopWatch;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

public class TimeInterceptor {
    private Logger LOG = Logger.getLogger(TimeInterceptor.class.getName());

    @AroundInvoke
    public Object tempoExecucao(InvocationContext invocationContext){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try{
            return invocationContext.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            stopWatch.stop();
            LOG.info("Tempo execução do método: " + stopWatch.getTime()
                    + " do método: " +  invocationContext.getMethod().getName()
                    + " da classe: " + invocationContext.getMethod().getDeclaringClass().getName());
        }
    }
}
