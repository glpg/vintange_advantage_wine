/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yi
 */


public class UserFilter implements Filter{
    


    // You need to override init() and destroy() as well, but they can be kept empty.
    public void init(FilterConfig config) 
        throws ServletException{
    }
    
    public void destroy(){
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse reps   = (HttpServletResponse)response;
        if(req.getSession().getAttribute("account")==null){
            reps.sendRedirect("/vintage_advantage/faces/signin.xhtml");
        }
        chain.doFilter(request, response);
    }
    
}