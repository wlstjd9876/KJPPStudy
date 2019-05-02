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
	//========================��� advice
	public List<AdviceCommand> selectList(Map<String,Object> map);  //�� ����Ʈ
	public int selectRowCount(Map<String,Object> map);  //�� ����Ʈ ����
	@Insert("INSERT INTO advice (adv_num,adv_title,email,adv_date,adv_phone,startdate,enddate,s_num) "
			+ "VALUES (advice_seq.nextval,#{adv_title},#{email},SYSDATE,#{adv_phone},#{startdate},#{enddate},#{s_num})")   //s_num �޾ƿ���
	public void insert(AdviceCommand advice);  //�� ���
	@Select("SELECT * FROM advice WHERE adv_num=#{adv_num}")
	public AdviceCommand selectAdvice(Integer adv_num);  //��ü �� 
	@Update("UPDATE advice SET adv_title=#{adv_title},adv_phone=#{adv_phone},startdate=#{startdate},enddate=#{enddate} WHERE adv_num=#{adv_num}")
	public void update(AdviceCommand advice);  //�� ����
	@Delete("DELETE FROM advice WHERE adv_num=#{adv_num}")
	public void delete(Map<String, Object> map);  //�� ����
	
	//========================�� ���� ��� advice_detail
	@Insert("INSERT INTO advice_detail (ad_num, ad_code, ad_day, starttime, endtime, ad_memo, ad_money, adv_num) "
			+ "VALUES (ADVIDE_DETAIL_SEQ.nextval, #{ad_code}, #{ad_day}, #{starttime}, #{endtime}, #{ad_memo}, #{ad_money}, #{adv_num})")
	public void insesrtDetail(AdviceDetailCommand detail);  //�� �� ���� ���
	@Select("SELECT * FROM advice_detail WHERE adv_num=#{adv_num} ORDER BY ad_day, starttime")
	public List<AdviceDetailCommand> selectDetailList(Map<String, Object> map);  //�� �� ���� ��ü ���
	@Select("SELECT * FROM advice_detail WHERE adv_num=#{adv_num}")
	public AdviceDetailCommand selectDetailAdvice(Integer adv_num);  //�� ��ȣ�� ���� �� �� ���� ���
	@Update("UPDATE advice_detail SET ad_memo=#{ad_memo}, ad_money=#{ad_money} WHERE ad_num = #{ad_num}")
	public void updateDetail(AdviceDetailCommand detail);  //�� �� ���� ����
	@Delete("DELETE FROM advice_detail WHERE ad_num = #{ad_num}")
	public void deleteDetail(Integer ad_num);  //�� �� ���� ����
	
	//========================��� advice_reply
	public List<AdviceReplyCommand> selectListReply(Map<String, Object> map);  //�� ��ȣ�� ���� ��� ��ü ���
	@Select("SELECT COUNT(*) FROM advice_reply WHERE adv_num=#{adv_num}")
	public int selectRowCountReply(Map<String, Object> map);  //��� ����
	@Insert("INSERT INTO advice_reply (adv_num,email,ar_date,startdate,enddate,ar_comment,s_num,ar_num) "
			+ "VALUES (#{adv_num},#{email},SYSDATE,#{startdate},#{enddate},#{ar_comment},1,ADVIDE_REPLY_SEQ.nextval)")  //s_num �޾ƿ���
	public void insertReply(AdviceReplyCommand adviceReply);  //��� ���
	@Update("UPDATE advice_reply SET ar_comment=#{ar_comment} WHERE ar_num=#{ar_num}")
	public void updateReply(AdviceReplyCommand adviceReply);  //��� ����
	@Delete("DELETE FROM advice_reply WHERE ar_num=#{ar_num}")
	public void deleteReply(Integer ar_num);  //��� ����
	@Select("SELECT * FROM advice_reply WHERE ar_num=#{ar_num}")
	public AdviceReplyCommand selectReplyDetail(Integer ar_num);
	
	//========================��� �� advice_reply_detail
	@Select("SELECT * FROM advice_reply_detail d JOIN advice_reply a ON d.ar_num = a.ar_num WHERE a.adv_num = #{adv_num} ORDER BY d.ad_day, d.starttime")
	public List<AdviceReplyDetailCommand> selectReplyDetailList(Map<String, Object> map);  //��� �� ��ü ���
	@Select("SELECT * FROM advice_reply_detail WHERE ad_num = #{ad_num}")
	public AdviceReplyDetailCommand selectReplyDetailAdvice(Integer ar_num);  //��� �� ���
	@Update("UPDATE advice_reply_detail SET ad_memo=#{ad_memo}, ad_money={ad_money} WHERE ad_num = #{ad_num}")
	public void updateReplyDetail(Integer ad_num);  //��� �� ����
	@Delete("DELETE FROM advice_reply_detail WHERE ad_num = #{ad_num}")
	public void deleteReplyDetail(Integer ad_num);  //��� �� ����
	
	//========================��ü ����
	@Delete("DELETE FROM advice_detail WHERE adv_num=#{adv_num}")
	public void deleteDetailAll(Integer adv_num);  //�� ��ȣ�� ���� �� �� ����
	@Delete("DELETE FROM advice_reply WHERE adv_num=#{adv_num}")
	public void deleteReplyAll(Integer adv_num);  //�� ��ȣ�� ���� ��� ����
	@Delete("DELETE FROM advice_reply_detail WHERE ar_num=#{ar_num}")
	public void deleteReplyDetailAll(Integer ar_num);  //��� ��ȣ�� ���� ��� �� ����
}
