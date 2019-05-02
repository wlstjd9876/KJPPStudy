package kr.spring.share.service;

import java.util.List;
import java.util.Map;

import kr.spring.share.domain.ShareCommand;
import kr.spring.share.domain.ShareReplyCommand;

public interface ShareService {
	//�θ��
	public List<ShareCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map< String, Object> map);
	public void insert(ShareCommand share);
	public ShareCommand selectShare(Integer num); //MyBatis�� �����͸� �ѱ�� ���� Integer�� ���
	public void update(ShareCommand share);
	public void delete(Integer num);

	//���
	public List<ShareReplyCommand> selectListReply(Map<String, Object> map);
	public int selectRowCountReply(Map<String, Object> map);
	public void insertReply(ShareReplyCommand shareReply);
	public void updateReply(ShareReplyCommand shareReply);
	public void deleteReply(Integer sr_num);
}
