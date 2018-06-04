package abr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import abr.bean.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);  

    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {    
        User user =  (User)request.getSession().getAttribute("user");   
        if(user == null){  
        	log.info("--------------------login check--------------------");    
            log.info("requestUri:"+request.getRequestURI());   
            log.info("--------------------redirect to login page--------------------");  
            response.sendRedirect(request.getContextPath()+"/login");
            return false;  
        }else  
            return true;     
    }    
    
}
