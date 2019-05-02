package kr.spring.gowith.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.gowith.dao.GowithMapper;
import kr.spring.gowith.domain.GowithCommand;

@Service("gowithService")
public class GowithServiceImpl implements GowithService{
	
	@Resource
	private GowithMapper gowithMapper;

	@Override
	public List<GowithCommand> selectList(Map<String, Object> map) {
		return gowithMapper.selectList(map);
	}

	@Override
	public void insert(GowithCommand gowith) {
		gowithMapper.insert(gowith);
		
	}


	@Override
	public int selectRowCount(Map<String, Object> map) {
		
		return gowithMapper.selectRowCount(map);
	}

	@Override
	public void update(GowithCommand gowith) {
		gowithMapper.update(gowith);
		
	}

	@Override
	public GowithCommand selectGowith(Integer go_num) {
	   	
		return gowithMapper.selectGowith(go_num);
	}
	
	@Override
	public GowithCommand selectGowith(String email) {
		
		return gowithMapper.selectGowith(email);
	}

	@Override
	public void delete(Integer go_num) {
		//��û ����
		gowithMapper.deleteApp(go_num);
		//����� ����
		gowithMapper.delete(go_num);
	}

	@Override
	public int count(int go_num) {
		return gowithMapper.count(go_num);
	}
	
	//���ۼ��� ȸ�� ���� �ø���
	@Override
	public void updateScore(String email) {
		gowithMapper.updateScore(email);
	}


}
