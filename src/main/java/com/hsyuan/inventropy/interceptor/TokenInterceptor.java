package com.hsyuan.inventropy.interceptor;


import com.hsyuan.inventropy.utils.JwtUtils;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 令牌拦截器 - 校验请求头中的token是否有效
 * Spring Boot 中的拦截器,用于校验请求头中的token是否有效，范围比Filter更小
 */
@Component // 注册为Spring组件,让IOC容器管理
public class TokenInterceptor implements HandlerInterceptor {
    // 预处理回调方法,在Controller方法调用之前调用。true表示继续流程, false表示流程中断
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null||token.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token不能为空");
            return false;
        }
        try{
            // 校验token
            JwtUtils.parseToken(token);
            // 校验通过,将token中的员工id放入请求属性中,后续Controller方法可以从请求属性中获取员工id
            ThreadLocalUtils.set(JwtUtils.parseToken(token).get("id"));
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token校验失败");
            return false;
        }
        return true;
    }
    // 后处理回调方法,在Controller方法调用之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 移除线程本地变量中的员工id,防止内存泄漏
        ThreadLocalUtils.remove();
    }
}
