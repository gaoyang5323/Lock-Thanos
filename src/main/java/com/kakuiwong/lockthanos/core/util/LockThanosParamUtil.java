package com.kakuiwong.lockthanos.core.util;

import com.kakuiwong.lockthanos.annotation.LockThanos;
import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.exception.LockExceptionHandler;
import com.kakuiwong.lockthanos.exception.LockThanosException;
import com.kakuiwong.lockthanos.exception.impl.LockExceptionHandlerDefault;
import jodd.util.ClassUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class LockThanosParamUtil implements InitializingBean {

    private Map<Class<? extends LockExceptionHandler>, LockExceptionHandler> map = new ConcurrentHashMap<>(4);
    private SpelExpressionParser spelParser = new SpelExpressionParser();

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put(LockExceptionHandlerDefault.class, new LockExceptionHandlerDefault());
    }

    public LockParam convert(LockThanos annotation, ProceedingJoinPoint joinPoint) {
        return new LockParam(annotation.lockName() + getKey(annotation.key(), joinPoint),
                annotation.tryLockTime(),
                annotation.autoUnlockTime(),
                annotation.timeUnit(),
                annotation.type(),
                getExceptionHandler(annotation.exceptionHandler()));
    }

    public LockExceptionHandler getExceptionHandler(Class<? extends LockExceptionHandler> exceptionHandlerCls) {
        return map.computeIfAbsent(exceptionHandlerCls, k -> {
            try {
                return ClassUtil.newInstance(exceptionHandlerCls);
            } catch (Exception e) {
                throw new LockThanosException("LockExceptionHandler class Cannot instantiate", e);
            }
        });
    }

    public String getKey(String[] keys, ProceedingJoinPoint joinPoint) {
        if (keys.length == 1 && keys[0].equals("")) {
            return "";
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        List<String> names = Arrays.asList(signature.getParameterNames());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < names.size(); i++) {
            evaluationContext.setVariable(names.get(i), args[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            Object value = spelParser.parseExpression(key).getValue(evaluationContext);
            if(value==null){
                continue;
            }
            stringBuilder.append(value);
            stringBuilder.append("_");
        }
        return stringBuilder.toString();
    }
}
