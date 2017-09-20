package com.store.model;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class StoreJDBCDAO implements StoreDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba103g4";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO store (STORE_NO,MEM_AC,TAX_ID_NO,WIN_ID_PIC,STORE_PHONE,STORE_ADD,STORE_ADD_LAT,"
			+ "STORE_ADD_LON,STORE_NAME,STORE_CONT,STORE_PIC1,STORE_PIC2,STORE_PIC3,STORE_FREE_SHIP,"
			+ "STORE_STAT,STORE_STAT_CONT,STORE_STAT_CDATE) VALUES ('S'||STORE_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '待審中', null, sysdate)";
	private static final String GET_ALL_STMT = "SELECT * FROM store order by STORE_NO";
	private static final String GET_ONE_STMT = "SELECT STORE_NO,MEM_AC,TAX_ID_NO,WIN_ID_PIC,STORE_PHONE,STORE_ADD,STORE_ADD_LAT,"
			+ "STORE_ADD_LON,STORE_NAME,STORE_CONT,STORE_PIC1,STORE_PIC2,STORE_PIC3,STORE_FREE_SHIP,"
			+ "STORE_STAT,STORE_STAT_CONT,STORE_STAT_CDATE FROM store where STORE_NO = ?";
	private static final String DELETE = "DELETE FROM store where STORE_NO = ?";
	private static final String UPDATE = "UPDATE store set TAX_ID_NO=?, WIN_ID_PIC=?, STORE_PHONE=? ,STORE_ADD=?,STORE_ADD_LAT=?,STORE_ADD_LON=?,STORE_NAME=?,STORE_CONT=?,STORE_PIC1=?,STORE_PIC2=?,STORE_PIC3=?,STORE_FREE_SHIP=?,STORE_STAT='待審中',STORE_STAT_CDATE=sysdate  where STORE_NO = ? and STORE_STAT like '%審核不通過%'";
	private static final String UPDATE_STAT ="UPDATE store set STORE_STAT=?,store_stat_cdate=sysdate,STORE_STAT_CONT=? where STORE_NO = ?";
	private static final String SELECT_STAT = "select * from store where store_stat like ?";
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<StoreVO> getAll_stat(String store_stat) {
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_STAT);
			pstmt.setString(1, store_stat);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getString("store_no"));
				storeVO.setMem_ac(rs.getString("mem_ac"));
				storeVO.setTax_id_no(rs.getString("tax_id_no"));
				storeVO.setWin_id_pic(rs.getBytes("win_id_pic"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_add_lat(rs.getString("store_add_lat"));
				storeVO.setStore_add_lon(rs.getString("store_add_lon"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_cont(rs.getString("store_cont"));
				storeVO.setStore_pic1(rs.getBytes("store_pic1"));
				storeVO.setStore_pic2(rs.getBytes("store_pic2"));
				storeVO.setStore_pic3(rs.getBytes("store_pic3"));
				storeVO.setStore_free_ship(rs.getInt("store_free_ship"));
				storeVO.setStore_stat(rs.getString("store_stat"));
				storeVO.setStore_stat_cont(rs.getString("store_stat_cont"));
				storeVO.setStore_stat_cdate(rs.getDate("store_stat_cdate"));
				list.add(storeVO); 
				// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}

		return list;
	}
	
	
	
	
	
	
	@Override
	public void insert(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, storeVO.getMem_ac());
			pstmt.setString(2, storeVO.getTax_id_no());
			
			byte [] WIN_ID_PIC = storeVO.getWin_id_pic();
			Blob blobWIN_ID_PIC = con.createBlob();
			
			blobWIN_ID_PIC.setBytes(1, WIN_ID_PIC);
			pstmt.setBlob(3, blobWIN_ID_PIC);

			pstmt.setString(4, storeVO.getStore_phone());
			pstmt.setString(5, storeVO.getStore_add());
			pstmt.setString(6, storeVO.getStore_add_lat());
			pstmt.setString(7, storeVO.getStore_add_lon());
			pstmt.setString(8, storeVO.getStore_name());
			pstmt.setString(9, storeVO.getStore_cont());
			byte [] STORE_PIC1 = storeVO.getStore_pic1();
			Blob blobSTORE_PIC1 = con.createBlob();
			blobSTORE_PIC1.setBytes(1, STORE_PIC1);
			pstmt.setBlob(10, blobSTORE_PIC1);
			
			byte [] STORE_PIC2 = storeVO.getStore_pic2();
			Blob blobSTORE_PIC2 = con.createBlob();
			blobSTORE_PIC2.setBytes(1, STORE_PIC2);
			pstmt.setBlob(11, blobSTORE_PIC2);
			
			byte [] STORE_PIC3 = storeVO.getStore_pic3();
			Blob blobSTORE_PIC3 = con.createBlob();
			blobSTORE_PIC3.setBytes(1, STORE_PIC3);
			pstmt.setBlob(12, blobSTORE_PIC3);
			
			
			pstmt.setInt(13, storeVO.getStore_free_ship());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(StoreVO storeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, storeVO.getTax_id_no());
			
			byte [] WIN_ID_PIC = storeVO.getWin_id_pic();
			Blob blobWIN_ID_PIC = con.createBlob();
			blobWIN_ID_PIC.setBytes(1, WIN_ID_PIC);
			pstmt.setBlob(2, blobWIN_ID_PIC);
			
			
			pstmt.setString(3, storeVO.getStore_phone());
			pstmt.setString(4, storeVO.getStore_add());
			pstmt.setString(5, storeVO.getStore_add_lat());
			pstmt.setString(6, storeVO.getStore_add_lon());
			pstmt.setString(7, storeVO.getStore_name());
			pstmt.setString(8, storeVO.getStore_cont());
			
			byte [] STORE_PIC1 = storeVO.getStore_pic1();
			Blob blobSTORE_PIC1 = con.createBlob();
			blobSTORE_PIC1.setBytes(1, STORE_PIC1);
			pstmt.setBlob(9, blobSTORE_PIC1);
			
			byte [] STORE_PIC2 = storeVO.getStore_pic2();
			Blob blobSTORE_PIC2 = con.createBlob();
			blobSTORE_PIC2.setBytes(1, STORE_PIC2);
			pstmt.setBlob(10, blobSTORE_PIC2);
			
			byte [] STORE_PIC3 = storeVO.getStore_pic3();
			Blob blobSTORE_PIC3 = con.createBlob();
			blobSTORE_PIC3.setBytes(1, STORE_PIC3);
			pstmt.setBlob(11, blobSTORE_PIC3);
			
			pstmt.setInt(12, storeVO.getStore_free_ship());
			pstmt.setString(13, storeVO.getStore_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void update_stat(StoreVO storeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STAT);

			pstmt.setString(1, storeVO.getStore_stat());
//			pstmt.setDate(2, storeVO.getStore_stat_cdate());
			pstmt.setString(2, storeVO.getStore_stat_cont());
			pstmt.setString(3, storeVO.getStore_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String store_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, store_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public StoreVO findByPrimaryKey(String store_no) {
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, store_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getString("store_no"));
				storeVO.setMem_ac(rs.getString("mem_ac"));
				storeVO.setTax_id_no(rs.getString("tax_id_no"));
				storeVO.setWin_id_pic(rs.getBytes("win_id_pic"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_add_lat(rs.getString("store_add_lat"));
				storeVO.setStore_add_lon(rs.getString("store_add_lon"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_cont(rs.getString("store_cont"));
				storeVO.setStore_pic1(rs.getBytes("store_pic1"));
				storeVO.setStore_pic2(rs.getBytes("store_pic2"));
				storeVO.setStore_pic3(rs.getBytes("store_pic3"));
				storeVO.setStore_free_ship(rs.getInt("store_free_ship"));
				storeVO.setStore_stat(rs.getString("store_stat"));
				storeVO.setStore_stat_cont(rs.getString("store_stat_cont"));
				storeVO.setStore_stat_cdate(rs.getDate("store_stat_cdate"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getString("store_no"));
				storeVO.setMem_ac(rs.getString("mem_ac"));
				storeVO.setTax_id_no(rs.getString("tax_id_no"));
				storeVO.setWin_id_pic(rs.getBytes("win_id_pic"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_add_lat(rs.getString("store_add_lat"));
				storeVO.setStore_add_lon(rs.getString("store_add_lon"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_cont(rs.getString("store_cont"));
				storeVO.setStore_pic1(rs.getBytes("store_pic1"));
				storeVO.setStore_pic2(rs.getBytes("store_pic2"));
				storeVO.setStore_pic3(rs.getBytes("store_pic3"));
				storeVO.setStore_free_ship(rs.getInt("store_free_ship"));
				storeVO.setStore_stat(rs.getString("store_stat"));
				storeVO.setStore_stat_cont(rs.getString("store_stat_cont"));
				storeVO.setStore_stat_cdate(rs.getDate("store_stat_cdate"));
				list.add(storeVO); 
				// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}

		return list;
	}
	
	
	

	public static void main(String[] args) throws IOException {

		StoreJDBCDAO dao = new StoreJDBCDAO();
//
//		// 新增
//		StoreVO sotreVO1 = new StoreVO();
//		sotreVO1.setMem_ac("dantea");
//		sotreVO1.setTax_id_no("86912114");
//		byte [] win_id_pic = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\6.jpg");
//		
//		sotreVO1.setWin_id_pic(win_id_pic);
//		sotreVO1.setStore_phone("0912-345-789");
//		sotreVO1.setStore_add("桃園市楊梅區高獅路5號");
//		sotreVO1.setStore_add_lat("24.922551");
//		sotreVO1.setStore_add_lon("121.148549");
//		sotreVO1.setStore_name("混蛋咖啡聽");
//		sotreVO1.setStore_cont("混蛋咖啡聽是一群混蛋開的11111");
//		byte [] store_pic1 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\851.jpg");
//		sotreVO1.setStore_pic1(store_pic1);
//		byte [] store_pic2 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\852.jpg");
//		sotreVO1.setStore_pic2(store_pic2);
//		byte [] store_pic3 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\853.jpg");
//		sotreVO1.setStore_pic3(store_pic3);
//		sotreVO1.setStore_free_ship(1000);
//		
//		dao.insert(sotreVO1);

		// 前端修改
//		StoreVO storeVO2 = new StoreVO();
//		storeVO2.setTax_id_no("84561278");
//		byte [] win_id_pic = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\6.jpg");
//		storeVO2.setWin_id_pic(win_id_pic);
//		
//		storeVO2.setStore_phone("08545156");
//		
//		storeVO2.setStore_add("測試地址修改22222");
//		storeVO2.setStore_add_lat("000.123");
//		storeVO2.setStore_add_lon("123.1236");
//		storeVO2.setStore_name("混蛋555");
//		storeVO2.setStore_cont("混蛋殺過人5555");
//		
//		byte [] store_pic1 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\851.jpg");
//		storeVO2.setStore_pic1(store_pic1);
//		byte [] store_pic2 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\852.jpg");
//		storeVO2.setStore_pic2(store_pic2);
//		byte [] store_pic3 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\853.jpg");
//		storeVO2.setStore_pic3(store_pic3);
//		
//		storeVO2.setStore_free_ship(100);
//		
//		storeVO2.setStore_no("S1000000052");
//		dao.update(storeVO2);
		
		//後端修改
//		StoreVO storeVO3 = new StoreVO();
//		storeVO3.setStore_stat("審核不通過");
//		storeVO3.setStore_no("S1000000004");
//		storeVO3.setStore_stat_cont("就是不給你過12");
//		dao.update_stat(storeVO3);
		
		
		
		
		
		

		// 刪除
//		dao.delete("S1000000011");

		// 查詢
//		StoreVO storeVO4 = dao.findByPrimaryKey("S1000000001");
//		System.out.println(storeVO4.getStore_no() + ",");
//		System.out.println(storeVO4.getMem_ac() + ",");
//		System.out.println(storeVO4.getTax_id_no() + ",");
//		System.out.println(storeVO4.getWin_id_pic() + ",");
//		System.out.println(storeVO4.getStore_phone() + ",");
//		System.out.println(storeVO4.getStore_add() + ",");
//		System.out.println(storeVO4.getStore_add_lat() + ",");
//		System.out.println(storeVO4.getStore_add_lon());
//		System.out.println(storeVO4.getStore_name() + ",");
//		System.out.println(storeVO4.getStore_cont());
//		System.out.println(storeVO4.getStore_pic1() + ",");
//		System.out.println(storeVO4.getStore_pic2() + ",");
//		System.out.println(storeVO4.getStore_pic3() + ",");
//		System.out.println(storeVO4.getStore_free_ship() + ",");
//		System.out.println(storeVO4.getStore_stat() + ",");
//		System.out.println(storeVO4.getStore_stat_cont());
//		System.out.println(storeVO4.getStore_stat_cdate());
//		System.out.println("---------------------");

		// 查詢
//		List<StoreVO> list = dao.getAll();
//		for (StoreVO astore : list) {
//			System.out.println(astore.getStore_no() + ",");
//			System.out.println(astore.getMem_ac() + ",");
//			System.out.println(astore.getTax_id_no() + ",");
//			System.out.println(astore.getWin_id_pic() + ",");
//			System.out.println(astore.getStore_phone() + ",");
//			System.out.println(astore.getStore_add() + ",");
//			System.out.println(astore.getStore_add_lat() + ",");
//			System.out.println(astore.getStore_add_lon());
//			System.out.println(astore.getStore_name() + ",");
//			System.out.println(astore.getStore_cont());
//			System.out.println(astore.getStore_pic1() + ",");
//			System.out.println(astore.getStore_pic2() + ",");
//			System.out.println(astore.getStore_pic3() + ",");
//			System.out.println(astore.getStore_free_ship() + ",");
//			System.out.println(astore.getStore_stat() + ",");
//			System.out.println(astore.getStore_stat_cont());
//			System.out.println(astore.getStore_stat_cdate());
//			System.out.println();
//		}
		//後端狀態查詢 
//		List<StoreVO> list1 = dao.getAll_stat("審核通過");
//		for (StoreVO astore : list1) {
//			System.out.println(astore.getStore_no() + ",");
//			System.out.println(astore.getMem_ac() + ",");
//			System.out.println(astore.getTax_id_no() + ",");
//			System.out.println(astore.getWin_id_pic() + ",");
//			System.out.println(astore.getStore_phone() + ",");
//			System.out.println(astore.getStore_add() + ",");
//			System.out.println(astore.getStore_add_lat() + ",");
//			System.out.println(astore.getStore_add_lon());
//			System.out.println(astore.getStore_name() + ",");
//			System.out.println(astore.getStore_cont());
//			System.out.println(astore.getStore_pic1() + ",");
//			System.out.println(astore.getStore_pic2() + ",");
//			System.out.println(astore.getStore_pic3() + ",");
//			System.out.println(astore.getStore_free_ship() + ",");
//			System.out.println(astore.getStore_stat() + ",");
//			System.out.println(astore.getStore_stat_cont());
//			System.out.println(astore.getStore_stat_cdate());
//			System.out.println();
//		
//		}
		
		
		
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
