package com.funong.funong.conf;

import com.funong.funong.pojo.Token;
import com.funong.funong.service.TokenService;
import com.funong.funong.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class RootInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private WebSocketService webSocketService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }else {
            try {
                HttpSession session = request.getSession();
                String token1 = (String) session.getAttribute("User-Token");
                    Token token = tokenService.getTokenByToken(token1);
                    if (token.getTokenType().equals("root")){
                        return true;
                    }else {
                        return false;
                    }

            } catch (Exception e) {
                String token2 = request.getHeader("User-Token");
                Token token = tokenService.getTokenByToken(token2);
                if (token.getTokenType().equals("root")){
                    return true;
                }else {
                    webSocketService.sendToAllTerminal((long) 1,"token过期");
                    return false;
                }
            }


        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
