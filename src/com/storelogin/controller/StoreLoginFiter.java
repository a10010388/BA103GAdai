package com.storelogin.controller;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class StoreLogin
 */
@WebFilter("/StoreLoginFiter")
public class StoreLoginFiter implements Filter {
	private FilterConfig config;
    
    public StoreLoginFiter() {
       
    }

	public void destroy() {
		config=null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object store_no = session.getAttribute("store_no");
		if (store_no == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/FrontEnd/reg_store/ToStore.jsp");
//			session.setAttribute("mem_ac","mrbrown");
//			System.out.println("filterSet");
			chain.doFilter(request, response);
			return;
		} else {
//			System.out.println("filterElse");
			session.setAttribute("store_no",store_no);
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.config=config;
	}

}
