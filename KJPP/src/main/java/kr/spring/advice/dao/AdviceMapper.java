package kr.spring.advice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.advice.domain.AdviceCommand;
import kr.spring.advice.domain.AdviceDetailCommand;
import kr.spring.advice.domain.AdviceReplyCommand;
import kr.spring.advice.domain.AdviceReplyDetailCommand;

public interface AdviceMapper {
	//========================목록 advice
	public List<AdviceCommand> selectList(Map<String,Object> map);  //글 리스트
	public int selectRowCount(Map<String,Object> map);  //글 리스트 개수
	@Insert("INSERT INTO advice (adv_num,adv_title,email,adv_date,adv_phone,startdate,enddate,s_num) "
			+ "VALUES (advice_seq.nextval,#{adv_title},#{email},SYSDATE,#{adv_phone},#{startdate},#{enddate},#{s_num})")   //s_num 받아오기
	public void insert(AdviceCommand advice);  //글 등록
	@Select("SELECT * FROM advice WHERE adv_num=#{adv_num}")
	public AdviceCommand selectAdvice(Integer adv_num);  //전체 글 
	@Update("UPDATE advice SET adv_title=#{adv_title},adv_phone=#{adv_phone},startdate=#{startdate},enddate=#{enddate} WHERE adv_num=#{adv_num}")
	public void update(AdviceCommand advice);  //글 수정
	@Delete("DELETE FROM advice WHERE adv_num=#{adv_num}")
	public void delete(Map<String, Object> map);  //글 삭제
	
	//========================상세 일정 목록 advice_detail
	@Insert("INSERT INTO advice_detail (ad_num, ad_code, ad_day, starttime, endtime, ad_memo, ad_money, adv_num) "
			+ "VALUES (ADVIDE_DETAIL_SEQ.nextval, #{ad_code}, #{ad_day}, #{starttime}, #{endtime}, #{ad_memo}, #{ad_money}, #{adv_num})")
	public void insesrtDetail(AdviceDetailCommand detail);  //글 상세 일정 등록
	@Select("SELECT * FROM advice_detail WHERE adv_num=#{adv_num} ORDER BY ad_day, starttime")
	public List<AdviceDetailCommand> selectDetailList(Map<String, Object> map);  //글 상세 일정 전체 목록
	@Select("SELECT * FROM advice_detail WHERE adv_num=#{adv_num}")
	public AdviceDetailCommand selectDetailAdvice(Integer adv_num);  //글 번호에 따라서 글 상세 일정 목록
	@Update("UPDATE advice_detail SET ad_memo=#{ad_memo}, ad_money=#{ad_money} WHERE ad_num = #{ad_num}")
	public void updateDetail(AdviceDetailCommand detail);  //글 상세 일정 수정
	@Delete("DELETE FROM advice_detail WHERE ad_num = #{ad_num}")
	public void deleteDetail(Integer ad_num);  //글 상세 일정 삭제
	
	//========================댓글 advice_reply
	public List<AdviceReplyCommand> selectListReply(Map<String, Object> map);  //글 번호에 따른 댓글 전체 목록
	@Select("SELECT COUNT(*) FROM advice_reply WHERE adv_num=#{adv_num}")
	public int selectRowCountReply(Map<String, Object> map);  //댓글 개수
	@Insert("INSERT INTO advice_reply (adv_num,email,ar_date,startdate,enddate,ar_comment,s_num,ar_num) "
			+ "VALUES (#{adv_num},#{email},SYSDATE,#{startdate},#{enddate},#{ar_comment},1,ADVIDE_REPLY_SEQ.nextval)")  //s_num 받아오기
	public void insertReply(AdviceReplyCommand adviceReply);  //댓글 등록
	@Update("UPDATE advice_reply SET ar_comment=#{ar_comment} WHERE ar_num=#{ar_num}")
	public void updateReply(AdviceReplyCommand adviceReply);  //댓글 수정
	@Delete("DELETE FROM advice_reply WHERE ar_num=#{ar_num}")
	public void deleteReply(Integer ar_num);  //댓글 삭제
	@Select("SELECT * FROM advice_reply WHERE ar_num=#{ar_num}")
	public AdviceReplyCommand selectReplyDetail(Integer ar_num);
	
	//========================댓글 상세 advice_reply_detail
	@Select("SELECT * FROM advice_reply_detail d JOIN advice_reply a ON d.ar_num = a.ar_num WHERE a.adv_num = #{adv_num} ORDER BY d.ad_day, d.starttime")
	public List<AdviceReplyDetailCommand> selectReplyDetailList(Map<String, Object> map);  //댓글 상세 전체 목록
	@Select("SELECT * FROM advice_reply_detail WHERE ad_num = #{ad_num}")
	public AdviceReplyDetailCommand selectReplyDetailAdvice(Integer ar_num);  //댓글 상세 목록
	@Update("UPDATE advice_reply_detail SET ad_memo=#{ad_memo}, ad_money={ad_money} WHERE ad_num = #{ad_num}")
	public void updateReplyDetail(Integer ad_num);  //댓글 상세 수정
	@Delete("DELETE FROM advice_reply_detail WHERE ad_num = #{ad_num}")
	public void deleteReplyDetail(Integer ad_num);  //댓글 상세 삭제
	
	//========================전체 삭제
	@Delete("DELETE FROM advice_detail WHERE adv_num=#{adv_num}")
	public void deleteDetailAll(Integer adv_num);  //글 번호에 따른 글 상세 삭제
	@Delete("DELETE FROM advice_reply WHERE adv_num=#{adv_num}")
	public void deleteReplyAll(Integer adv_num);  //글 번호에 따른 댓글 삭제
	@Delete("DELETE FROM advice_reply_detail WHERE ar_num=#{ar_num}")
	public void deleteReplyDetailAll(Integer ar_num);  //댓글 번호에 따른 댓글 상세 삭제
}
