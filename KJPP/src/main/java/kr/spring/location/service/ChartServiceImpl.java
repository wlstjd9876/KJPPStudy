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
	
	//회원들의 여행 스타일 차트
	@Override
	public List<ChartCommand> style() {
		return chartMapper.style();
	}
	
	//동행과 함께하는 여행 타입 차트
	@Override
	public List<ChartCommand> type() {
		return chartMapper.type();
	}
	
	//여행 시기 차트
	@Override
	public List<ChartCommand> month() {
		return chartMapper.month();
	}

}
