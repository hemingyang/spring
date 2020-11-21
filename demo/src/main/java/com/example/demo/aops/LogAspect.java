package com.example.demo.aops;

import com.example.demo.logs.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * 操作日志记录处理
 *
 * @author
 */
@Aspect
@Component
@Slf4j
public class LogAspect {



    ThreadLocal<Long> startTime = new ThreadLocal<>();

    //private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    // 配置织入点
    @Pointcut("@annotation(com.example.demo.logs.LogInfo)")
    public void logPointCut() {
    }


    /**
     * 处理完请求后执行
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }



    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {

        try {

            // 方法名称
            // 类名方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();

            // 获得注解
            LogInfo logInfo = getAnnotationLog(joinPoint);
            if (logInfo == null) {
                System.out.printf(className+methodName);
                return;
            }

        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
        System.out.printf("时间"+new Date().toString());
    }

    @After("logPointCut()")
    public void afterAdvice(){
        System.out.println("Student profile has been setup."+"初始日志LOGEEER");
        log.info("RESPONSE : "+"------------------");
        System.out.printf("时间"+new Date().toString());
    }


    /**
     * 是否存在注解，如果存在就获取
     */
    private LogInfo getAnnotationLog(JoinPoint joinPoint) throws Exception {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(LogInfo.class);
        }
        return null;
    }

}
