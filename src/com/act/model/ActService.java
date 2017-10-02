package com.act.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.act_comm.model.Act_commVO;
import com.act_pair.model.Act_pairVO;
import com.fo_act.model.Fo_actVO;

public class ActService {

	private ActDAO_interface dao;
	
	public ActService(){
		dao= new ActJNDIDAO();
	}
	
	public ActVO addAct(String act_no,String mem_ac,String org_cont,String act_name, int min_mem,int max_mem,int mem_count,Date act_op_date,Date act_ed_date,Date dl_date,Date fd_date,String act_add,String act_add_lat,String act_add_lon,String act_cont,String act_tag,int act_fee,String pay_way,byte[] act_pic1,byte[] act_pic2,byte[] act_pic3,String act_stat,String re_cont,Date review_ed_date){
		
		
		ActVO act_VO=new ActVO();
		act_VO.setAct_no(act_no);
		act_VO.setMem_ac(mem_ac);
		act_VO.setOrg_cont(org_cont);
		act_VO.setAct_name(act_name);
		act_VO.setMin_mem(min_mem);
		act_VO.setMax_mem(max_mem);
		act_VO.setMem_count(mem_count);
		act_VO.setAct_op_date(act_op_date);
		act_VO.setAct_ed_date(act_ed_date);
		act_VO.setDl_date(dl_date);
		act_VO.setFd_date(fd_date);
		act_VO.setAct_add(act_add);
		act_VO.setAct_add_lat(act_add_lat);
		act_VO.setAct_add_lon(act_add_lon);
		act_VO.setAct_cont(act_cont);
		act_VO.setAct_tag(act_tag);
		act_VO.setAct_fee(act_fee);
		act_VO.setPay_way(pay_way);
		act_VO.setAct_pic1(act_pic1);
		act_VO.setAct_pic2(act_pic2);
		act_VO.setAct_pic3(act_pic3);
		act_VO.setAct_stat(act_stat);
		act_VO.setRe_cont(re_cont);
		act_VO.setReview_ed_date(review_ed_date);
		
		dao.insert(act_VO);
		return act_VO;
	}
	
public ActVO updateAct(String act_no,String mem_ac,String org_cont,String act_name, int min_mem,int max_mem,int mem_count,Date act_op_date,Date act_ed_date,Date dl_date,Date fd_date,String act_add,String act_add_lat,String act_add_lon,String act_cont,String act_tag,int act_fee,String pay_way,byte[] act_pic1,byte[] act_pic2,byte[] act_pic3,String act_stat,String re_cont,Date review_ed_date){
		
		ActVO act_VO=new ActVO();
		act_VO.setAct_no(act_no);
		act_VO.setMem_ac(mem_ac);
		act_VO.setOrg_cont(org_cont);
		act_VO.setAct_name(act_name);
		act_VO.setMin_mem(min_mem);
		act_VO.setMax_mem(max_mem);
		act_VO.setMem_count(mem_count);
		act_VO.setAct_op_date(act_op_date);
		act_VO.setAct_ed_date(act_ed_date);
		act_VO.setDl_date(dl_date);
		act_VO.setFd_date(fd_date);
		act_VO.setAct_add(act_add);
		act_VO.setAct_add_lat(act_add_lat);
		act_VO.setAct_add_lon(act_add_lon);
		act_VO.setAct_cont(act_cont);
		act_VO.setAct_tag(act_tag);
		act_VO.setAct_fee(act_fee);
		act_VO.setPay_way(pay_way);
		act_VO.setAct_pic1(act_pic1);
		act_VO.setAct_pic2(act_pic2);
		act_VO.setAct_pic3(act_pic3);
		act_VO.setAct_stat(act_stat);
		act_VO.setRe_cont(re_cont);
		act_VO.setReview_ed_date(review_ed_date);
		
		dao.update(act_VO);
		return act_VO;
	}
	
public void deleteAct(String act_no){
	dao.delete(act_no);
}

public ActVO getOneAct(String act_no) {
	return dao.findByPrimaryKey(act_no);
}

public List<ActVO> getAll() {
	return dao.getAll();
}

public Set<Act_commVO> getAct_commByAct_no(String ACT_NO){
	return dao.getAct_commByAct_no(ACT_NO);
}
public Set<Act_pairVO> getAct_pairByAct_no1(String ACT_NO){
	
	return dao.getAct_pairByAct_no(ACT_NO);
}
public Set<Fo_actVO> getFo_actByAct_no(String ACT_NO){
	
	return dao.getFo_actByAct_no(ACT_NO);
}


}
