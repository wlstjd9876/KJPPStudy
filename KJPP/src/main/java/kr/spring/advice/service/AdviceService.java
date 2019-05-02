package kr.spring.advice.service;

import java.util.List;
import java.util.Map;

import kr.spring.advice.domain.AdviceCommand;
import kr.spring.advice.domain.AdviceDetailCommand;
import kr.spring.advice.domain.AdviceReplyCommand;
import kr.spring.advice.domain.AdviceReplyDetailCommand;

public interface AdviceService {
	//========================목록 advice
	public List<AdviceCommand> selectList(Map<String,Object> map);  //글 리스트
	public int selectRowCount(Map<String,Object> map);  //글 리스트 개수
	public void insert(AdviceCommand advice);  //글 등록
	public AdviceCommand selectAdvice(Integer adv_num);  //전체 글 
	public void update(AdviceCommand advice);  //글 수정
	public void delete(Map<String,Object> map);  //글 삭제
	
	//========================상세 일정 목록 advice_detail
	public void insesrtDetail(AdviceDetailCommand detail);  //글 상세 등록
	public List<AdviceDetailCommand> selectDetailList(Map<String, Object> map);  //글 상세 전체 목록
	public AdviceDetailCommand selectDetailAdvice(Integer adv_num);  //글 번호에 따라서 글 상세 목록
	public void updateDetail(AdviceDetailCommand detail);  //글 상세 수정
	public void deleteDetail(Integer ad_num);  //글 상세 삭제
	
	//========================댓글 advice_reply
	public List<AdviceReplyCommand> selectListReply(Map<String, Object> map);  //글 번호에 따른 댓글 전체 목록
	public int selectRowCountReply(Map<String, Object> map);  //댓글 개수
	public void insertReply(AdviceReplyCommand adviceReply);  //댓글 등록
	public void updateReply(AdviceReplyCommand adviceReply);  //댓글 수정
	public void deleteReply(Integer ar_num);  //댓글 삭제
	public AdviceReplyCommand selectReplyDetail(Integer ar_num);  //댓글 상세
	
	//========================댓글 상세 advice_reply_detail
	public List<AdviceReplyDetailCommand> selectReplyDetailList(Map<String, Object> map);  //댓글 상세 전체 목록
	public AdviceReplyDetailCommand selectReplyDetailAdvice(Integer ar_num);  //댓글 상세 목록
	public void updateReplyDetail(Integer ad_num);  //댓글 상세 수정
	public void deleteReplyDetail(Integer ad_num);  //댓글 상세 삭제
}
