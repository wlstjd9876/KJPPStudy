package kr.spring.advice.service;

import java.util.List;
import java.util.Map;

import kr.spring.advice.domain.AdviceCommand;
import kr.spring.advice.domain.AdviceDetailCommand;
import kr.spring.advice.domain.AdviceReplyCommand;
import kr.spring.advice.domain.AdviceReplyDetailCommand;

public interface AdviceService {
	//========================��� advice
	public List<AdviceCommand> selectList(Map<String,Object> map);  //�� ����Ʈ
	public int selectRowCount(Map<String,Object> map);  //�� ����Ʈ ����
	public void insert(AdviceCommand advice);  //�� ���
	public AdviceCommand selectAdvice(Integer adv_num);  //��ü �� 
	public void update(AdviceCommand advice);  //�� ����
	public void delete(Map<String,Object> map);  //�� ����
	
	//========================�� ���� ��� advice_detail
	public void insesrtDetail(AdviceDetailCommand detail);  //�� �� ���
	public List<AdviceDetailCommand> selectDetailList(Map<String, Object> map);  //�� �� ��ü ���
	public AdviceDetailCommand selectDetailAdvice(Integer adv_num);  //�� ��ȣ�� ���� �� �� ���
	public void updateDetail(AdviceDetailCommand detail);  //�� �� ����
	public void deleteDetail(Integer ad_num);  //�� �� ����
	
	//========================��� advice_reply
	public List<AdviceReplyCommand> selectListReply(Map<String, Object> map);  //�� ��ȣ�� ���� ��� ��ü ���
	public int selectRowCountReply(Map<String, Object> map);  //��� ����
	public void insertReply(AdviceReplyCommand adviceReply);  //��� ���
	public void updateReply(AdviceReplyCommand adviceReply);  //��� ����
	public void deleteReply(Integer ar_num);  //��� ����
	public AdviceReplyCommand selectReplyDetail(Integer ar_num);  //��� ��
	
	//========================��� �� advice_reply_detail
	public List<AdviceReplyDetailCommand> selectReplyDetailList(Map<String, Object> map);  //��� �� ��ü ���
	public AdviceReplyDetailCommand selectReplyDetailAdvice(Integer ar_num);  //��� �� ���
	public void updateReplyDetail(Integer ad_num);  //��� �� ����
	public void deleteReplyDetail(Integer ad_num);  //��� �� ����
}
