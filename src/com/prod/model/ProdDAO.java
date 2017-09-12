package com.prod.model;

import java.sql.Blob;
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

import com.store.model.StoreVO;

public class ProdDAO implements ProdDAO_interface{
	
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		private static final String INSERT_STMT = "INSERT INTO PROD (PROD_NO,STORE_NO,PROD_NAME,BEAN_TYPE,BEAN_GRADE,BEAN_CONTRY,BEAN_REGION,"
				+ "BEAN_FARM,BEAN_FARMER,BEAN_EL,PROC,ROAST,BEAN_ATTR_ACID,BEAN_ATTR_AROMA,"
				+ "BEAN_ATTR_BODY,BEAN_ATTR_AFTER,BEAN_ATTR_BAL,BEAN_AROMA,PROD_PRICE,PROD_WT,"
				+ "SEND_FEE,PROD_SUP,PROD_CONT,PROD_PIC1,PROD_PIC2,PROD_PIC3,PROD_STAT,ED_TIME) VALUES ('P'||PROD_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = "SELECT * FROM PROD order by prod_NO";
		private static final String GET_ONE_STMT = "SELECT PROD_NO,STORE_NO,PROD_NAME,BEAN_TYPE,BEAN_GRADE,BEAN_CONTRY,BEAN_REGION,"
				+ "BEAN_FARM,BEAN_FARMER,BEAN_EL,PROC,ROAST,BEAN_ATTR_ACID,BEAN_ATTR_AROMA,"
				+ "BEAN_ATTR_BODY,BEAN_ATTR_AFTER,BEAN_ATTR_BAL,BEAN_AROMA,PROD_PRICE,PROD_WT,"
				+ "SEND_FEE,PROD_SUP,PROD_CONT,PROD_PIC1,PROD_PIC2,PROD_PIC3,PROD_STAT,ED_TIME FROM PROD where PROD_NO = ?";
		private static final String DELETE = "DELETE FROM PROD where PROD_NO = ?";
		private static final String UPDATE = "UPDATE prod set PROD_NAME=?, BEAN_TYPE=?, BEAN_CONTRY=? where PROD_NO = ?";
	

