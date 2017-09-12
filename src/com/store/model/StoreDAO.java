package com.store.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StoreDAO implements StoreDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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
			con = ds.getConnection();
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
	public void update(StoreVO StoreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setString(1, StoreVO.getSTORE_NAME());
			pstmt.setString(2, StoreVO.getSTORE_PHONE());
			pstmt.setString(3, StoreVO.getSTORE_ADD());
			pstmt.setString(4, StoreVO.getSTORE_NO());

			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, STORE_NO);

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
	public StoreVO findByPrimaryKey(String STORE_NO) {
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
