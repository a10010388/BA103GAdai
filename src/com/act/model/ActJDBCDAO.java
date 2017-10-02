package com.act.model;

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
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.act_comm.model.Act_commVO;
import com.act_pair.model.Act_pairVO;
import com.fo_act.model.Fo_actVO;

public class ActJDBCDAO implements ActDAO_interface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String userid="ba103g4";
	String password="123456";
	
	private static final String INSERT_STMT ="insert into act values('A' || act_no_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT ="select * from act";
	private static final String GET_ONE_STMT="select * from act where ACT_NO=?";
	private static final String DELETE_ACT = "delete from act where act_no=?";
	private static final String UPDATE ="update act set MEM_AC=?,ORG_CONT=?,ACT_NAME=?,MIN_MEM=?,MAX_MEM=?,MEM_COUNT=?,ACT_OP_DATE=?,ACT_ED_DATE=?,DL_DATE=?,FD_DATE=?,ACT_ADD=?, ACT_ADD_LAT=?,ACT_ADD_LON=?,ACT_CONT=?,ACT_TAG=?,ACT_FEE=?,PAY_WAY=?,ACT_PIC1=?,ACT_PIC2=?,ACT_PIC3=?,ACT_STAT=?,RE_CONT=?,REVIEW_ED_DATE=?where act_no=?";
	
	private static final String DELETE_ACT_COMM="delete from act_comm where act_no=?";
	private static final String DELETE_ACT_PAIR="delete from act_pair where act_no=?";
	private static final String DELETE_FO_ACT="delete from fo_act where act_no=?";
	private static final String GET_ACT_COMM_ByAct_no_STMT="SELECT * FROM ACT_COMM WHERE ACT_NO=? ORDER BY ACT_NO";
	private static final String GET_ACT_PAIR_ByAct_no_STMT="SELECT * FROM ACT_PAIR WHERE ACT_NO=? ORDER BY ACT_NO";
	private static final String GET_FO_ACT_ByAct_no_STMT="SELECT * FROM FO_ACT WHERE ACT_NO=? ORDER BY ACT_NO";
	
	
	@Override
	public void insert(ActVO act_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
	
				con = DriverManager.getConnection(url, userid, password);
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1,act_VO.getMem_ac());
				pstmt.setString(2,act_VO.getOrg_cont());
				pstmt.setString(3,act_VO.getAct_name());
				pstmt.setInt(4,act_VO.getMin_mem());
				pstmt.setInt(5,act_VO.getMax_mem());
				pstmt.setInt(6,act_VO.getMem_count());
				pstmt.setDate(7,act_VO.getAct_op_date());
				pstmt.setDate(8,act_VO.getAct_ed_date());
				pstmt.setDate(9,act_VO.getDl_date());
				pstmt.setDate(10,act_VO.getFd_date());
				pstmt.setString(11,act_VO.getAct_add());
				pstmt.setString(12,act_VO.getAct_add_lat());
				pstmt.setString(13,act_VO.getAct_add_lon());
				pstmt.setString(14,act_VO.getAct_cont());
				pstmt.setString(15,act_VO.getAct_tag());
				pstmt.setInt(16,act_VO.getAct_fee());
				pstmt.setString(17,act_VO.getPay_way());
				byte[] pic1=act_VO.getAct_pic1();
				byte[] pic2=act_VO.getAct_pic2();
				byte[] pic3=act_VO.getAct_pic3();
				Blob blob1=con.createBlob();
				Blob blob2=con.createBlob();
				Blob blob3=con.createBlob();
				blob1.setBytes(1, pic1);
				blob2.setBytes(1, pic2);
				blob3.setBytes(1, pic3);
				pstmt.setBlob(18,blob1);
				pstmt.setBlob(19,blob2);
				pstmt.setBlob(20,blob3);
				pstmt.setString(21,act_VO.getAct_stat());
				pstmt.setString(22,act_VO.getRe_cont());
				pstmt.setDate(23,act_VO.getReview_ed_date());
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		
		
	}

	@Override
	public void update(ActVO act_VO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			
				con = DriverManager.getConnection(url, userid, password);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1,act_VO.getMem_ac());
				pstmt.setString(2,act_VO.getOrg_cont());
				pstmt.setString(3,act_VO.getAct_name());
				pstmt.setInt(4,act_VO.getMin_mem());
				pstmt.setInt(5,act_VO.getMax_mem());
				pstmt.setInt(6,act_VO.getMem_count());
				pstmt.setDate(7,act_VO.getAct_op_date());
				pstmt.setDate(8,act_VO.getAct_ed_date());
				pstmt.setDate(9,act_VO.getDl_date());
				pstmt.setDate(10,act_VO.getFd_date());
				pstmt.setString(11,act_VO.getAct_add());
				pstmt.setString(12,act_VO.getAct_add_lat());
				pstmt.setString(13,act_VO.getAct_add_lon());
				pstmt.setString(14,act_VO.getAct_cont());
				pstmt.setString(15,act_VO.getAct_tag());
				pstmt.setInt(16,act_VO.getAct_fee());
				pstmt.setString(17,act_VO.getPay_way());
				byte[] pic1=act_VO.getAct_pic1();
				byte[] pic2=act_VO.getAct_pic2();
				byte[] pic3=act_VO.getAct_pic3();
				Blob blob1=con.createBlob();
				Blob blob2=con.createBlob();
				Blob blob3=con.createBlob();
				blob1.setBytes(1, pic1);
				blob2.setBytes(1, pic2);
				blob3.setBytes(1, pic3);	
				pstmt.setBlob(18,blob1);
				pstmt.setBlob(19,blob2);
				pstmt.setBlob(20,blob3);
				pstmt.setString(21,act_VO.getAct_stat());
				pstmt.setString(22,act_VO.getRe_cont());
				pstmt.setDate(23,act_VO.getReview_ed_date());
				pstmt.setString(24,act_VO.getAct_no());
				pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
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
	public void delete(String ACT_NO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
		
				con = DriverManager.getConnection(url, userid, password);
				con.setAutoCommit(false);
				pstmt=con.prepareStatement(DELETE_ACT_COMM);
				pstmt.setString(1, ACT_NO);
				pstmt.executeUpdate();
				
				pstmt=con.prepareStatement(DELETE_ACT_PAIR);
				pstmt.setString(1, ACT_NO);
				pstmt.executeUpdate();
				
				pstmt=con.prepareStatement(DELETE_FO_ACT);
				pstmt.setString(1, ACT_NO);
				pstmt.executeUpdate();
				pstmt=con.prepareStatement(DELETE_ACT);
				pstmt.setString(1, ACT_NO);
				pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				
				if (con != null) {
					try {
						
						// 3●設定於當有exception發生時之catch區塊內
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} catch (ClassNotFoundException e) {
				
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			}finally{
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}

	@Override
	public ActVO findByPrimaryKey(String ACT_NO) {
		ActVO act_vo=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
		
				con = DriverManager.getConnection(url, userid, password);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1, ACT_NO);
				rs=pstmt.executeQuery();
			while(rs.next()){
				act_vo=new ActVO();
				act_vo.setAct_no(rs.getString("ACT_NO"));
				act_vo.setMem_ac(rs.getString("MEM_AC"));
				act_vo.setOrg_cont(rs.getString("ORG_CONT"));
				act_vo.setAct_name(rs.getString("ACT_NAME"));
				act_vo.setMin_mem(rs.getInt("MIN_MEM"));
				act_vo.setMax_mem(rs.getInt("MAX_MEM"));
				act_vo.setMem_count(rs.getInt("MEM_COUNT"));
				act_vo.setAct_op_date(rs.getDate("ACT_OP_DATE"));
				act_vo.setAct_ed_date(rs.getDate("ACT_ED_DATE"));
				act_vo.setDl_date(rs.getDate("DL_DATE"));
				act_vo.setFd_date(rs.getDate("FD_DATE"));
				act_vo.setAct_add(rs.getString("ACT_ADD"));
				act_vo.setAct_add_lat(rs.getString("ACT_ADD_LAT"));
				act_vo.setAct_add_lon(rs.getString("ACT_ADD_LON"));
				act_vo.setAct_cont(rs.getString("ACT_CONT"));
				act_vo.setAct_tag(rs.getString("ACT_TAG"));
				act_vo.setAct_fee(rs.getInt("ACT_FEE"));
				act_vo.setPay_way(rs.getString("PAY_WAY"));
			
				act_vo.setAct_pic1(rs.getBytes("ACT_PIC1"));
				act_vo.setAct_pic2(rs.getBytes("ACT_PIC2"));
				act_vo.setAct_pic3(rs.getBytes("ACT_PIC3"));
				act_vo.setAct_stat(rs.getString("ACT_STAT"));
				act_vo.setRe_cont(rs.getString("RE_CONT"));
				act_vo.setReview_ed_date(rs.getDate("REVIEW_ED_DATE"));
				
			}
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

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
		return act_vo;
		
	}

	@Override
	public List<ActVO> getAll() {
		List<ActVO> list=new ArrayList<ActVO>();
		ActVO act_vo=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName(driver);
			
				con = DriverManager.getConnection(url, userid, password);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs=pstmt.executeQuery();
				while(rs.next()){
					act_vo=new ActVO();
					act_vo.setAct_no(rs.getString("ACT_NO"));
					act_vo.setMem_ac(rs.getString("MEM_AC"));
					act_vo.setOrg_cont(rs.getString("ORG_CONT"));
					act_vo.setAct_name(rs.getString("ACT_NAME"));
					act_vo.setMin_mem(rs.getInt("MIN_MEM"));
					act_vo.setMax_mem(rs.getInt("MAX_MEM"));
					act_vo.setMem_count(rs.getInt("MEM_COUNT"));
					act_vo.setAct_op_date(rs.getDate("ACT_OP_DATE"));
					act_vo.setAct_ed_date(rs.getDate("ACT_ED_DATE"));
					act_vo.setDl_date(rs.getDate("DL_DATE"));
					act_vo.setFd_date(rs.getDate("FD_DATE"));
					act_vo.setAct_add(rs.getString("ACT_ADD"));
					act_vo.setAct_add_lat(rs.getString("ACT_ADD_LAT"));
					act_vo.setAct_add_lon(rs.getString("ACT_ADD_LON"));
					act_vo.setAct_cont(rs.getString("ACT_CONT"));
					act_vo.setAct_tag(rs.getString("ACT_TAG"));
					act_vo.setAct_fee(rs.getInt("ACT_FEE"));
					act_vo.setPay_way(rs.getString("PAY_WAY"));
				
					act_vo.setAct_pic1(rs.getBytes("ACT_PIC1"));
					act_vo.setAct_pic2(rs.getBytes("ACT_PIC2"));
					act_vo.setAct_pic3(rs.getBytes("ACT_PIC3"));
					act_vo.setAct_stat(rs.getString("ACT_STAT"));
					act_vo.setRe_cont(rs.getString("RE_CONT"));
					act_vo.setReview_ed_date(rs.getDate("REVIEW_ED_DATE"));
					
					list.add(act_vo);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	@Override
	public Set<Act_commVO> getAct_commByAct_no(String ACT_NO) {
		Set<Act_commVO> set=new LinkedHashSet<Act_commVO>();
		Act_commVO act_commVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);

				con = DriverManager.getConnection(url, userid, password);
				pstmt=con.prepareStatement(GET_ACT_COMM_ByAct_no_STMT);
				pstmt.setString(1, ACT_NO);
				rs=pstmt.executeQuery();
				while(rs.next()){
					act_commVO=new Act_commVO();
					act_commVO.setComm_no(rs.getString("COMM_NO"));
					act_commVO.setAct_no(rs.getString("ACT_NO"));
					act_commVO.setMem_ac(rs.getString("MEM_AC"));
					act_commVO.setComm_cont(rs.getString("COMM_CONT"));
					act_commVO.setComm_date(rs.getDate("COMM_DATE"));
					act_commVO.setComm_reply_cont(rs.getString("COMM_REPLY_CONT"));
					act_commVO.setComm_reply_date(rs.getDate("COMM_REPLY_DATE"));
					set.add(act_commVO);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

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
	public Set<Act_pairVO> getAct_pairByAct_no(String ACT_NO) {
		 Set<Act_pairVO> set=new  LinkedHashSet<Act_pairVO>();
		 Act_pairVO act_pair_vo;
		 Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
		
					con = DriverManager.getConnection(url, userid, password);
					pstmt=con.prepareStatement(GET_ACT_PAIR_ByAct_no_STMT);
					pstmt.setString(1, ACT_NO);
					rs=pstmt.executeQuery();
					while(rs.next()){
						 act_pair_vo=new Act_pairVO();
						 act_pair_vo.setAct_no(rs.getString("ACT_NO"));
						 act_pair_vo.setMem_ac(rs.getString("MEM_AC"));
						 act_pair_vo.setApply_date(rs.getDate("APPLY_DATE"));
						 act_pair_vo.setPay_state(rs.getString("PAY_STATE"));
						 act_pair_vo.setChk_state(rs.getString("CHK_STATE"));
						set.add(act_pair_vo);
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
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
	public Set<Fo_actVO> getFo_actByAct_no(String ACT_NO) {
		Set<Fo_actVO> set=new  LinkedHashSet<Fo_actVO>();
		Fo_actVO fo_act_vo;
		 Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				Class.forName(driver);

					con = DriverManager.getConnection(url, userid, password);
					pstmt=con.prepareStatement(GET_FO_ACT_ByAct_no_STMT);
					pstmt.setString(1, ACT_NO);
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						fo_act_vo=new Fo_actVO();
						fo_act_vo.setMem_ac(rs.getString("MEM_AC"));
						fo_act_vo.setAct_no(rs.getString("ACT_NO"));
						fo_act_vo.setFo_act_date(rs.getDate("FO_ACT_DATE"));
						set.add(fo_act_vo);
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
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
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
ActJDBCDAO dao=new ActJDBCDAO();

ActVO act_vo1=new ActVO();
	act_vo1.setMem_ac("camacoffee");
	act_vo1.setOrg_cont("我是主辦人");
	act_vo1.setAct_name("我是活動");
	act_vo1.setMin_mem(6);
	act_vo1.setMax_mem(10);
	act_vo1.setMem_count(5);
	act_vo1.setAct_op_date(java.sql.Date.valueOf("2017-09-07"));
	act_vo1.setAct_ed_date(java.sql.Date.valueOf("2017-09-09"));
	act_vo1.setDl_date(java.sql.Date.valueOf("2017-09-02"));
	act_vo1.setFd_date(java.sql.Date.valueOf("2017-09-01"));
	act_vo1.setAct_add("台灣");
	act_vo1.setAct_add_lat("121.550537");
	act_vo1.setAct_add_lon("25.032904");
	act_vo1.setAct_cont("bababa~~");
	act_vo1.setAct_tag("隨便啦~");
	act_vo1.setAct_fee(0);
	act_vo1.setPay_way("不需繳費");
	byte[ ]pic1=getByteArray("C:\\Users\\Java\\Desktop\\專題照片\\act.jpg");
	act_vo1.setAct_pic1(pic1);
	act_vo1.setAct_pic2(null);
	act_vo1.setAct_pic3(null);
	act_vo1.setAct_stat("已審核");
	act_vo1.setRe_cont(null);
	act_vo1.setReview_ed_date(java.sql.Date.valueOf("2017-09-01"));
	dao.insert(act_vo1);
//	
//	Act_vo act_vo2=new Act_vo();
//	act_vo2.setMem_ac("starter4244");
//	act_vo2.setOrg_cont("我不是主辦人");
//	act_vo2.setAct_name("我是活動");
//	act_vo2.setMin_mem(6);
//	act_vo2.setMax_mem(10);
//	act_vo2.setMem_count(5);
//	act_vo2.setAct_op_date(java.sql.Date.valueOf("2017-09-07"));
//	act_vo2.setAct_ed_date(java.sql.Date.valueOf("2017-09-09"));
//	act_vo2.setDl_date(java.sql.Date.valueOf("2017-09-02"));
//	act_vo2.setFd_date(java.sql.Date.valueOf("2017-09-01"));
//	act_vo2.setAct_add("台灣");
//	act_vo2.setAct_add_lat("121.550537");
//	act_vo2.setAct_add_lon("25.032904");
//	act_vo2.setAct_cont("bababa~~");
//	act_vo2.setAct_tag("隨便啦~");
//	act_vo2.setAct_fee(0);
//	act_vo2.setPay_way("不需繳費");
//	byte[ ]pic2=getByteArray("C:\\Users\\Java\\Desktop\\專題照片\\act.jpg");
//	act_vo2.setAct_pic1(pic2);
//	act_vo2.setAct_pic2(null);
//	act_vo2.setAct_pic3(null);
//	act_vo2.setAct_stat("已審核");
//	act_vo2.setRe_cont(null);
//	act_vo2.setReview_ed_date(java.sql.Date.valueOf("2017-09-01"));
//	act_vo2.setAct_no("A1000000012");
//	dao.update(act_vo2);
	
//	dao.delete("A1000000001");
	
//Act_vo act_vo3=dao.findByPrimaryKey("A1000000005");
//System.out.print(act_vo3.getMem_ac());
//System.out.print(act_vo3.getOrg_cont());
//
//	List<Act_vo>list=dao.getAll();
//	for(Act_vo act_vo4:list){
//		System.out.print(act_vo4.getMem_ac()+",");
//		System.out.print(act_vo4.getOrg_cont()+",");
//		System.out.println();
//		
//	}
//Set<Act_commVO> set1=dao.getAct_commByAct_no("A1000000005");
//for(Act_commVO act_comm_vo5:set1){
//	System.out.print(act_comm_vo5.getComm_no()+",");
//	System.out.print(act_comm_vo5.getAct_no()+",");
//	System.out.print(act_comm_vo5.getMem_ac()+",");
//	System.out.print(act_comm_vo5.getComm_cont()+",");
//	System.out.print(act_comm_vo5.getComm_date()+",");
//	System.out.print(act_comm_vo5.getComm_reply_cont()+",");
//	System.out.print(act_comm_vo5.getComm_reply_date()+",");
//	System.out.println();
//	}

//Set<Act_pairVO> set2=dao.getAct_pairByAct_no("A1000000002");
//for(Act_pairVO act_pair_vo:set2){
//	 System.out.print(act_pair_vo.getAct_no()+",");
//		System.out.print(act_pair_vo.getMem_ac()+",");
//		System.out.print(act_pair_vo.getApply_date()+",");
//		System.out.print(act_pair_vo.getPay_state()+",");
//		System.out.print(act_pair_vo.getChk_state()+",");
//		System.out.println();
//	 
//	 
//	 
//}
//Set<Fo_actVO> set3=dao.getFo_actByAct_no("A1000000002");
//for(Fo_actVO fo_act_vo: set3){
//	System.out.print(fo_act_vo.getMem_ac()+",");
//	System.out.print(fo_act_vo.getAct_no()+",");
//	System.out.print(fo_act_vo.getFo_act_date()+",");
//	System.out.println();
//	}


	}
	
	public static byte[] getByteArray(String path) throws IOException{
		FileInputStream fis=new FileInputStream(new File(path));
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		int i;
		byte [] buffer=new byte [8193];
		if((i=fis.read(buffer))!=-1){
			baos.write(buffer,0,i);
		}
		return baos.toByteArray();
	}

	
	
}
