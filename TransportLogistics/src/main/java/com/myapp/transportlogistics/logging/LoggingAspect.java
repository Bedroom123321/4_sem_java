package com.myapp.transportlogistics.logging;

import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    private static final String METHOD_WITHOUT_ARGUMENTS = "Метод без аргуметов";

    @Pointcut("execution(public * com.myapp.transportlogistics.controller.*.*(..))")
    public void controllerLog() {}

    @Pointcut("execution(public * com.myapp.transportlogistics.service.*.*(..)))")
    public void serviceLog() {}

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : METHOD_WITHOUT_ARGUMENTS;
        log.info("Вызов метода контроллера: {}. Аргументы метода: {}",
                jp.getSignature().toShortString(), argsString);
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterController(JoinPoint jp, Object returnObject) {
        if (returnObject != null) {
            log.info("Завершение работы метода контроллера: {}. Результат: {}",
                    jp.getSignature().toShortString(), returnObject);
        } else {
            log.info("Завершение работы метода контроллера: {}.",
                    jp.getSignature().toShortString());
        }
    }

    @AfterThrowing(throwing = "exception", pointcut = "controllerLog()")
    public void trowsExceptionInController(JoinPoint jp, Exception exception) {
        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : METHOD_WITHOUT_ARGUMENTS;
        log.error("Исключение в методе контроллера: {}. "
                        + "Аргументы метода: {}. Сообщение исключения: {}",
                jp.getSignature().toShortString(), argsString, exception.getMessage());
    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : METHOD_WITHOUT_ARGUMENTS;
        log.info("Вызов метода сервиса: {}. Аргументы метода: {}",
                jp.getSignature().toShortString(), argsString);
    }

    @AfterReturning(returning = "returnObject", pointcut = "serviceLog()")
    public void doAfterService(JoinPoint jp, Object returnObject) {
        if (returnObject != null) {
            log.info("Завершение работы метода сервиса: {}. Результат: {}",
                    jp.getSignature().toShortString(), returnObject);
        } else {
            log.info("Завершение работы метода сервиса: {}.",
                    jp.getSignature().toShortString());
        }
    }

    @AfterThrowing(throwing = "exception", pointcut = "serviceLog()()")
    public void trowsExceptionInService(JoinPoint jp, Exception exception) {
        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : METHOD_WITHOUT_ARGUMENTS;
        log.error("Исключение в методе сервиса: {}. Аргументы метода: {}. Сообщение исключения: {}",
                jp.getSignature().toShortString(), argsString, exception.getMessage());
    }

    @Before("execution(public * com.myapp.transportlogistics.cache.Cache.*(..))")
    public void doBeforeCache(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : METHOD_WITHOUT_ARGUMENTS;
        log.info("Вызов метода кэша: {}. Аргументы метода: {}",
                jp.getSignature().toShortString(), argsString);
    }

    @AfterReturning(returning = "result", pointcut =
            "execution(public * com.myapp.transportlogistics.cache.Cache.get(..))")
    public void doAfterGet(JoinPoint jp, Optional<?> result) {

        if (result.isPresent()) {
            Object key = jp.getArgs()[0];
            Object value = result.get();
            log.info("Попадание в кэш: ключ={}, значение={}", key, value);
        } else {
            Object key = jp.getArgs()[0];
            log.info("Промах кэша: ключ={}", key);
        }
    }

    @AfterReturning(pointcut =
            "execution(public * com.myapp.transportlogistics.cache.Cache.put(..))")
    public void doAfterPut(JoinPoint jp) {
        Object[] args = jp.getArgs();
        log.info("Записано в кэш: ключ={}, значение={}", args[0], args[1]);
    }

    @AfterReturning(pointcut =
            "execution(public * com.myapp.transportlogistics.cache.Cache.remove(..))")
    public void doAfterRemove(JoinPoint jp) {
        Object key = jp.getArgs()[0];
        if (key == null) {
            log.warn("Попытка удалить несуществующий ключ");
        } else {
            log.info("Удалено из кэша: ключ={}", key);
        }
    }

    @AfterReturning(pointcut =
            "execution(public * com.myapp.transportlogistics.cache.Cache.getSize(..))",
            returning = "size")
    public void doAfterSize(int size) {
        log.info("Текущий размер кэша: {}", size);
    }
}
