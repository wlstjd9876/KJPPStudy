package kr.spring.location.service;

import java.util.List;

import kr.spring.location.domain.ChartCommand;

public interface ChartService {
	
	//회원들의 여행 스타일 차트
	public List<ChartCommand> style();
	
	//동행과 함께하는 여행 타입 차트
	public List<ChartCommand> type();
	
	//여행 시기 차트
	public List<ChartCommand> month();
	
	
}
