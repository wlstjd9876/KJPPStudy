package kr.spring.location.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.location.dao.ChartMapper;
import kr.spring.location.domain.ChartCommand;

@Service("chartService")
public class ChartServiceImpl implements ChartService {
	
	@Resource
	private ChartMapper chartMapper;
	
	//ȸ������ ���� ��Ÿ�� ��Ʈ
	@Override
	public List<ChartCommand> style() {
		return chartMapper.style();
	}
	
	//����� �Բ��ϴ� ���� Ÿ�� ��Ʈ
	@Override
	public List<ChartCommand> type() {
		return chartMapper.type();
	}
	
	//���� �ñ� ��Ʈ
	@Override
	public List<ChartCommand> month() {
		return chartMapper.month();
	}

}
