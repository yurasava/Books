package io.github.yurasava.bookservice.aspect;

import io.github.yurasava.bookservice.entities.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private Map<Long, Optional<Book>> cache = new HashMap<>();

    @Before("execution(* io.github.yurasava.bookservice.service.BookService.*(..))")
    public void log(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Calling method: {}", methodName);
        logger.info("Method's arguments: {}", Arrays.toString(args));
    }


    @AfterReturning(pointcut = "execution(* io.github.yurasava.bookservice.service.BookService.*(..))", returning = "result")
    public void logResult(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} returned: {}", methodName, result);
    }

    @Around("execution(* io.github.yurasava.bookservice.service.BookService.getBookById(Long))")
    public Optional<Book> cacheGetBookById(ProceedingJoinPoint joinPoint) throws Throwable {
        Long id = (Long) joinPoint.getArgs()[0];
        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            Optional<Book> book = (Optional<Book>) joinPoint.proceed();
            cache.put(id, book);
            return book;
        }
    }
}
