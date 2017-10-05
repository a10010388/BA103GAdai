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

import com.store.model.*;

/**
 * Servlet Filter implementation class StoreLogin
 */
@WebFilter("/StoreLoginFilter")
public class StoreLoginFilter implements Filter {
	private FilterConfig config;
    
    public StoreLoginFilter() {
       
    }

	public void destroy() {
		config=null;
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		String mem_ac = (String) session.getAttribute("mem_ac");
		StoreService storeSvc = new StoreService();
		StoreVO storeVO =new StoreVO();
		storeVO =storeSvc.getOneByMem(mem_ac);
//		String store_no = storeVO.getStore_no();
//		session.setAttribute("store_no",store_no);
		
		
		
		
		if (storeVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/FrontEnd/notstore/notstore.jsp");
//			session.setAttribute("mem_ac","mrbrown");
//			System.out.println("filterSet");
			chain.doFilter(request, response);
			return;
		} else if (storeVO.getStore_stat().equals("審核不通過")){
//			System.out.println("filterElse");
			session.setAttribute("store_no",storeVO.getStore_no());
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/FrontEnd/notstore/store_bynotpass.jsp");
			chain.doFilter(request, response);
		}else if (storeVO.getStore_stat().equals("待審中")){
//			System.out.println("filterElse");
			session.setAttribute("store_no",storeVO.getStore_no());
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/FrontEnd/notstore/store_Auditwait.jsp");
			chain.doFilter(request, response);
		} else {
//			System.out.println("filterElse");
			session.setAttribute("store_no",storeVO.getStore_no());
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.config=config;
	}

}
