package kr.spring.goapp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.goapp.dao.GoappMapper;
import kr.spring.goapp.domain.GoappCommand;

@Service("goappService")
public class GoappServiceImpl implements GoappService{
	
	@Resource
	private GoappMapper goappMapper;

	@Override
	public List<GoappCommand> selectList(int go_num) {
		return goappMapper.selectList(go_num);
	}

	@Override
	public int selectRowCount(Map<String, Object>map) {
		return goappMapper.selectRowCount(map);
	}

	@Override
	public void insert(GoappCommand goapp) {
		goappMapper.insert(goapp);		
	}

	@Override
	public void update(GoappCommand goapp) {
		goappMapper.update(goapp);		
	}
	
	@Override
	public void delete(int app_num) {
		goappMapper.delete(app_num);		
	}

	@Override
	public GoappCommand selectMember(int app_num) {
		return goappMapper.selectMember(app_num);
	}

	@Override
	public void confirm(int app_num) {
		goappMapper.confirm(app_num);
	}

	@Override
	public int check(Map<String, Object> map) {
		return goappMapper.check(map);
	}
	
}