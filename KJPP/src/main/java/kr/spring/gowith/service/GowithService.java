package kr.spring.gowith.service;

import java.util.List;
import java.util.Map;

import kr.spring.gowith.domain.GowithCommand;
import kr.spring.member.domain.MemberCommand;

public interface GowithService {
	//동행등록(ch14 회원가입 참고)
	public void insert(GowithCommand gowith);
	public void update(GowithCommand gowith);
	public void delete(Integer go_num);
	public GowithCommand selectGowith(Integer go_num);
	public GowithCommand selectGowith(String email);
	
	//신청 수락 인원 카운트
	public int count(int go_num);
	
	//동행 리스트(ch14 게시판 참고)
	public List<GowithCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
	//글작성시 회원 점수 올리기
	public void updateScore(String email);
	
}
