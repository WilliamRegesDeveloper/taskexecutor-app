package br.com.taskexecutor.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class StopWatchAspect {

    private static final Logger logger = LoggerFactory.getLogger(StopWatchAspect.class);

    @Around("@annotation(ShowCronous)")
    public Object showStopWatch(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        logger.info("========" + joinPoint.getSignature().getName() + "=========");
        logger.info("Start: -> " + stopWatch.getTotalTimeSeconds() );

        Object proceed = joinPoint.proceed();

        stopWatch.stop();
        logger.info("End: -> " + stopWatch.getTotalTimeSeconds());
        return proceed;
    }
}
