package kr.spring.location.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.location.domain.ChartCommand;

public interface ChartMapper {
	
	//회원들의 여행 스타일 차트
	@Select("SELECT COUNT(*) countstyle, s_style FROM schedule GROUP BY s_style ORDER BY countstyle")
	public List<ChartCommand> style();
	
	//동행과 함께하는 여행 타입 차트
	@Select("SELECT COUNT(*) count, go_type FROM gowith GROUP BY go_type ORDER BY count DESC")
	public List<ChartCommand> type();
	
	//여행 가장 많이 떠나는 시기 차트
	@Select("SELECT TO_CHAR(TO_DATE(s_startdate,'yyyy-MM-dd'),'MM') as startdate, COUNT(*) countmonth FROM schedule GROUP BY TO_CHAR(TO_DATE(s_startdate,'yyyy-MM-dd'),'MM') ORDER BY TO_CHAR(TO_DATE(s_startdate,'yyyy-MM-dd'),'MM')")
	public List<ChartCommand> month();
	
		
}
