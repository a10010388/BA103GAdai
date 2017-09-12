package com.store.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@MultipartConfig()

public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		String action = req.getParameter("action");
		System.out.println(action);
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("STORE_NO");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入店家編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String STORE_NO = null;
				try {
					STORE_NO = new String(str);

				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getonestore(STORE_NO);
				if (storeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("storeVO", storeVO); // 資料庫取出的empVO物件,存入req
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String STORE_NO = new String(req.getParameter("STORE_NO"));

				/*************************** 2.開始查詢資料 ****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getonestore(STORE_NO);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("storeVO", storeVO); // 資料庫取出的empVO物件,存入req
				String url = "/store/update_store_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String STORE_NAME = req.getParameter("STORE_NAME").trim();
				System.out.println(STORE_NAME);
				String TAX_ID_NO = req.getParameter("TAX_ID_NO").trim();
				String STORE_STAT = req.getParameter("STORE_STAT").trim();
				String STORE_PHONE = req.getParameter("STORE_PHONE").trim();
				String STORE_ADD = req.getParameter("STORE_ADD").trim();
				String STORE_CONT = req.getParameter("STORE_CONT").trim();
				Integer STORE_FREE_SHIP = new Integer(req.getParameter("STORE_FREE_SHIP").trim());

				String STORE_NO = req.getParameter("STORE_NO").trim();
				StoreVO storeVO = new StoreVO();
				storeVO.setSTORE_NAME(STORE_NAME);
				storeVO.setTAX_ID_NO(TAX_ID_NO);
				storeVO.setSTORE_STAT(STORE_STAT);
				storeVO.setSTORE_PHONE(STORE_PHONE);
				storeVO.setSTORE_ADD(STORE_ADD);
				storeVO.setSTORE_CONT(STORE_CONT);
				storeVO.setSTORE_NO(STORE_NO);
				storeVO.setSTORE_FREE_SHIP(STORE_FREE_SHIP);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.updatesotre(STORE_NO, TAX_ID_NO, STORE_PHONE, STORE_ADD, STORE_NAME, STORE_CONT,
						STORE_FREE_SHIP, STORE_STAT);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("storeVO", storeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
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
				String STORE_NAME = req.getParameter("STORE_NAME").trim();
				String TAX_ID_NO = req.getParameter("TAX_ID_NO").trim();
				String STORE_ADD = req.getParameter("STORE_ADD").trim();
				String STORE_PHONE = req.getParameter("TAX_ID_NO").trim();
				String STORE_CONT = req.getParameter("STORE_CONT").trim();
				String MEM_AC = req.getParameter("MEM_AC").trim();
				InputStream WIN_ID_PIC = req.getPart("WIN_ID_PIC").getInputStream();
				ByteArrayOutputStream id_pic = new ByteArrayOutputStream();
				int idpic;
				byte[] WIN_ID_PICimg = new byte[16384];

				while ((idpic = WIN_ID_PIC.read(WIN_ID_PICimg, 0, WIN_ID_PICimg.length)) != -1) {
					id_pic.write(WIN_ID_PICimg, 0, idpic);
				}
				id_pic.flush();
				String STORE_ADD_LAT =req.getParameter("STORE_ADD_LAT").trim();
				String STORE_ADD_LON =req.getParameter("STORE_ADD_LON").trim();
				
				InputStream STORE_PIC11 = req.getPart("STORE_PIC1").getInputStream();
				ByteArrayOutputStream spic_1 = new ByteArrayOutputStream();
				int spic1;
				byte[] STORE_PIC1 = new byte[16384];

				while ((spic1 = STORE_PIC11.read(STORE_PIC1, 0, STORE_PIC1.length)) != -1) {
					spic_1.write(STORE_PIC1, 0, spic1);
				}
				spic_1.flush();
				
				InputStream STORE_PIC22 = req.getPart("STORE_PIC2").getInputStream();
				ByteArrayOutputStream spic_2 = new ByteArrayOutputStream();
				int spic2;
				byte[] STORE_PIC2 = new byte[16384];

				while ((spic2 = STORE_PIC22.read(STORE_PIC2, 0, STORE_PIC2.length)) != -1) {
					spic_2.write(STORE_PIC2, 0, spic2);
				}
				spic_2.flush();
				
				InputStream STORE_PIC33 = req.getPart("STORE_PIC3").getInputStream();
				ByteArrayOutputStream spic_3 = new ByteArrayOutputStream();
				int spic3;
				byte[] STORE_PIC3 = new byte[16384];

				while ((spic3 = STORE_PIC33.read(STORE_PIC3, 0, STORE_PIC3.length)) != -1) {
					spic_3.write(STORE_PIC3, 0, spic3);
				}
				spic_3.flush();
				
				String STORE_STAT = req.getParameter("STORE_STAT").trim();
				Integer STORE_FREE_SHIP = new Integer(req.getParameter("STORE_FREE_SHIP").trim());
				java.sql.Date STORE_STAT_CDATE = null;
				try {
					STORE_STAT_CDATE = java.sql.Date.valueOf(req.getParameter("STORE_STAT_CDATE").trim());
				} catch (IllegalArgumentException e) {
					STORE_STAT_CDATE=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				StoreVO storeVO = new StoreVO();
//				storeVO.setSTORE_NAME(STORE_NAME);
//				storeVO.setTAX_ID_NO(TAX_ID_NO);
//				storeVO.setSTORE_ADD(STORE_ADD);
//				storeVO.setSTORE_PHONE(STORE_PHONE);
//				storeVO.setSTORE_CONT(STORE_CONT);
//				storeVO.setMEM_AC(MEM_AC);
//				storeVO.setWIN_ID_PIC(WIN_ID_PICimg);
//				storeVO.setSTORE_ADD_LAT(STORE_ADD_LAT);
//				storeVO.setSTORE_ADD_LON(STORE_ADD_LON);
//				storeVO.setSTORE_PIC1(STORE_PIC1);
//				storeVO.setSTORE_PIC2(STORE_PIC2);
//				storeVO.setSTORE_PIC2(STORE_PIC3);
//				storeVO.setSTORE_STAT(STORE_STAT);
//				storeVO.setSTORE_FREE_SHIP(STORE_FREE_SHIP);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/store/addStore.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.addStore(MEM_AC, TAX_ID_NO, WIN_ID_PICimg, STORE_PHONE, STORE_ADD, STORE_ADD_LAT, STORE_ADD_LON, STORE_NAME, STORE_CONT, STORE_PIC1, STORE_PIC2, STORE_PIC3, STORE_FREE_SHIP, STORE_STAT_CDATE, STORE_STAT);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/addStore.jsp");
				failureView.forward(req, res);
			}
		}

	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
