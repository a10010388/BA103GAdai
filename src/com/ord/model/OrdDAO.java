package com.ord.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdDAO implements OrdDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ordVO.getORD_NAME());
			pstmt.setString(2, ordVO.getORD_PHONE());
			pstmt.setString(3, ordVO.getORD_ADD());
			pstmt.setString(4, ordVO.getORD_NO());
			pstmt.executeUpdate();
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ORD_NO);
			pstmt.executeUpdate();
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
			con =ds.getConnection();
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
			con =ds.getConnection();
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

}
