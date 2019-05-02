package kr.spring.goapp.service;

import java.util.List;
import java.util.Map;

import kr.spring.goapp.domain.GoappCommand;

public interface GoappService {
	
	//신청서
	public GoappCommand selectMember(int app_num);
	public List<GoappCommand> selectList(int go_num);
	public int selectRowCount(Map<String, Object>map);
	
	public void insert(GoappCommand goapp);
	public void update(GoappCommand goapp);
	public void delete(int app_num);
	
	//신청 수락
	public void confirm(int app_num);
	//중복 신청 방지
	public int check(Map<String, Object> map);
	
}
