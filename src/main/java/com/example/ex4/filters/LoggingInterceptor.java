package com.example.ex4.filters;

import com.example.ex4.beans.UserSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The filter class
 */
public class LoggingInterceptor implements HandlerInterceptor {

    /**
     *
     */
    public LoggingInterceptor() {
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        UserSession userSession = (UserSession) request.getSession().getAttribute("scopedTarget.userSessionBean");
        if (userSession == null || userSession.getUserName() == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {

    }


}