package com.ord_list.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ord_listJDBCDAO implements Ord_list_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "test";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Ord_list (ORD_NO,PROD_NO,AMONT) VALUES ('O1000000005', 'P1000000009', ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Ord_list order by ORD_NO";
	private static final String GET_ONE_STMT = "SELECT ORD_NO,PROD_NO,AMONT FROM Ord_list where ORD_NO = ?  ";
	private static final String DELETE = "DELETE FROM Ord_list where ORD_NO = ?";
	private static final String UPDATE = "UPDATE Ord_list set AMONT=? where ORD_NO = ? and PROD_NO = ? ";

	@Override
	public void insert(Ord_listVO ord_listvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, ord_listvo.getAMONT());
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
	public void update(Ord_listVO ord_listvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ord_listvo.getAMONT());
			pstmt.setString(2, ord_listvo.getORD_NO());
			pstmt.setString(3, ord_listvo.getPROD_NO());
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
	public void delete(String ord_list_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ord_list_no);

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
	public Ord_listVO findByPrimaryKey(String ord_list_no) {
		Ord_listVO ord_listvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ord_list_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ord_listvo = new Ord_listVO();
				ord_listvo.setORD_NO(rs.getString("ORD_NO"));
				ord_listvo.setPROD_NO(rs.getString("PROD_NO"));
				ord_listvo.setAMONT(rs.getInt("AMONT"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ord_listvo;
	}

	@Override
	public List<Ord_listVO> getAll() {
		List<Ord_listVO> list = new ArrayList<>();
		Ord_listVO ord_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ord_listVO = new Ord_listVO();
				ord_listVO.setORD_NO(rs.getString("ORD_NO"));
				ord_listVO.setPROD_NO(rs.getString("PROD_NO"));
				ord_listVO.setAMONT(rs.getInt("AMONT"));
				list.add(ord_listVO);
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
		Ord_listJDBCDAO dao = new Ord_listJDBCDAO();
		// 新增
		// Ord_listVO ord_listVO1 = new Ord_listVO();
		// ord_listVO1.setAMONT(3);
		// dao.insert(ord_listVO1);
		// 修改
		// Ord_listVO ord_listVO2 = new Ord_listVO();
		// ord_listVO2.setAMONT(8);
		// ord_listVO2.setORD_NO("O1000000005");
		// ord_listVO2.setPROD_NO("P1000000006");
		// dao.update(ord_listVO2);
		// 刪除
		// dao.delete("O1000000005");
		// 查詢
//		Ord_listVO ord_listVO3 = dao.findByPrimaryKey("O1000000005");
//		System.out.println(ord_listVO3.getORD_NO());
//		System.out.println(ord_listVO3.getPROD_NO());
//		System.out.println(ord_listVO3.getAMONT());
//		System.out.println("---------------------");
		// 查詢
		List<Ord_listVO> list = dao.getAll();
		for (Ord_listVO aord_list : list) {
			System.out.println(aord_list.getORD_NO());
			System.out.println(aord_list.getPROD_NO());
			System.out.println(aord_list.getAMONT());
			System.out.println("---------------------");
		}

	}

}
