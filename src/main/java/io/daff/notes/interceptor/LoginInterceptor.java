package io.daff.notes.interceptor;

import io.daff.enums.Codes;
import io.daff.exception.BaseException;
import io.daff.notes.util.SimpleRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author daffupman
 * @since 2021/3/3
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private SimpleRedisUtil simpleRedisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        // 获取header的token参数
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            log.info( "token为空，请求被拦截" );
            throw new BaseException(Codes.AUTHENTICATION_FAILED, "用户身份认证失败");
        }
        String userInfo = simpleRedisUtil.get(token);
        if (StringUtils.isEmpty(userInfo)) {
            log.info("无效的token，用户认证失败");
            throw new BaseException(Codes.AUTHENTICATION_FAILED, "用户身份认证失败");
        }

        return true;
    }
}
