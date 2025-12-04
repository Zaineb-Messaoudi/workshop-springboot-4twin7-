package tn.esprit.tpfoyer.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspectAround {
    @Around("execution(* tn.esprit.tpfoyer.services.ChambreServiceImpl.findChambreByEtudiantCin(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable
    {
        long start= System.currentTimeMillis();
        Object obj= pjp.proceed();
        long elapsedTime= System.currentTimeMillis() -start;
        log.info("Methodexecutiontime: " + elapsedTime+ " milliseconds.");
        return obj;
    }
}
