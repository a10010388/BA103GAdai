package com.prod.model;

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

public class ProdJDBCDAO implements ProdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "test";
	String passwd = "123456";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(ProdVO prodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, prodVO.getPROD_NAME());
			pstmt.setString(2, prodVO.getBEAN_TYPE());
			pstmt.setString(3, prodVO.getBEAN_CONTRY());
			pstmt.setString(4, prodVO.getPROD_NO());
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
	public void delete(String PROD_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, PROD_NO);
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
	public ProdVO findByPrimaryKey(String PROD_NO) {
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		ProdJDBCDAO dao = new ProdJDBCDAO();
		// 新增
		 ProdVO prodvo1= new ProdVO();
		
		 prodvo1.setSTORE_NO("S1000000001");
		 prodvo1.setPROD_NAME("巴布紐幾內亞, 維基谷地, 天堂鳥莊園AA");
		 prodvo1.setBEAN_TYPE("卡杜拉 Caturra");
		 prodvo1.setBEAN_GRADE("G1");
		 prodvo1.setBEAN_CONTRY("巴布紐幾內亞");
		 prodvo1.setBEAN_REGION("維基谷地");
		 prodvo1.setBEAN_FARM("天堂鳥莊園");
		 prodvo1.setBEAN_FARMER("123");
		 prodvo1.setBEAN_EL(1999);
		 prodvo1.setPROC("日曬");
		 prodvo1.setROAST("深焙");
		 prodvo1.setBEAN_ATTR_ACID(1);
		 prodvo1.setBEAN_ATTR_AROMA(2);
		 prodvo1.setBEAN_ATTR_BODY(3);
		 prodvo1.setBEAN_ATTR_AFTER(4);
		 prodvo1.setBEAN_ATTR_BAL(5);
		 prodvo1.setBEAN_AROMA("臭水溝味道");
		 prodvo1.setPROD_PRICE(599);
		 prodvo1.setPROD_WT(1);
		 prodvo1.setSEND_FEE(100);
		 prodvo1.setPROD_SUP(200);
		 prodvo1.setPROD_CONT("濃厚的化糞池味道");
		 byte [] PROD_PIC1 =getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\咖啡豆1.jpg");
		 prodvo1.setPROD_PIC1(PROD_PIC1);
		 byte [] PROD_PIC2 =getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\咖啡豆2.jpg");
		 prodvo1.setPROD_PIC2(PROD_PIC2);
		 byte [] PROD_PIC3 =getPictureByteArray("C:\\Users\\Java\\Desktop\\photo\\picFrom\\咖啡豆2.jpg");
		 prodvo1.setPROD_PIC3(PROD_PIC3);
		 prodvo1.setPROD_STAT("下架");
		 java.util.Date E_TIME = new java.util.Date();
		 java.sql.Date ED_TIME = new java.sql.Date(E_TIME.getTime());
		 prodvo1.setED_TIME(ED_TIME);
		 dao.insert(prodvo1);

		// 修改
//		 ProdVO prodvo2= new ProdVO();
//		 prodvo2.setPROD_NAME("貓大便咖啡");
//		 prodvo2.setBEAN_TYPE("麝香貓大便豆");
//		 prodvo2.setBEAN_CONTRY("強國玻璃心");
//		 prodvo2.setPROD_NO("P1000000013");
//		 dao.update(prodvo2);

		// 刪除
//		 dao.delete("P1000000012");
		// 查詢
//		ProdVO prodVO3 = dao.findByPrimaryKey("P1000000001");
//		System.out.println(prodVO3.getPROD_NO() + ",");
//		System.out.println(prodVO3.getSTORE_NO() + ",");
//		System.out.println(prodVO3.getPROD_NAME() + ",");
//		System.out.println(prodVO3.getBEAN_TYPE() + ",");
//		System.out.println(prodVO3.getBEAN_GRADE() + ",");
//		System.out.println(prodVO3.getBEAN_CONTRY() + ",");
//		System.out.println(prodVO3.getBEAN_REGION() + ",");
//		System.out.println(prodVO3.getBEAN_FARM() + ",");
//		System.out.println(prodVO3.getBEAN_FARMER() + ",");
//		System.out.println(prodVO3.getBEAN_EL() + ",");
//		System.out.println(prodVO3.getPROC() + ",");
//		System.out.println(prodVO3.getROAST() + ",");
//		System.out.println(prodVO3.getBEAN_ATTR_ACID() + ",");
//		System.out.println(prodVO3.getBEAN_ATTR_AROMA() + ",");
//		System.out.println(prodVO3.getBEAN_ATTR_BODY() + ",");
//		System.out.println(prodVO3.getBEAN_ATTR_AFTER() + ",");
//		System.out.println(prodVO3.getBEAN_ATTR_BAL() + ",");
//		System.out.println(prodVO3.getBEAN_AROMA() + ",");
//		System.out.println(prodVO3.getPROD_PRICE() + ",");
//		System.out.println(prodVO3.getPROD_WT() + ",");
//		System.out.println(prodVO3.getSEND_FEE() + ",");
//		System.out.println(prodVO3.getPROD_SUP() + ",");
//		System.out.println(prodVO3.getPROD_CONT() + ",");
//		System.out.println(prodVO3.getPROD_PIC1() + ",");
//		System.out.println(prodVO3.getPROD_PIC2() + ",");
//		System.out.println(prodVO3.getPROD_PIC3() + ",");
//		System.out.println(prodVO3.getPROD_STAT() + ",");
//		System.out.println(prodVO3.getED_TIME());
//		System.out.println("---------------------");
		// 查詢
//		 List<ProdVO> list =dao.getAll();
//		 for (ProdVO aprod:list){
//		 System.out.println(aprod.getPROD_NO());
//		 System.out.println(aprod.getSTORE_NO());
//		 System.out.println(aprod.getPROD_NAME());
//		 System.out.println(aprod.getBEAN_TYPE());
//		 System.out.println(aprod.getBEAN_GRADE());
//		 System.out.println(aprod.getBEAN_CONTRY());
//		 System.out.println(aprod.getBEAN_REGION());
//		 System.out.println(aprod.getBEAN_FARM());
//		 System.out.println(aprod.getBEAN_FARMER());
//		 System.out.println(aprod.getBEAN_EL());
//		 System.out.println(aprod.getPROC());
//		 System.out.println(aprod.getROAST());
//		 System.out.println(aprod.getBEAN_ATTR_ACID());
//		 System.out.println(aprod.getBEAN_ATTR_AROMA());
//		 System.out.println(aprod.getBEAN_ATTR_BODY());
//		 System.out.println(aprod.getBEAN_ATTR_AFTER());
//		 System.out.println(aprod.getBEAN_ATTR_BAL());
//		 System.out.println(aprod.getBEAN_AROMA());
//		 System.out.println(aprod.getPROD_PRICE());
//		 System.out.println(aprod.getPROD_WT());
//		 System.out.println(aprod.getSEND_FEE());
//		 System.out.println(aprod.getPROD_SUP());
//		 System.out.println(aprod.getPROD_CONT());
//		 System.out.println(aprod.getPROD_PIC1());
//		 System.out.println(aprod.getPROD_PIC2());
//		 System.out.println(aprod.getPROD_PIC3());
//		 System.out.println(aprod.getPROD_STAT());
//		 System.out.println(aprod.getED_TIME());
//		 System.out.println("---------------------");
//		 }
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
