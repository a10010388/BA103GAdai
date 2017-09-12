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
import java.util.List;



public class OrdJDBCDAO implements OrdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "test";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ord (ORD_NO,MEM_AC,SEND_FEE,TOTAL_PAY,ORD_NAME,ORD_PHONE,ORD_ADD,"
			+ "PAY_INFO,ORD_STAT,ORD_DATE,PAY_DATE,PAY_CHK_DATE,SEND_DATE,SEND_ID) VALUES ('O'||ORD_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ord order by ORD_NO";
	private static final String GET_ONE_STMT = "SELECT ORD_NO,MEM_AC,SEND_FEE,TOTAL_PAY,ORD_NAME,ORD_PHONE,ORD_ADD,"
			+ "PAY_INFO,ORD_STAT,ORD_DATE,PAY_DATE,PAY_CHK_DATE,SEND_DATE,SEND_ID FROM ord where ORD_NO = ?";
	private static final String DELETE = "DELETE FROM ord where ORD_NO = ?";
	private static final String UPDATE = "UPDATE ord set ORD_NAME=?, ORD_PHONE=?, ORD_ADD=? where ORD_NO = ?";

	@Override
	public void insert(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ordVO.getMEM_AC());
			pstmt.setInt(2, ordVO.getSEND_FEE());
			pstmt.setInt(3, ordVO.getTOTAL_PAY());
			pstmt.setString(4, ordVO.getORD_NAME());
			pstmt.setString(5, ordVO.getORD_PHONE());
			pstmt.setString(6, ordVO.getORD_ADD());
			pstmt.setString(7, ordVO.getPAY_INFO());
			pstmt.setString(8, ordVO.getORD_STAT());
			pstmt.setDate(9, ordVO.getORD_DATE());
			pstmt.setDate(10, ordVO.getPAY_DATE());
			pstmt.setDate(11, ordVO.getPAY_CHK_DATE());
			pstmt.setDate(12, ordVO.getSEND_DATE());
			pstmt.setString(13, ordVO.getSEND_ID());
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
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ordVO.getORD_NAME());
			pstmt.setString(2, ordVO.getORD_PHONE());
			pstmt.setString(3, ordVO.getORD_ADD());
			pstmt.setString(4, ordVO.getORD_NO());
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
	public void delete(String ORD_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ORD_NO);

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
	public OrdVO findByPrimaryKey(String ORD_NO) {
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ORD_NO);
			rs=pstmt.executeQuery();
			while (rs.next()){
				ordVO = new  OrdVO();
				ordVO.setORD_NO(rs.getString("ORD_NO"));
				ordVO.setMEM_AC(rs.getString("MEM_AC"));
				ordVO.setSEND_FEE(rs.getInt("SEND_FEE"));
				ordVO.setTOTAL_PAY(rs.getInt("TOTAL_PAY"));
				ordVO.setORD_NAME(rs.getString("ORD_NAME"));
				ordVO.setORD_PHONE(rs.getString("ORD_PHONE"));
				ordVO.setORD_ADD(rs.getString("ORD_ADD"));
				ordVO.setPAY_INFO(rs.getString("PAY_INFO"));
				ordVO.setORD_STAT(rs.getString("ORD_STAT"));
				ordVO.setORD_DATE(rs.getDate("ORD_DATE"));
				ordVO.setPAY_DATE(rs.getDate("PAY_DATE"));
				ordVO.setPAY_CHK_DATE(rs.getDate("PAY_CHK_DATE"));
				ordVO.setSEND_DATE(rs.getDate("SEND_DATE"));
				ordVO.setSEND_ID(rs.getString("SEND_ID"));
				
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
	public List<OrdVO> getAll() {
		List<OrdVO> list = new ArrayList<OrdVO>();
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
				ordVO.setORD_NO(rs.getString("ORD_NO"));
				ordVO.setMEM_AC(rs.getString("MEM_AC"));
				ordVO.setSEND_FEE(rs.getInt("SEND_FEE"));
				ordVO.setTOTAL_PAY(rs.getInt("TOTAL_PAY"));
				ordVO.setORD_NAME(rs.getString("ORD_NAME"));
				ordVO.setORD_PHONE(rs.getString("ORD_PHONE"));
				ordVO.setORD_ADD(rs.getString("ORD_ADD"));
				ordVO.setPAY_INFO(rs.getString("PAY_INFO"));
				ordVO.setORD_STAT(rs.getString("ORD_STAT"));
				ordVO.setORD_DATE(rs.getDate("ORD_DATE"));
				ordVO.setPAY_DATE(rs.getDate("PAY_DATE"));
				ordVO.setPAY_CHK_DATE(rs.getDate("PAY_CHK_DATE"));
				ordVO.setSEND_DATE(rs.getDate("SEND_DATE"));
				ordVO.setSEND_ID(rs.getString("SEND_ID"));
				list.add(ordVO);
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
		
		
		return list;
	}

	public static void main(String[] args) {
		OrdJDBCDAO dao = new OrdJDBCDAO();

		 //新增
//		 OrdVO ordVO1 = new OrdVO();
//		 ordVO1.setMEM_AC("dantea");
//		 ordVO1.setSEND_FEE(80);
//		 ordVO1.setTOTAL_PAY(510);
//		 ordVO1.setORD_NAME("萎小寶");
//		 ordVO1.setORD_PHONE("0918856413");
//		 ordVO1.setORD_ADD("中國天地會總部");
//		 ordVO1.setPAY_INFO("C10102345");
//		 ordVO1.setORD_STAT("已確認付款");
//		 java.util.Date O_DATE = new java.util.Date();
//		 java.sql.Date ORD_DATE = new java.sql.Date(O_DATE.getTime());
//		 ordVO1.setORD_DATE(ORD_DATE);
//		 java.util.Date P_DATE = new java.util.Date();
//		 java.sql.Date PAY_DATE = new java.sql.Date(P_DATE.getTime());
//		 ordVO1.setPAY_DATE(PAY_DATE);
//		 java.util.Date C_DATE = new java.util.Date();
//		 java.sql.Date CHK_DATE = new java.sql.Date(C_DATE.getTime());
//		 ordVO1.setPAY_CHK_DATE(CHK_DATE);
//		 java.util.Date S_DATE = new java.util.Date();
//		 java.sql.Date SEND_DATE = new java.sql.Date(S_DATE.getTime());
//		 ordVO1.setSEND_DATE(SEND_DATE);
//		 ordVO1.setSEND_ID("1324567970");
//		 dao.insert(ordVO1);

		// 修改
//		OrdVO ordVO2 = new OrdVO();
//		ordVO2.setORD_NAME("神雕大俠");
//		ordVO2.setORD_PHONE("0935882186");
//		ordVO2.setORD_ADD("終南山古墓派");
//		ordVO2.setORD_NO("O1000000003");
//		dao.update(ordVO2);
		//刪除
//		dao.delete("O1000000013");
		//查詢
//		OrdVO ordVO3 = dao.findByPrimaryKey("O1000000001");
//		System.out.println(ordVO3.getORD_NO());
//		System.out.println(ordVO3.getMEM_AC());
//		System.out.println(ordVO3.getSEND_FEE());
//		System.out.println(ordVO3.getTOTAL_PAY());
//		System.out.println(ordVO3.getORD_NAME());
//		System.out.println(ordVO3.getORD_PHONE());
//		System.out.println(ordVO3.getORD_ADD());
//		System.out.println(ordVO3.getPAY_INFO());
//		System.out.println(ordVO3.getORD_STAT());
//		System.out.println(ordVO3.getORD_DATE());
//		System.out.println(ordVO3.getPAY_DATE());
//		System.out.println(ordVO3.getPAY_CHK_DATE());
//		System.out.println(ordVO3.getSEND_DATE());
//		System.out.println(ordVO3.getSEND_ID());
//		System.out.println("---------------------");
		
		List<OrdVO> list =dao.getAll();
		for(OrdVO aord:list){
			System.out.println(aord.getORD_NO());
			System.out.println(aord.getMEM_AC());
			System.out.println(aord.getSEND_FEE());
			System.out.println(aord.getTOTAL_PAY());
			System.out.println(aord.getORD_NAME());
			System.out.println(aord.getORD_PHONE());
			System.out.println(aord.getORD_ADD());
			System.out.println(aord.getPAY_INFO());
			System.out.println(aord.getORD_STAT());
			System.out.println(aord.getORD_DATE());
			System.out.println(aord.getPAY_DATE());
			System.out.println(aord.getPAY_CHK_DATE());
			System.out.println(aord.getSEND_DATE());
			System.out.println(aord.getSEND_ID());
			System.out.println("---------------------");
			
		}
		
		
	}

}
