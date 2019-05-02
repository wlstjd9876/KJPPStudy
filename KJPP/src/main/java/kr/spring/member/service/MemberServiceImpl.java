package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.MemberCommand;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Resource
	private MemberMapper memberMapper;
	
	@Override
	public void insert(MemberCommand member) {
		memberMapper.insert(member);
		memberMapper.insertDetail(member);
	}

	@Override
	public MemberCommand selectMember(String email) {
		return memberMapper.selectMember(email);
	}
	
	@Override
	public MemberCommand checkNickname(String td_nickname) {
		return memberMapper.checkNickname(td_nickname);
	}

	@Override
	public void update(MemberCommand member) {
		memberMapper.update(member);
	}
  
	@Override
	public void updatePassword(MemberCommand member) {
		memberMapper.updatePassword(member);
	}
  
	@Override
	public void delete(String email) {
		memberMapper.delete(email);
		memberMapper.deleteDetail(email);
	}


	@Override
	public List<MemberCommand> selectList(Map<String, Object> map) {
		return memberMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return memberMapper.selectRowCount(map);
	}
	@Override
	public void updateNick(MemberCommand member) {
		memberMapper.updateNick(member);
	}
}
