package kr.spring.goapp.service;

import java.util.List;
import java.util.Map;

import kr.spring.goapp.domain.GoappCommand;

public interface GoappService {
	
	//��û��
	public GoappCommand selectMember(int app_num);
	public List<GoappCommand> selectList(int go_num);
	public int selectRowCount(Map<String, Object>map);
	
	public void insert(GoappCommand goapp);
	public void update(GoappCommand goapp);
	public void delete(int app_num);
	
	//��û ����
	public void confirm(int app_num);
	//�ߺ� ��û ����
	public int check(Map<String, Object> map);
	
}
