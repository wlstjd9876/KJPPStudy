package kr.spring.gowith.service;

import java.util.List;
import java.util.Map;

import kr.spring.gowith.domain.GowithCommand;
import kr.spring.member.domain.MemberCommand;

public interface GowithService {
	//������(ch14 ȸ������ ����)
	public void insert(GowithCommand gowith);
	public void update(GowithCommand gowith);
	public void delete(Integer go_num);
	public GowithCommand selectGowith(Integer go_num);
	public GowithCommand selectGowith(String email);
	
	//��û ���� �ο� ī��Ʈ
	public int count(int go_num);
	
	//���� ����Ʈ(ch14 �Խ��� ����)
	public List<GowithCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
	//���ۼ��� ȸ�� ���� �ø���
	public void updateScore(String email);
	
}
