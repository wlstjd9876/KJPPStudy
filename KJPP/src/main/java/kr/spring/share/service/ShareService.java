package kr.spring.share.service;

import java.util.List;
import java.util.Map;

import kr.spring.share.domain.ShareCommand;
import kr.spring.share.domain.ShareReplyCommand;

public interface ShareService {
	//부모글
	public List<ShareCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map< String, Object> map);
	public void insert(ShareCommand share);
	public ShareCommand selectShare(Integer num); //MyBatis에 데이터를 넘기기 위해 Integer로 사용
	public void update(ShareCommand share);
	public void delete(Integer num);

	//댓글
	public List<ShareReplyCommand> selectListReply(Map<String, Object> map);
	public int selectRowCountReply(Map<String, Object> map);
	public void insertReply(ShareReplyCommand shareReply);
	public void updateReply(ShareReplyCommand shareReply);
	public void deleteReply(Integer sr_num);
}
