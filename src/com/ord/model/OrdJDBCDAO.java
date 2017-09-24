package com.ord.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.ord_list.model.Ord_listVO;



public class OrdJDBCDAO implements OrdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba103g4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ord (ORD_NO,MEM_AC,SEND_FEE,TOTAL_PAY,ORD_NAME,ORD_PHONE,ORD_ADD,"
			+ "PAY_INFO,ORD_STAT,ORD_DATE,PAY_DATE,PAY_CHK_DATE,SEND_DATE,SEND_ID) VALUES ('O'||ORD_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ord order by ORD_DATE desc";
	private static final String GET_ONE_STMT = "SELECT ORD_NO,MEM_AC,SEND_FEE,TOTAL_PAY,ORD_NAME,ORD_PHONE,ORD_ADD,"
			+ "PAY_INFO,ORD_STAT,ORD_DATE,PAY_DATE,PAY_CHK_DATE,SEND_DATE,SEND_ID FROM ord where ORD_NO = ?";
	private static final String DELETE = "DELETE FROM ord where ORD_NO = ?";
	private static final String UPDATE_STAT = "UPDATE ord set ORD_STAT=?, PAY_CHK_DATE=?, SEND_DATE=? ,SEND_ID=? where ORD_NO = ?";
	private static final String GET_ALL_ORDER_LIST = "select * from ord_list where ORD_NO=?";

	@Override
	public void insert(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ordVO.getMem_ac());
			pstmt.setInt(2, ordVO.getSend_fee());
			pstmt.setInt(3, ordVO.getTotal_pay());
			pstmt.setString(4, ordVO.getOrd_name());
			pstmt.setString(5, ordVO.getOrd_phone());
			pstmt.setString(6, ordVO.getOrd_add());
			pstmt.setString(7, ordVO.getPay_info());
			pstmt.setString(8, ordVO.getOrd_stat());
			pstmt.setDate(9, ordVO.getOrd_date());
			pstmt.setDate(10, ordVO.getPay_date());
			pstmt.setDate(11, ordVO.getPay_chk_date());
			pstmt.setDate(12, ordVO.getSend_date());
			pstmt.setString(13, ordVO.getSend_id());
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
	public void update(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STAT);
			
			pstmt.setString(1, ordVO.getOrd_stat());
			pstmt.setDate(2, ordVO.getPay_chk_date());
			pstmt.setDate(3, ordVO.getSend_date());
			pstmt.setString(4, ordVO.getSend_id());
			pstmt.setString(5, ordVO.getOrd_no ());
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
	public void delete(String ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ord_no);

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public OrdVO findByPrimaryKey(String ord_no) {
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ord_no);
			rs=pstmt.executeQuery();
			while (rs.next()){
				ordVO = new  OrdVO();
				ordVO.setOrd_no(rs.getString("ORD_NO"));
				ordVO.setMem_ac(rs.getString("MEM_AC"));
				ordVO.setSend_fee(rs.getInt("SEND_FEE"));
				ordVO.setTotal_pay(rs.getInt("TOTAL_PAY"));
				ordVO.setOrd_name(rs.getString("ORD_NAME"));
				ordVO.setOrd_phone(rs.getString("ORD_PHONE"));
				ordVO.setOrd_add(rs.getString("ORD_ADD"));
				ordVO.setPay_info(rs.getString("PAY_INFO"));
				ordVO.setOrd_stat(rs.getString("ORD_STAT"));
				ordVO.setOrd_date(rs.getDate("ORD_DATE"));
				ordVO.setPay_date(rs.getDate("PAY_DATE"));
				ordVO.setPay_chk_date(rs.getDate("PAY_CHK_DATE"));
				ordVO.setSend_date(rs.getDate("SEND_DATE"));
				ordVO.setSend_id(rs.getString("SEND_ID"));
				
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

		return ordVO;
	}

	@Override
	public Set<OrdVO> getAll() {
		Set<OrdVO> set = new LinkedHashSet<OrdVO>();
		OrdVO ordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()){
				ordVO = new  OrdVO();
				ordVO.setOrd_no(rs.getString("ORD_NO"));
				ordVO.setMem_ac(rs.getString("MEM_AC"));
				ordVO.setSend_fee(rs.getInt("SEND_FEE"));
				ordVO.setTotal_pay(rs.getInt("TOTAL_PAY"));
				ordVO.setOrd_name(rs.getString("ORD_NAME"));
				ordVO.setOrd_phone(rs.getString("ORD_PHONE"));
				ordVO.setOrd_add(rs.getString("ORD_ADD"));
				ordVO.setPay_info(rs.getString("PAY_INFO"));
				ordVO.setOrd_stat(rs.getString("ORD_STAT"));
				ordVO.setOrd_date(rs.getDate("ORD_DATE"));
				ordVO.setPay_date(rs.getDate("PAY_DATE"));
				ordVO.setPay_chk_date(rs.getDate("PAY_CHK_DATE"));
				ordVO.setSend_date(rs.getDate("SEND_DATE"));
				ordVO.setSend_id(rs.getString("SEND_ID"));
				set.add(ordVO);
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
		
		
		return set;
	}
	
	@Override
	public Set<Ord_listVO> getOrd_listByOrd(String ord_no) {
		Set<Ord_listVO> set = new LinkedHashSet<Ord_listVO>();
		Ord_listVO Ord_listVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ORDER_LIST);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();
			while (rs.next()){
				Ord_listVO = new  Ord_listVO();
				Ord_listVO.setOrd_no(rs.getString("ORD_NO"));
				Ord_listVO.setProd_no(rs.getString("PROD_NO"));
				Ord_listVO.setAmont(rs.getInt("AMONT"));
				set.add(Ord_listVO);
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
		return set ;
	}

	public static void main(String[] args) {
		OrdJDBCDAO dao = new OrdJDBCDAO();

		 //新增
//		 OrdVO ordVO1 = new OrdVO();
//		 ordVO1.setMem_ac("dantea");
//		 ordVO1.setSend_fee(80);
//		 ordVO1.setTotal_pay(510);
//		 ordVO1.setOrd_name("萎小寶");
//		 ordVO1.setOrd_phone("0918856413");
//		 ordVO1.setOrd_add("中國天地會總部");
//		 ordVO1.setPay_info("C10102345");
//		 ordVO1.setOrd_stat("已確認付款");
//		 java.util.Date O_DATE = new java.util.Date();
//		 java.sql.Date ORD_DATE = new java.sql.Date(O_DATE.getTime());
//		 ordVO1.setOrd_date(ORD_DATE);
//		 java.util.Date P_DATE = new java.util.Date();
//		 java.sql.Date PAY_DATE = new java.sql.Date(P_DATE.getTime());
//		 ordVO1.setPay_date(PAY_DATE);
//		 java.util.Date C_DATE = new java.util.Date();
//		 java.sql.Date CHK_DATE = new java.sql.Date(C_DATE.getTime());
//		 ordVO1.setPay_chk_date(CHK_DATE);
//		 java.util.Date S_DATE = new java.util.Date();
//		 java.sql.Date SEND_DATE = new java.sql.Date(S_DATE.getTime());
//		 ordVO1.setSend_date(SEND_DATE);
//		 ordVO1.setSend_id("1324567970");
//		 dao.insert(ordVO1);

		// 修改
//		OrdVO ordVO2 = new OrdVO();
//		ordVO2.setOrd_stat("已出貨");
//		java.util.Date S_DATE = new java.util.Date();
//		java.sql.Date PAY_CHK_DATE = new java.sql.Date(S_DATE.getTime());
//		
//		java.sql.Date SEND_DATE = new java.sql.Date(S_DATE.getTime());
//		ordVO2.setPay_chk_date(PAY_CHK_DATE);
//		ordVO2.setSend_date(SEND_DATE);
//		ordVO2.setSend_id("75757576");
//		ordVO2.setOrd_no("O1000000002");
//		dao.update(ordVO2);
		//刪除
//		dao.delete("O1000000013");
		//查詢
//		OrdVO ordVO3 = dao.findByPrimaryKey("O1000000001");
//		System.out.println(ordVO3.getOrd_no());
//		System.out.println(ordVO3.getMem_ac());
//		System.out.println(ordVO3.getSend_fee());
//		System.out.println(ordVO3.getTotal_pay());
//		System.out.println(ordVO3.getOrd_name());
//		System.out.println(ordVO3.getOrd_phone());
//		System.out.println(ordVO3.getOrd_add());
//		System.out.println(ordVO3.getPay_info());
//		System.out.println(ordVO3.getOrd_stat());
//		System.out.println(ordVO3.getOrd_date());
//		System.out.println(ordVO3.getPay_date());
//		System.out.println(ordVO3.getPay_chk_date());
//		System.out.println(ordVO3.getSend_date());
//		System.out.println(ordVO3.getSend_id());
//		System.out.println("---------------------");
//		
		//查詢所有訂單
		Set<OrdVO> set =dao.getAll();
		for(OrdVO aord:set){
			System.out.println(aord.getOrd_no());
			System.out.println(aord.getMem_ac());
			System.out.println(aord.getSend_fee());
			System.out.println(aord.getTotal_pay());
			System.out.println(aord.getOrd_name());
			System.out.println(aord.getOrd_phone());
			System.out.println(aord.getOrd_add());
			System.out.println(aord.getPay_info());
			System.out.println(aord.getOrd_stat());
			System.out.println(aord.getOrd_date());
			System.out.println(aord.getPay_date());
			System.out.println(aord.getPay_chk_date());
			System.out.println(aord.getSend_date());
			System.out.println(aord.getSend_id());
			System.out.println("---------------------");
			
		}
		//查詢某單筆訂單的細目
//		Set<Ord_listVO> set = dao.getOrd_listByOrd("O1000000012");
//		for(Ord_listVO ordlist:set){
//			System.out.println(ordlist.getOrd_no());
//			System.out.println(ordlist.getProd_no());
//			System.out.println(ordlist.getAmont());
//		}
	}

	

}
