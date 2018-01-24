package com.hadi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import sun.plugin.dom.exception.InvalidStateException;

/**
 *
 */
@Aspect
@Component
public class ControllerValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerValidationAspect.class);

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void methodAcceptBindingResult() {
    }

    @Pointcut("execution(* com.hadi..*Controller.*(..))")
    public void controllers() {
    }

    @Pointcut("execution(* *(.., org.springframework.validation.BindingResult, ..))")
    public void getBindingResult() {
    }

    @Before("methodAcceptBindingResult() && controllers() && getBindingResult()")
    public void controllerBeforeAdvice(JoinPoint jp) {
        System.out.println(jp);

        for (Object arg : jp.getArgs()) {
            if (!(arg instanceof BindingResult))
                continue;
            BindingResult bindingResult = (BindingResult) arg;
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input data is corrupted");
                throw new InvalidStateException("Input data is corrupted");
            }
        }

        LOGGER.info("No Exception for BindingResult");
    }
} 