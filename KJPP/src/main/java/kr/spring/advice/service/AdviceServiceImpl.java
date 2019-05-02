package kr.spring.advice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.advice.dao.AdviceMapper;
import kr.spring.advice.domain.AdviceCommand;
import kr.spring.advice.domain.AdviceDetailCommand;
import kr.spring.advice.domain.AdviceReplyCommand;
import kr.spring.advice.domain.AdviceReplyDetailCommand;

@Service("adviceService")
public class AdviceServiceImpl implements AdviceService{

	@Resource
	private AdviceMapper adviceMapper;
	
	//====================조언 글
	@Override
	public List<AdviceCommand> selectList(Map<String, Object> map) {
		return adviceMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return adviceMapper.selectRowCount(map);
	}

	@Override
	public void insert(AdviceCommand advice) {
		adviceMapper.insert(advice);
	}

	@Override
	public AdviceCommand selectAdvice(Integer adv_num) {
		return adviceMapper.selectAdvice(adv_num);
	}

	@Override
	public void update(AdviceCommand advice) {
		adviceMapper.update(advice);
	}

	@Override
	public void delete(Map<String, Object> map) {
		//댓글 상세 일정 삭제
		List<AdviceReplyDetailCommand> list = new ArrayList<AdviceReplyDetailCommand>();
		
		int adv_num = (Integer)map.get("adv_num");
		
		list = adviceMapper.selectReplyDetailList(map);
		for(int i=0; i<list.size(); i++) {
			adviceMapper.deleteReplyDetailAll(list.iterator().next().getAr_num());
		}
		
		//댓글 삭제
		adviceMapper.deleteReplyAll(adv_num);
		
		//일정상세 삭제
		adviceMapper.deleteDetailAll(adv_num);
		//일정 삭제
		adviceMapper.delete(map);
	}

	//====================조언 상세 일정
	@Override
	public void insesrtDetail(AdviceDetailCommand detail) {
		adviceMapper.insesrtDetail(detail);
	}

	@Override
	public List<AdviceDetailCommand> selectDetailList(Map<String, Object> map) {
		return adviceMapper.selectDetailList(map);
	}

	@Override
	public AdviceDetailCommand selectDetailAdvice(Integer adv_num) {
		return adviceMapper.selectDetailAdvice(adv_num);
	}

	@Override
	public void updateDetail(AdviceDetailCommand detail) {
		adviceMapper.updateDetail(detail);
	}

	@Override
	public void deleteDetail(Integer ad_num) {
		adviceMapper.deleteDetail(ad_num);
	}

	//====================조언 댓글
	@Override
	public List<AdviceReplyCommand> selectListReply(Map<String, Object> map) {
		return adviceMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return adviceMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(AdviceReplyCommand adviceReply) {
		adviceMapper.insertReply(adviceReply);
	}
	
	@Override
	public void updateReply(AdviceReplyCommand adviceReply) {
		adviceMapper.updateReply(adviceReply);
	}

	@Override
	public void deleteReply(Integer ar_num) {
		//조언 댓글 상세 일정 삭제
		adviceMapper.deleteReplyDetailAll(ar_num);
		//조언 댓글 삭제
		adviceMapper.deleteReply(ar_num);
	}
	@Override
	public AdviceReplyCommand selectReplyDetail(Integer ar_num) {
		return adviceMapper.selectReplyDetail(ar_num);
	}

	//====================조언 댓글 상세 일정
	@Override
	public List<AdviceReplyDetailCommand> selectReplyDetailList(Map<String, Object> map) {
		return adviceMapper.selectReplyDetailList(map);
	}

	@Override
	public AdviceReplyDetailCommand selectReplyDetailAdvice(Integer ar_num) {
		return adviceMapper.selectReplyDetailAdvice(ar_num);
	}

	@Override
	public void updateReplyDetail(Integer ad_num) {
		adviceMapper.updateReplyDetail(ad_num);
	}

	@Override
	public void deleteReplyDetail(Integer ad_num) {
		adviceMapper.deleteReplyDetail(ad_num);
	}

}
