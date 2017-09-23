package com.prod.controller;

import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.prod.model.*;
import com.store.model.*;

@WebServlet("/prod/Prod_manag.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Prod_manag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		System.out.println(req.getParameter("action"));
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String prod_no = new String(req.getParameter("prod_no"));
				String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:istAllEmp.jsp)
				req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,
				
															// 存入req(只用於:istAllEmp.jsp)

				

				/*************************** 2.開始查詢資料 ****************************************/
				ProdService prodSvc = new ProdService();
				ProdVO prodvo = prodSvc.getOneProd(prod_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("prodvo", prodvo); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/prod/get_oneprod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
												// update_emp_input.jsp
				successView.forward(req, res);
					
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
					
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/listAllpro_bystore.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update_prod".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/

				String prod_no = req.getParameter("prod_no");
				String store_no = req.getParameter("store_no").trim();
				String prod_name = req.getParameter("prod_name").trim();
				String bean_type = req.getParameter("bean_type").trim();
				String bean_grade = req.getParameter("bean_grade").trim();
				String bean_contry = req.getParameter("bean_contry").trim();
				String bean_region = req.getParameter("bean_region").trim();
				String bean_farm = req.getParameter("bean_farm").trim();
				String bean_farmer = req.getParameter("bean_farmer").trim();
				Integer bean_el  = new Integer (req.getParameter("bean_el").trim());
				String proc = req.getParameter("proc").trim();
				String roast = req.getParameter("roast").trim();
				Integer bean_attr_acid  = new Integer (req.getParameter("bean_attr_acid").trim());
				Integer bean_attr_aroma  = new Integer (req.getParameter("bean_attr_aroma").trim());
				Integer bean_attr_body  = new Integer (req.getParameter("bean_attr_body").trim());
				Integer bean_attr_after  = new Integer (req.getParameter("bean_attr_after").trim());
				Integer bean_attr_bal  = new Integer (req.getParameter("bean_attr_bal").trim());
				String bean_aroma = req.getParameter("bean_aroma").trim();
				Integer prod_price  = new Integer (req.getParameter("prod_price").trim());
				Double prod_wt  = new Double (req.getParameter("prod_wt").trim());
				Integer send_fee  = new Integer (req.getParameter("send_fee").trim());
				Integer prod_sup  = new Integer (req.getParameter("prod_sup").trim());
				String prod_cont = req.getParameter("prod_cont").trim();
				String prod_stat =req.getParameter("prod_stat").trim();
		
				String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:istAllEmp.jsp)
				req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,
															// 存入req(只用於:istAllEmp.jsp)

				
				
				ProdVO prodVO = new ProdVO();
				prodVO.setProd_no(prod_no);
				prodVO.setStore_no(store_no);
				
				prodVO.setProd_name(prod_name);
				prodVO.setBean_type(bean_type);
				prodVO.setBean_grade(bean_grade);
				prodVO.setBean_contry(bean_contry);
				
				prodVO.setBean_region(bean_region);
				prodVO.setBean_farm(bean_farm);
				
				prodVO.setBean_el(bean_el);
				prodVO.setProc(proc);
				prodVO.setRoast(roast);
				
				prodVO.setBean_attr_acid(bean_attr_acid);
				prodVO.setBean_attr_aroma(bean_attr_aroma);
				prodVO.setBean_attr_body(bean_attr_body);
				prodVO.setBean_attr_after(bean_attr_after);
				prodVO.setBean_attr_bal(bean_attr_bal);
				
				prodVO.setBean_aroma(bean_aroma);
				prodVO.setProd_price(prod_price);
				prodVO.setProd_wt(prod_wt);
				
				prodVO.setSend_fee(send_fee);
				prodVO.setProd_sup(prod_sup);
				prodVO.setProd_cont(prod_cont);
				prodVO.setProd_stat(prod_stat);
				
				

				 InputStream prod_pic1 = req.getPart("prod_pic1").getInputStream();
				 ByteArrayOutputStream pro_1 = new ByteArrayOutputStream();
				 int p1;
				 byte[] pho1 = new byte[16384];
				 while ((p1 = prod_pic1.read(pho1, 0, pho1.length))
				 != -1) {
				 pro_1.write(pho1, 0, p1);
				 }
				 byte [] pro_pho1 = pro_1.toByteArray();
				 
				 if (pro_pho1.length == 0){
				 errorMsgs.add("商品照片1不可為空!");
				 }
				 
				 
				 
				 InputStream prod_pic2 = req.getPart("prod_pic2").getInputStream();
				 ByteArrayOutputStream pro_2 = new ByteArrayOutputStream();
				 int p2;
				 byte[] pho2 = new byte[16384];
				 while ((p2 = prod_pic2.read(pho2, 0, pho2.length))
				 != -1) {
				 pro_2.write(pho2, 0, p2);
				 }
				 byte [] pro_pho2 = pro_2.toByteArray();
				 
				 InputStream prod_pic3 = req.getPart("prod_pic2").getInputStream();
				 ByteArrayOutputStream pro_3 = new ByteArrayOutputStream();
				 int p3;
				 byte[] pho3 = new byte[16384];
				 while ((p3 = prod_pic3.read(pho3, 0, pho3.length))
				 != -1) {
				 pro_3.write(pho3, 0, p3);
				 }
				 byte [] pro_pho3 = pro_3.toByteArray();


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/prod/get_oneprod.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				System.out.println(1111111);
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.updateProdbysto(prod_no,store_no,prod_name, bean_type, bean_grade, bean_contry, bean_region, bean_farm, bean_farmer, bean_el, proc, roast, bean_attr_acid, bean_attr_aroma, bean_attr_body, bean_attr_after, bean_attr_bal, bean_aroma, prod_price, prod_wt, send_fee, prod_sup, prod_cont, pro_pho1, pro_pho2, pro_pho3, prod_stat);
				/***************************
				 * 
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				
				req.setAttribute("prodVO", prodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/FrontEnd/prod/listAllpro_bystore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/get_oneprod.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addprod.jsp

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				
				String prod_stat = req.getParameter("prod_stat").trim();
				
				String store_no = req.getParameter("store_no").trim();
				String prod_name = req.getParameter("prod_name").trim();
				String bean_type = req.getParameter("bean_type").trim();
				String bean_grade = req.getParameter("bean_grade").trim();
				String bean_contry = req.getParameter("bean_contry").trim();
				String bean_region = req.getParameter("bean_region").trim();
				String bean_farm = req.getParameter("bean_farm").trim();

				String bean_farmer = req.getParameter("bean_farmer").trim();
				Integer bean_el = new Integer(req.getParameter("bean_el").trim());

				String proc = (req.getParameter("proc").trim());
				
				String roast = (req.getParameter("roast").trim());
				Integer bean_attr_acid = new Integer(req.getParameter("bean_attr_acid").trim());
				Integer bean_attr_aroma = new Integer(req.getParameter("bean_attr_aroma").trim());
				Integer bean_attr_body = new Integer(req.getParameter("bean_attr_acid").trim());
				Integer bean_attr_after = new Integer(req.getParameter("bean_attr_after").trim());
				Integer bean_attr_bal = new Integer(req.getParameter("bean_attr_bal").trim());
				String bean_aroma = (req.getParameter("bean_aroma").trim());
				Integer prod_price = new Integer(req.getParameter("prod_price").trim());
				Integer send_fee = new Integer(req.getParameter("send_fee").trim());
				Integer prod_sup = new Integer(req.getParameter("prod_sup").trim());
				String prod_cont = (req.getParameter("prod_cont").trim());
				
				
				InputStream prod_pic1 = req.getPart("prod_pic1").getInputStream();
				ByteArrayOutputStream pro_pic1 = new ByteArrayOutputStream();
				int pic1;
				byte[] proimg1 = new byte[16384];

				while ((pic1 = prod_pic1.read(proimg1, 0, proimg1.length)) != -1) {
					pro_pic1.write(proimg1, 0, pic1);
				}
				byte[] proimg1_1 = pro_pic1.toByteArray();

				InputStream prod_pic2 = req.getPart("prod_pic2").getInputStream();
				ByteArrayOutputStream pro_pic2 = new ByteArrayOutputStream();
				int pic2;
				byte[] proimg2 = new byte[16384];

				while ((pic2 = prod_pic2.read(proimg2, 0, proimg2.length)) != -1) {
					pro_pic2.write(proimg2, 0, pic2);
				}
				byte[] proimg1_2 = pro_pic2.toByteArray();

				InputStream prod_pic3 = req.getPart("prod_pic3").getInputStream();
				ByteArrayOutputStream pro_pic3 = new ByteArrayOutputStream();
				int pic3;
				byte[] proimg3 = new byte[16384];
				while ((pic3 = prod_pic3.read(proimg3, 0, proimg3.length)) != -1) {
					pro_pic3.write(proimg3, 0, pic3);
				}
				byte[] proimg1_3 = pro_pic3.toByteArray();

			

				// 驗證
				if (prod_name == null || (prod_name.trim()).length() == 0) {
					errorMsgs.add("請輸入商品名稱");
				}

				if (bean_contry == null || (bean_contry.trim()).length() == 0) {
					errorMsgs.add("請輸入生產國");
				}

				if (prod_price == null ) {
					errorMsgs.add("標價不可為空");
				}
				
				
				Double prod_wt = null;
				
				try {
					prod_wt = new Double(req.getParameter("prod_wt").trim());
				} catch (NumberFormatException e) {
					prod_wt = 0.0;
					errorMsgs.add("重量請填數字");
				}
				
				
				if (prod_cont == null || (prod_cont.trim()).length() == 0) {
					errorMsgs.add("請輸入商品描述");
				}

				if (proimg1_1.length == 0) {
					errorMsgs.add("商品照1不可為空");
				}

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/reg_store/UpToStore.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				

				ProdVO prodVO = new ProdVO();
				prodVO.setProd_name(prod_name);
				prodVO.setBean_type(bean_type);
				prodVO.setBean_grade(bean_grade);
				prodVO.setBean_contry(bean_contry);
				prodVO.setBean_region(bean_region);
				prodVO.setBean_farm(bean_farm);
				prodVO.setBean_farmer(bean_farmer);
				prodVO.setBean_el(bean_el);
				prodVO.setProc(proc);
				prodVO.setRoast(roast);
				prodVO.setBean_attr_acid(bean_attr_acid);
				prodVO.setBean_attr_aroma(bean_attr_aroma);
				prodVO.setBean_attr_body(bean_attr_body);
				prodVO.setBean_attr_after(bean_attr_after);
				prodVO.setBean_attr_bal(bean_attr_bal);
				prodVO.setBean_aroma(bean_aroma);
				prodVO.setProd_price(prod_price);
				
				prodVO.setProd_wt(prod_wt);
				prodVO.setSend_fee(send_fee);
				prodVO.setProd_sup(prod_sup);
				
				prodVO.setProd_cont(prod_cont);
				prodVO.setProd_sup(prod_sup);
				
				prodVO.setProd_pic1(proimg1_1);
				prodVO.setProd_pic1(proimg1_2);
				prodVO.setProd_pic1(proimg1_3);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", prodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/reg_store/UpToStore.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.addProd(store_no, prod_name, bean_type, bean_grade, bean_contry, bean_region, bean_farm, bean_farmer, bean_el, proc, roast, bean_attr_acid, bean_attr_aroma, bean_attr_body, bean_attr_after, bean_attr_bal, bean_aroma, prod_price, prod_wt, send_fee, prod_sup, prod_cont, prod_pic1, prod_pic2, prod_pic3, prod_stat, ed_time)
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/FrontEnd/reg_store/Finreg.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交Finreg.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/reg_store/UpToStore.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
