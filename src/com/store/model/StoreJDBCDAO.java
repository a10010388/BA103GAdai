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
	String userid = "test";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO store (STORE_NO,MEM_AC,TAX_ID_NO,WIN_ID_PIC,STORE_PHONE,STORE_ADD,STORE_ADD_LAT,"
			+ "STORE_ADD_LON,STORE_NAME,STORE_CONT,STORE_PIC1,STORE_PIC2,STORE_PIC3,STORE_FREE_SHIP,"
			+ "STORE_STAT,STORE_STAT_CONT,STORE_STAT_CDATE) VALUES ('S'||STORE_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM store order by STORE_NO";
	private static final String GET_ONE_STMT = "SELECT STORE_NO,MEM_AC,TAX_ID_NO,WIN_ID_PIC,STORE_PHONE,STORE_ADD,STORE_ADD_LAT,"
			+ "STORE_ADD_LON,STORE_NAME,STORE_CONT,STORE_PIC1,STORE_PIC2,STORE_PIC3,STORE_FREE_SHIP,"
			+ "STORE_STAT,STORE_STAT_CONT,STORE_STAT_CDATE FROM store where STORE_NO = ?";
	private static final String DELETE = "DELETE FROM store where STORE_NO = ?";
	private static final String UPDATE = "UPDATE store set STORE_NAME=?, STORE_PHONE=?, STORE_ADD=? where STORE_NO = ?";

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
			pstmt.setString(14, storeVO.getStore_stat());
			pstmt.setString(15, storeVO.getStore_stat_cont());
			pstmt.setDate(16, storeVO.getStore_stat_cdate());

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

			pstmt.setString(1, storeVO.getStore_name());
			pstmt.setString(2, storeVO.getStore_phone());
			pstmt.setString(3, storeVO.getStore_add());
			pstmt.setString(4, storeVO.getStore_no());

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
		StoreVO sotreVO1 = new StoreVO();
		sotreVO1.setMem_ac("dantea");
		sotreVO1.setTax_id_no("86933444");
		byte [] win_id_pic = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\6.jpg");
		
		sotreVO1.setWin_id_pic(win_id_pic);
		sotreVO1.setStore_phone("0912-345-789");
		sotreVO1.setStore_add("桃園市楊梅區高獅路5號");
		sotreVO1.setStore_add_lat("24.922551");
		sotreVO1.setStore_add_lon("121.148549");
		sotreVO1.setStore_name("混蛋咖啡聽");
		sotreVO1.setStore_cont("混蛋咖啡聽是一群混蛋開的");
		byte [] store_pic1 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\851.jpg");
		sotreVO1.setStore_pic1(store_pic1);
		byte [] store_pic2 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\852.jpg");
		sotreVO1.setStore_pic2(store_pic2);
		byte [] store_pic3 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\853.jpg");
		sotreVO1.setStore_pic3(store_pic3);
		sotreVO1.setStore_free_ship(1000);
		sotreVO1.setStore_stat("審核不通過");
		sotreVO1.setStore_stat_cont("混蛋不准開");
		java.util.Date stat_cdate = new java.util.Date();
		java.sql.Date store_stat_cdate = new java.sql.Date(stat_cdate.getTime());
		sotreVO1.setStore_stat_cdate(store_stat_cdate);
		dao.insert(sotreVO1);

		// 修改
//		StoreVO storeVO2 = new StoreVO();
//		storeVO2.setStore_name("混蛋");
//		storeVO2.setStore_phone("025555555");
//		storeVO2.setStore_add("測試修改地址111");
//		storeVO2.setStore_no("S1000000014");
//		dao.update(storeVO2);

		// 刪除
//		dao.delete("S1000000011");

		// 查詢
//		StoreVO storeVO3 = dao.findByPrimaryKey("S1000000001");
//		System.out.println(storeVO3.getStore_no() + ",");
//		System.out.println(storeVO3.getMem_ac() + ",");
//		System.out.println(storeVO3.getTax_id_no() + ",");
//		System.out.println(storeVO3.getWin_id_pic() + ",");
//		System.out.println(storeVO3.getStore_phone() + ",");
//		System.out.println(storeVO3.getStore_add() + ",");
//		System.out.println(storeVO3.getStore_add_lat() + ",");
//		System.out.println(storeVO3.getStore_add_lon());
//		System.out.println(storeVO3.getStore_name() + ",");
//		System.out.println(storeVO3.getStore_cont());
//		System.out.println(storeVO3.getStore_pic1() + ",");
//		System.out.println(storeVO3.getStore_pic2() + ",");
//		System.out.println(storeVO3.getStore_pic3() + ",");
//		System.out.println(storeVO3.getStore_free_ship() + ",");
//		System.out.println(storeVO3.getStore_stat() + ",");
//		System.out.println(storeVO3.getStore_stat_cont());
//		System.out.println(storeVO3.getStore_stat_cdate());
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
