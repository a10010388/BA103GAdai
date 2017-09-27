package com.ord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ord_list.model.Ord_listJDBCDAO;
import com.ord_list.model.Ord_listVO;

public class OrdJNDIDAO implements OrdDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ord (ORD_NO,MEM_AC,SEND_FEE,TOTAL_PAY,ORD_NAME,ORD_PHONE,ORD_ADD,"
			+ "PAY_INFO,ORD_STAT,ORD_DATE,PAY_DATE,PAY_CHK_DATE,SEND_DATE,SEND_ID) VALUES ('O'||ORD_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ord order by ORD_DATE desc";
	private static final String GET_ONE_STMT = "SELECT ORD_NO,MEM_AC,SEND_FEE,TOTAL_PAY,ORD_NAME,ORD_PHONE,ORD_ADD,"
			+ "PAY_INFO,ORD_STAT,ORD_DATE,PAY_DATE,PAY_CHK_DATE,SEND_DATE,SEND_ID FROM ord where ORD_NO = ?";
	private static final String DELETE = "DELETE FROM ord where ORD_NO = ?";
	//賣家改狀態
	private static final String UPDATE_STAT = "UPDATE ord set ORD_STAT=?, PAY_CHK_DATE=?, SEND_DATE=? ,SEND_ID=? where ORD_NO = ?";
	//查詢某訂單細目
	private static final String GET_ALL_ORDER_LIST = "select * from ord_list where ORD_NO=?";

	@Override
	public void insert(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
	public String insertWithOrd_list(OrdVO ordVO, Set<Ord_listVO> ord_listVOs) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_ord_no = null;
		try {

			con = ds.getConnection();
			String pk[] = {"ord_no"};
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
    		// 先新增部門	
			pstmt = con.prepareStatement(INSERT_STMT, pk);
			pstmt.setString(1, ordVO.getMem_ac());
			pstmt.setInt(2, ordVO.getSend_fee());
			pstmt.setInt(3, ordVO.getTotal_pay());
			pstmt.setString(4, null);
			pstmt.setString(5, null);
			pstmt.setString(6, null);
			pstmt.setString(7, null);
			pstmt.setString(8, "未付款");
			pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(10, null);
			pstmt.setTimestamp(11, null);
			pstmt.setTimestamp(12, null);
			pstmt.setString(13, null);
			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_ord_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_ord_no +"(剛新增成功的OrdNo)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			Ord_listJDBCDAO dao = new Ord_listJDBCDAO();
			System.out.println("set.size()-A="+ord_listVOs.size());
			for (Ord_listVO ord_listVO : ord_listVOs) {
				ord_listVO.setOrd_no(next_ord_no);
				dao.insertByCon(ord_listVO,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+ord_listVOs.size());
			System.out.println("新增部門編號" + next_ord_no + "時,共有" + ord_listVOs.size()
					+ "訂單項目");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return next_ord_no;
	}

	@Override
	public void update(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STAT);
			pstmt.setString(1, ordVO.getOrd_stat());
			pstmt.setDate(2, ordVO.getPay_chk_date());
			pstmt.setDate(3, ordVO.getSend_date());
			pstmt.setString(4, ordVO.getSend_id());
			pstmt.setString(5, ordVO.getOrd_no ());
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
	public void delete(String Ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, Ord_no);
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
	public OrdVO findByPrimaryKey(String Ord_no) {
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Ord_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordVO = new OrdVO();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordVO = new OrdVO();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDER_LIST);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ord_listVO = new Ord_listVO();
				Ord_listVO.setOrd_no(rs.getString("ORD_NO"));
				Ord_listVO.setProd_no(rs.getString("PROD_NO"));
				Ord_listVO.setAmont(rs.getInt("AMONT"));
				set.add(Ord_listVO);
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
		return set;
	}
}
