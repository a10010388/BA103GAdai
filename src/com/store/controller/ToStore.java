package com.store.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class ToStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameter("action"));

		String action = req.getParameter("action");

		if ("Application".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String mem_ac = req.getParameter("mem_ac");
				if (mem_ac == null || (mem_ac.trim()).length() == 0) {
					errorMsgs.add("請登入會員");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/reg_store/ToStore.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("mem_ac", mem_ac); // 資料庫取出的會員 放入req
				String url = "/FrontEnd/reg_store/UpToStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/reg_store/ToStore.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String mem_ac = req.getParameter("mem_ac").trim();
				String store_name = req.getParameter("store_name").trim();
				String tax_id_no = req.getParameter("tax_id_no").trim();
				String store_phone = req.getParameter("store_phone").trim();
				String store_add = req.getParameter("store_add").trim();
				String store_cont = req.getParameter("store_cont").trim();
				
				String store_add_lat = req.getParameter("store_add_lat").trim();
				String store_add_lon = req.getParameter("store_add_lon").trim();
				
				Integer store_free_ship = new Integer(req.getParameter("store_free_ship").trim());
				
				

				InputStream win_id_pic = req.getPart("win_id_pic").getInputStream();
				ByteArrayOutputStream id_pic = new ByteArrayOutputStream();
				int idpic;
				byte[] idimg = new byte[16384];

				while ((idpic = win_id_pic.read(idimg, 0, idimg.length)) != -1) {
					id_pic.write(idimg, 0, idpic);
				}
				byte[] idimg1 = id_pic.toByteArray();
				
				if(idimg1.length==0){
					
				}
				
				InputStream storepic1 = req.getPart("store_pic1").getInputStream();
				ByteArrayOutputStream spic_1 = new ByteArrayOutputStream();
				int spic1;
				byte[] sphoto1 = new byte[16384];

				while ((spic1 = storepic1.read(sphoto1, 0, sphoto1.length)) != -1) {
					spic_1.write(sphoto1, 0, spic1);
				}
				byte[] sphoto1_1 = spic_1.toByteArray();

				
				InputStream storepic2 = req.getPart("store_pic2").getInputStream();
				ByteArrayOutputStream spic_2 = new ByteArrayOutputStream();
				int spic2;
				byte[] sphoto2 = new byte[16384];
				while ((spic2 = storepic2.read(sphoto2, 0, sphoto2.length)) != -1) {
					spic_2.write(sphoto2, 0, spic2);
				}
				byte[] sphoto2_1 = spic_2.toByteArray();

				
				InputStream storepic3 = req.getPart("store_pic3").getInputStream();
				ByteArrayOutputStream spic_3 = new ByteArrayOutputStream();
				int spic3;
				byte[] sphoto3 = new byte[16384];

				while ((spic3 = storepic3.read(sphoto3, 0, sphoto3.length)) != -1) {
					spic_3.write(sphoto3, 0, spic3);
				}
				byte[] sphoto3_1 = spic_3.toByteArray();
				
				
//				java.util.Date stat_cdate = new java.util.Date();
//				java.sql.Date store_stat_cdate = new java.sql.Date(stat_cdate.getTime());
//				
//				String store_stat = "待審中";
				

				StoreVO storeVO = new StoreVO();
				storeVO.setStore_name(store_name);
				storeVO.setTax_id_no(tax_id_no);
				storeVO.setStore_add(store_add);
				storeVO.setStore_phone(store_phone);
				storeVO.setStore_cont(store_cont);
				storeVO.setMem_ac(mem_ac);
				storeVO.setWin_id_pic(idimg1);
				storeVO.setStore_add_lat(store_add_lat);
				storeVO.setStore_add_lon(store_add_lon);
				storeVO.setStore_pic1(sphoto1_1);
				storeVO.setStore_pic2(sphoto2_1);
				storeVO.setStore_pic3(sphoto3_1);
//				storeVO.setStore_stat(store_stat);
//				storeVO.setStore_stat_cdate(store_stat_cdate);
				storeVO.setStore_free_ship(store_free_ship);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/reg_store/ToStore.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println(mem_ac);
				/*************************** 2.開始新增資料 ***************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.addStore(mem_ac, tax_id_no, idimg1, store_phone, store_add, store_add_lat,
						store_add_lon, store_name, store_cont, sphoto1_1, sphoto2_1, sphoto3_1, store_free_ship);
				
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/BackEnd/reg_store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/reg_store/ToStore.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
