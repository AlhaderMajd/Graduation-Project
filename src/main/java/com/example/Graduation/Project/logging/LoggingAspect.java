package com.example.Graduation.Project.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // Pointcut definitions (these should already exist)
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || " +
            "within(@org.springframework.stereotype.Service *) || " +
            "within(@org.springframework.stereotype.Repository *)")
    public void springBeanPointcut() {}

    @Pointcut("within(com.example.Graduation.Project..*)")
    public void applicationPackagePointcut() {}

    // Add this @Before advice
    @Before("applicationPackagePointcut() && springBeanPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("User {} called {}.{}()",
                getCurrentUsername(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }


    // Helper method to get current user
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "anonymous";
    }


    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("{} executed in {} ms",
                joinPoint.getSignature(),
                executionTime);

        return proceed;
    }
    private String maskSensitiveData(Object arg) {
        if (arg == null) return "null";

        String str = arg.toString();

        // Mask passwords in any object
        if (str.toLowerCase().contains("password")) {
            return "[PASSWORD_MASKED]";
        }

        // Mask JWT tokens
        if (str.toLowerCase().contains("bearer ")) {
            return "Bearer [TOKEN_MASKED]";
        }

        // Mask emails (optional)
        if (str.contains("@")) {
            return str.replaceAll("(^[^@]{2}|(?!^)\\G)[^@]", "$1*");
        }

        return str;
    }
    @AfterReturning(pointcut = "applicationPackagePointcut() && springBeanPointcut()",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exit: {}.{}() with result = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result != null ? result.toString() : "null");
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()",
            throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getCause() : "NULL");
    }

    // More specific pointcuts for controllers
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object logAroundGetMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("GET request to {}.{}()",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;

        log.info("GET request completed in {} ms", elapsedTime);
        return result;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logAroundPostMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("POST request to {}.{}()",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;

        log.info("POST request completed in {} ms", elapsedTime);
        return result;
    }
}