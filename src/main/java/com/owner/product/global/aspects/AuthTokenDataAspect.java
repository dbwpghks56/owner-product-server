package com.owner.product.global.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class AuthTokenDataAspect {
    // @AuthenticationPrincipal에서 가져온 값 이 null 일 때 커스텀 error 발생하게 AOP 만들어줘
    @Pointcut("args(.., @annotation(org.springframework.security.core.annotation.AuthenticationPrincipal))")
    public void tokenPointCut() {
    }

    @Before("tokenPointCut()")
    public void beforeGetMe() {
        log.info("beforeGetMe");
    }

}
