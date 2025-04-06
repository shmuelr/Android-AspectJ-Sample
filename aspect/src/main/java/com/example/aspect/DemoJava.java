package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.InitializerSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

@Aspect
public class DemoJava {

    @Pointcut("execution(@javax.inject.Inject *.new(..))")
    public void injectConstructors() {}

    @Around("injectConstructors()")
    public Object wrapInjectWithTimer(ProceedingJoinPoint joinPoint) throws Throwable {
        return wrapWithTimer(joinPoint, "Inject");
    }

    @Pointcut("execution(@dagger.Provides * *(..))")
    public void providesMethod() {}

    @Around("providesMethod()")
    public Object wrapProvidesWithTimer(ProceedingJoinPoint joinPoint) throws Throwable {
     return wrapWithTimer(joinPoint, "Provide");
    }

    public Object wrapWithTimer(ProceedingJoinPoint joinPoint, String annotation) throws Throwable {
        long startTime = System.nanoTime();
        Object result; // To hold the result of the original method

        String className;
        String methodName;
        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            className = signature.getDeclaringType().getSimpleName();
            methodName = signature.getName();
        } else {
            Signature signature = joinPoint.getSignature();
            className = signature.getDeclaringType().getSimpleName();
            methodName = "<init>";
        }

        try {
            // Proceed with the actual execution of the @Provides method
            result = joinPoint.proceed();
            return result; // Return the result obtained from the original method
        } finally {
            // This block executes whether the method returned normally or threw an exception
            long endTime = System.nanoTime();
            long durationNanos = endTime - startTime;
            long durationMillis = TimeUnit.NANOSECONDS.toMillis(durationNanos);

            // Log the timing information
            // Consider using a more robust logging framework (SLF4j, Logback, Log4j2)
            // in a real application.
            System.out.println(String.format(
                    "[TIMING] Dagger %s: %s.%s executed in %d ms (%d ns)",
                    annotation,
                    className,
                    methodName,
                    durationMillis,
                    durationNanos
            ));
        }
        // Note: If an exception was thrown by joinPoint.proceed(), it will propagate
        // naturally after the finally block completes. The 'return result' inside the
        // try block handles the normal return path.
    }

    public static DemoJava aspectOf() {
        return new DemoJava();
    }
}
