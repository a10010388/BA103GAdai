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
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G4DB");
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
	private static final String UPDATE = "UPDATE store set TAX_ID_NO=?, WIN_ID_PIC=?, STORE_PHONE=? ,STORE_ADD=?,STORE_ADD_LAT=?,STORE_ADD_LON=?,STORE_NAME=?,STORE_CONT=?,STORE_PIC1=?,STORE_PIC2=?,STORE_PIC3=?,STORE_FREE_SHIP=?,STORE_STAT_CONT=?,STORE_STAT_CDATE=?  where STORE_NO = ?";
	private static final String UPDATE_STAT ="UPDATE store set STORE_STAT=?,store_stat_cdate=? where STORE_NO = ?";

	@Override
	public void insert(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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

			con = ds.getConnection();
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
			pstmt.setString(13, storeVO.getStore_stat());
			pstmt.setString(14, storeVO.getStore_cont());
			pstmt.setDate(15, storeVO.getStore_stat_cdate());
			pstmt.setString(16, storeVO.getStore_no());
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
	public void update_stat(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STAT);
			
			pstmt.setString(1, storeVO.getStore_stat());
			
			pstmt.setDate(2, storeVO.getStore_stat_cdate());
			pstmt.setString(3, storeVO.getStore_no());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
