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

			pstmt.setString(1, storeVO.getMEM_AC());
			pstmt.setString(2, storeVO.getTAX_ID_NO());
			
			byte [] WIN_ID_PIC = storeVO.getWIN_ID_PIC();
			Blob blobWIN_ID_PIC = con.createBlob();
			
			blobWIN_ID_PIC.setBytes(1, WIN_ID_PIC);
			pstmt.setBlob(3, blobWIN_ID_PIC);

			pstmt.setString(4, storeVO.getSTORE_PHONE());
			pstmt.setString(5, storeVO.getSTORE_ADD());
			pstmt.setString(6, storeVO.getSTORE_ADD_LAT());
			pstmt.setString(7, storeVO.getSTORE_ADD_LON());
			pstmt.setString(8, storeVO.getSTORE_NAME());
			pstmt.setString(9, storeVO.getSTORE_CONT());
			byte [] STORE_PIC1 = storeVO.getSTORE_PIC1();
			Blob blobSTORE_PIC1 = con.createBlob();
			blobSTORE_PIC1.setBytes(1, STORE_PIC1);
			pstmt.setBlob(10, blobSTORE_PIC1);
			
			byte [] STORE_PIC2 = storeVO.getSTORE_PIC2();
			Blob blobSTORE_PIC2 = con.createBlob();
			blobSTORE_PIC2.setBytes(1, STORE_PIC2);
			pstmt.setBlob(11, blobSTORE_PIC2);
			
			byte [] STORE_PIC3 = storeVO.getSTORE_PIC3();
			Blob blobSTORE_PIC3 = con.createBlob();
			blobSTORE_PIC3.setBytes(1, STORE_PIC3);
			pstmt.setBlob(12, blobSTORE_PIC3);
			
			
			pstmt.setInt(13, storeVO.getSTORE_FREE_SHIP());
			pstmt.setString(14, storeVO.getSTORE_STAT());
			pstmt.setString(15, storeVO.getSTORE_STAT_CONT());
			pstmt.setDate(16, storeVO.getSTORE_STAT_CDATE());

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

			pstmt.setString(1, storeVO.getSTORE_NAME());
			pstmt.setString(2, storeVO.getSTORE_PHONE());
			pstmt.setString(3, storeVO.getSTORE_ADD());
			pstmt.setString(4, storeVO.getSTORE_NO());

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
	public void delete(String STORE_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, STORE_NO);

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
	public StoreVO findByPrimaryKey(String STORE_NO) {
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, STORE_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setSTORE_NO(rs.getString("STORE_NO"));
				storeVO.setMEM_AC(rs.getString("MEM_AC"));
				storeVO.setTAX_ID_NO(rs.getString("TAX_ID_NO"));
				storeVO.setWIN_ID_PIC(rs.getBytes("WIN_ID_PIC"));
				storeVO.setSTORE_PHONE(rs.getString("STORE_PHONE"));
				storeVO.setSTORE_ADD(rs.getString("STORE_ADD"));
				storeVO.setSTORE_ADD_LAT(rs.getString("STORE_ADD_LAT"));
				storeVO.setSTORE_ADD_LON(rs.getString("STORE_ADD_LON"));
				storeVO.setSTORE_NAME(rs.getString("STORE_NAME"));
				storeVO.setSTORE_CONT(rs.getString("STORE_CONT"));
				storeVO.setSTORE_PIC1(rs.getBytes("STORE_PIC1"));
				storeVO.setSTORE_PIC2(rs.getBytes("STORE_PIC2"));
				storeVO.setSTORE_PIC3(rs.getBytes("STORE_PIC3"));
				storeVO.setSTORE_FREE_SHIP(rs.getInt("STORE_FREE_SHIP"));
				storeVO.setSTORE_STAT(rs.getString("STORE_STAT"));
				storeVO.setSTORE_STAT_CONT(rs.getString("STORE_STAT_CONT"));
				storeVO.setSTORE_STAT_CDATE(rs.getDate("STORE_STAT_CDATE"));
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
				storeVO.setSTORE_NO(rs.getString("STORE_NO"));
				storeVO.setMEM_AC(rs.getString("MEM_AC"));
				storeVO.setTAX_ID_NO(rs.getString("TAX_ID_NO"));
				storeVO.setWIN_ID_PIC(rs.getBytes("WIN_ID_PIC"));
				storeVO.setSTORE_PHONE(rs.getString("STORE_PHONE"));
				storeVO.setSTORE_ADD(rs.getString("STORE_ADD"));
				storeVO.setSTORE_ADD_LAT(rs.getString("STORE_ADD_LAT"));
				storeVO.setSTORE_ADD_LON(rs.getString("STORE_ADD_LON"));
				storeVO.setSTORE_NAME(rs.getString("STORE_NAME"));
				storeVO.setSTORE_CONT(rs.getString("STORE_CONT"));
				storeVO.setSTORE_PIC1(rs.getBytes("STORE_PIC1"));
				storeVO.setSTORE_PIC2(rs.getBytes("STORE_PIC2"));
				storeVO.setSTORE_PIC3(rs.getBytes("STORE_PIC3"));
				storeVO.setSTORE_FREE_SHIP(rs.getInt("STORE_FREE_SHIP"));
				storeVO.setSTORE_STAT(rs.getString("STORE_STAT"));
				storeVO.setSTORE_STAT_CONT(rs.getString("STORE_STAT_CONT"));
				storeVO.setSTORE_STAT_CDATE(rs.getDate("STORE_STAT_CDATE"));
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

		// 新增
//		StoreVO sotreVO1 = new StoreVO();
//		sotreVO1.setMEM_AC("dantea");
//		sotreVO1.setTAX_ID_NO("86958535");
//		byte [] WIN_ID_PIC =getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\6.jpg");
//		
//		sotreVO1.setWIN_ID_PIC(WIN_ID_PIC);
//		sotreVO1.setSTORE_PHONE("0912-345-789");
//		sotreVO1.setSTORE_ADD("桃園市楊梅區高獅路5號");
//		sotreVO1.setSTORE_ADD_LAT("24.922551");
//		sotreVO1.setSTORE_ADD_LON("121.148549");
//		sotreVO1.setSTORE_NAME("混蛋咖啡聽");
//		sotreVO1.setSTORE_CONT("混蛋咖啡聽是一群混蛋開的");
//		byte [] STORE_PIC1 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\851.jpg");
//		sotreVO1.setSTORE_PIC1(STORE_PIC1);
//		byte [] STORE_PIC2 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\852.jpg");
//		sotreVO1.setSTORE_PIC2(STORE_PIC2);
//		byte [] STORE_PIC3 = getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\853.jpg");
//		sotreVO1.setSTORE_PIC3(STORE_PIC3);
//		sotreVO1.setSTORE_FREE_SHIP(1000);
//		sotreVO1.setSTORE_STAT("審核不通過");
//		sotreVO1.setSTORE_STAT_CONT("混蛋不准開");
//		java.util.Date STAT_CDATE = new java.util.Date();
//		java.sql.Date STORE_STAT_CDATE = new java.sql.Date(STAT_CDATE.getTime());
//		sotreVO1.setSTORE_STAT_CDATE(STORE_STAT_CDATE);
//		dao.insert(sotreVO1);

		// 修改
//		StoreVO storeVO2 = new StoreVO();
//		storeVO2.setSTORE_NAME("混蛋");
//		storeVO2.setSTORE_PHONE("025555555");
//		storeVO2.setSTORE_ADD("測試修改地址");
//		storeVO2.setSTORE_NO("S1000000014");
//		dao.update(storeVO2);

		// 刪除
//		dao.delete("S1000000011");

		// 查詢
//		StoreVO storeVO3 = dao.findByPrimaryKey("S1000000001");
//		System.out.println(storeVO3.getSTORE_NO() + ",");
//		System.out.println(storeVO3.getMEM_AC() + ",");
//		System.out.println(storeVO3.getTAX_ID_NO() + ",");
//		System.out.println(storeVO3.getWIN_ID_PIC() + ",");
//		System.out.println(storeVO3.getSTORE_PHONE() + ",");
//		System.out.println(storeVO3.getSTORE_ADD() + ",");
//		System.out.println(storeVO3.getSTORE_ADD_LAT() + ",");
//		System.out.println(storeVO3.getSTORE_ADD_LON());
//		System.out.println(storeVO3.getSTORE_NAME() + ",");
//		System.out.println(storeVO3.getSTORE_CONT());
//		System.out.println(storeVO3.getSTORE_PIC1() + ",");
//		System.out.println(storeVO3.getSTORE_PIC2() + ",");
//		System.out.println(storeVO3.getSTORE_PIC3() + ",");
//		System.out.println(storeVO3.getSTORE_FREE_SHIP() + ",");
//		System.out.println(storeVO3.getSTORE_STAT() + ",");
//		System.out.println(storeVO3.getSTORE_STAT_CONT());
//		System.out.println(storeVO3.getSTORE_STAT_CDATE());
//		System.out.println("---------------------");

		// 查詢
//		List<StoreVO> list = dao.getAll();
//		for (StoreVO astore : list) {
//			System.out.println(astore.getSTORE_NO() + ",");
//			System.out.println(astore.getMEM_AC() + ",");
//			System.out.println(astore.getTAX_ID_NO() + ",");
//			System.out.println(astore.getWIN_ID_PIC() + ",");
//			System.out.println(astore.getSTORE_PHONE() + ",");
//			System.out.println(astore.getSTORE_ADD() + ",");
//			System.out.println(astore.getSTORE_ADD_LAT() + ",");
//			System.out.println(astore.getSTORE_ADD_LON());
//			System.out.println(astore.getSTORE_NAME() + ",");
//			System.out.println(astore.getSTORE_CONT());
//			System.out.println(astore.getSTORE_PIC1() + ",");
//			System.out.println(astore.getSTORE_PIC2() + ",");
//			System.out.println(astore.getSTORE_PIC3() + ",");
//			System.out.println(astore.getSTORE_FREE_SHIP() + ",");
//			System.out.println(astore.getSTORE_STAT() + ",");
//			System.out.println(astore.getSTORE_STAT_CONT());
//			System.out.println(astore.getSTORE_STAT_CDATE());
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
