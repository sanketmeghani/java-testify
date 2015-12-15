package dev.sanket.metrics;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;

@Aspect
public class Metrics
{
    private static final MetricRegistry metrics;
    private static final ConsoleReporter reporter;

//    private static final Graphite graphite;
//    private static final GraphiteReporter reporter;

    private Context context;

    static
    {
        metrics = new MetricRegistry();

//        graphite = new Graphite(new InetSocketAddress("localhost", 2003));
//        reporter = GraphiteReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).filter(MetricFilter.ALL).build(graphite);
        
        reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
        reporter.start(3, TimeUnit.SECONDS);
    }

    @Around("execution(* *(..)) && @annotation(dev.sanket.metrics.Monitor)")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable
    {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Monitor monitor = method.getAnnotation(Monitor.class);
        MetricsType[] toBeCollected = monitor.metrics();

        Object returnValue = null;

        try
        {
            if (MetricsType.LATENCY.needsToCollect(toBeCollected))
            {
                Timer timer = metrics.timer(MetricRegistry.name(signature.getDeclaringTypeName(), signature.getName(), MetricsType.LATENCY.name()));
                context = timer.time();
            }

            returnValue = pjp.proceed();
        }
        finally
        {
            if (context != null)
            {
                context.stop();
            }
        }

        return returnValue;
    }
}
