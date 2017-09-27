package com.ord.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ord_list.model.Ord_listService;
import com.ord_list.model.Ord_listVO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;


@WebServlet("/ord/Ord_manag.do")
public class Ord_manag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		System.out.println(req.getParameter("action"));
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ord_no = new String(req.getParameter("ord_no"));
				String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:istAllEmp.jsp)
				req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,

				

				/*************************** 2.開始查詢資料 ****************************************/
				OrdService ordSvc = new OrdService();
				
				Set<Ord_listVO>  ord_listVOs = ordSvc.getOrd_listByOrd(ord_no);
				OrdVO  ordVO =ordSvc.getOrdByOrdno(ord_no);
				
				
				
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("ord_listVOs", ord_listVOs); // 資料庫取出的empVO物件,存入req
				req.setAttribute("ordVO", ordVO);
				String url = "/FrontEnd/ord_mag/Ord_listforUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/ord_mag/listAllorder_bystore.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
