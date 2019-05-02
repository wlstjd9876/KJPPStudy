package kr.spring.share.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.share.dao.ShareMapper;
import kr.spring.share.domain.ShareCommand;
import kr.spring.share.domain.ShareReplyCommand;

@Service("shareService")
public class ShareServiceImpl implements ShareService {

	@Resource
	private ShareMapper shareMapper;
	
	@Override
	public List<ShareCommand> selectList(Map<String, Object> map) {
		return shareMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return shareMapper.selectRowCount(map);
	}

	@Override
	public void insert(ShareCommand share) {
		shareMapper.insert(share);
	}

	@Override
	public ShareCommand selectShare(Integer num) {
		return shareMapper.selectShare(num);
	}

	@Override
	public void update(ShareCommand share) {
		shareMapper.update(share);
	}

	@Override
	public void delete(Integer num) {
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		shareMapper.deleteReplyByNum(num);
		//부모글 삭제
		shareMapper.delete(num);
	}

	@Override
	public List<ShareReplyCommand> selectListReply(Map<String, Object> map) {
		return shareMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return shareMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(ShareReplyCommand shareReply) {
		shareMapper.insertReply(shareReply);
	}

	@Override
	public void updateReply(ShareReplyCommand shareReply) {
		shareMapper.updateReply(shareReply);
	}

	@Override
	public void deleteReply(Integer sr_num) {
		shareMapper.deleteReply(sr_num);
	}



}