	@Override
	public void insert(ProdVO prodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, prodVO.getSTORE_NO());
			pstmt.setString(2, prodVO.getPROD_NAME());
			pstmt.setString(3, prodVO.getBEAN_TYPE());
			pstmt.setString(4, prodVO.getBEAN_GRADE());
			pstmt.setString(5, prodVO.getBEAN_CONTRY());
			pstmt.setString(6, prodVO.getBEAN_REGION());
			pstmt.setString(7, prodVO.getBEAN_FARM());
			pstmt.setString(8, prodVO.getBEAN_FARMER());
			pstmt.setInt(9, prodVO.getBEAN_EL());
			pstmt.setString(10, prodVO.getPROC());
			pstmt.setString(11, prodVO.getROAST());
			pstmt.setInt(12, prodVO.getBEAN_ATTR_ACID());
			pstmt.setInt(13, prodVO.getBEAN_ATTR_AROMA());
			pstmt.setInt(14, prodVO.getBEAN_ATTR_BODY());
			pstmt.setInt(15, prodVO.getBEAN_ATTR_AFTER());
			pstmt.setInt(16, prodVO.getBEAN_ATTR_BAL());
			pstmt.setString(17, prodVO.getBEAN_AROMA());
			pstmt.setInt(18, prodVO.getPROD_PRICE());
			pstmt.setInt(19, prodVO.getPROD_WT());
			pstmt.setInt(20, prodVO.getSEND_FEE());
			pstmt.setInt(21, prodVO.getPROD_SUP());
			pstmt.setString(22, prodVO.getPROD_CONT());
			byte[] PROD_PIC1 = prodVO.getPROD_PIC1();
			Blob bolbprod_pic1 = con.createBlob();
			bolbprod_pic1.setBytes(1, PROD_PIC1);
			pstmt.setBlob(23, bolbprod_pic1);
			byte[] PROD_PIC2 = prodVO.getPROD_PIC2();
			Blob bolbprod_pic2 = con.createBlob();
			bolbprod_pic2.setBytes(1, PROD_PIC2);
			pstmt.setBlob(24, bolbprod_pic2);
			byte[] PROD_PIC3 = prodVO.getPROD_PIC3();
			Blob bolbprod_pic3 = con.createBlob();
			bolbprod_pic3.setBytes(1, PROD_PIC3);
			pstmt.setBlob(25, bolbprod_pic3);
			pstmt.setString(26, prodVO.getPROD_STAT());
			pstmt.setDate(27, prodVO.getED_TIME());
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
	public void update(ProdVO prodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, prodVO.getPROD_NAME());
			pstmt.setString(2, prodVO.getBEAN_TYPE());
			pstmt.setString(3, prodVO.getBEAN_CONTRY());
			pstmt.setString(4, prodVO.getPROD_NO());
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
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
	public void delete(String PROD_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, PROD_NO);
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
	public ProdVO findByPrimaryKey(String PROD_NO) {
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, PROD_NO);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setPROD_NO(rs.getString("PROD_NO"));
				prodVO.setSTORE_NO(rs.getString("STORE_NO"));
				prodVO.setPROD_NAME(rs.getString("PROD_NAME"));
				prodVO.setBEAN_TYPE(rs.getString("BEAN_TYPE"));
				prodVO.setBEAN_GRADE(rs.getString("BEAN_GRADE"));
				prodVO.setBEAN_CONTRY(rs.getString("BEAN_CONTRY"));
				prodVO.setBEAN_REGION(rs.getString("BEAN_REGION"));
				prodVO.setBEAN_FARM(rs.getString("BEAN_FARM"));
				prodVO.setBEAN_FARMER(rs.getString("BEAN_FARMER"));
				prodVO.setBEAN_EL(rs.getInt("BEAN_EL"));
				prodVO.setPROC(rs.getString("PROC"));
				prodVO.setROAST(rs.getString("ROAST"));
				prodVO.setBEAN_ATTR_ACID(rs.getInt("BEAN_ATTR_ACID"));
				prodVO.setBEAN_ATTR_AROMA(rs.getInt("BEAN_ATTR_AROMA"));
				prodVO.setBEAN_ATTR_BODY(rs.getInt("BEAN_ATTR_BODY"));
				prodVO.setBEAN_ATTR_AFTER(rs.getInt("BEAN_ATTR_AFTER"));
				prodVO.setBEAN_ATTR_BAL(rs.getInt("BEAN_ATTR_BAL"));
				prodVO.setBEAN_AROMA(rs.getString("BEAN_AROMA"));
				prodVO.setPROD_PRICE(rs.getInt("PROD_PRICE"));
				prodVO.setPROD_WT(rs.getInt("PROD_WT"));
				prodVO.setSEND_FEE(rs.getInt("SEND_FEE"));
				prodVO.setPROD_SUP(rs.getInt("PROD_SUP"));
				prodVO.setPROD_CONT(rs.getString("PROD_CONT"));
				prodVO.setPROD_PIC1(rs.getBytes("PROD_PIC1"));
				prodVO.setPROD_PIC2(rs.getBytes("PROD_PIC2"));
				prodVO.setPROD_PIC3(rs.getBytes("PROD_PIC3"));
				prodVO.setPROD_STAT(rs.getString("PROD_STAT"));
				prodVO.setED_TIME(rs.getDate("ED_TIME"));

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
		return prodVO;
	}

	@Override
	public List<ProdVO> getAll() {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				prodVO = new ProdVO();
				prodVO.setPROD_NO(rs.getString("PROD_NO"));
				prodVO.setSTORE_NO(rs.getString("STORE_NO"));
				prodVO.setPROD_NAME(rs.getString("PROD_NAME"));
				prodVO.setBEAN_TYPE(rs.getString("BEAN_TYPE"));
				prodVO.setBEAN_GRADE(rs.getString("BEAN_GRADE"));
				prodVO.setBEAN_CONTRY(rs.getString("BEAN_CONTRY"));
				prodVO.setBEAN_REGION(rs.getString("BEAN_REGION"));
				prodVO.setBEAN_FARM(rs.getString("BEAN_FARM"));
				prodVO.setBEAN_FARMER(rs.getString("BEAN_FARMER"));
				prodVO.setBEAN_EL(rs.getInt("BEAN_EL"));
				prodVO.setPROC(rs.getString("PROC"));
				prodVO.setROAST(rs.getString("ROAST"));
				prodVO.setBEAN_ATTR_ACID(rs.getInt("BEAN_ATTR_ACID"));
				prodVO.setBEAN_ATTR_AROMA(rs.getInt("BEAN_ATTR_AROMA"));
				prodVO.setBEAN_ATTR_BODY(rs.getInt("BEAN_ATTR_BODY"));
				prodVO.setBEAN_ATTR_AFTER(rs.getInt("BEAN_ATTR_AFTER"));
				prodVO.setBEAN_ATTR_BAL(rs.getInt("BEAN_ATTR_BAL"));
				prodVO.setBEAN_AROMA(rs.getString("BEAN_AROMA"));
				prodVO.setPROD_PRICE(rs.getInt("PROD_PRICE"));
				prodVO.setPROD_WT(rs.getInt("PROD_WT"));
				prodVO.setSEND_FEE(rs.getInt("SEND_FEE"));
				prodVO.setPROD_SUP(rs.getInt("PROD_SUP"));
				prodVO.setPROD_CONT(rs.getString("PROD_CONT"));
				prodVO.setPROD_PIC1(rs.getBytes("PROD_PIC1"));
				prodVO.setPROD_PIC2(rs.getBytes("PROD_PIC2"));
				prodVO.setPROD_PIC3(rs.getBytes("PROD_PIC3"));
				prodVO.setPROD_STAT(rs.getString("PROD_STAT"));
				prodVO.setED_TIME(rs.getDate("ED_TIME"));
				list.add(prodVO);

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
