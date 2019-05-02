package kr.spring.calendar.service;

import java.util.List;
import java.util.Map;

import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.calendar.domain.CalendarDetailCommand;

public interface CalendarService {
	//일정
	public void insert(CalendarCommand calendar);
	public List<CalendarCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	public CalendarCommand selectCalendar(Integer s_num);
	public void update(CalendarCommand calendar);
	public void delete(Integer s_num);
	public List<CalendarCommand> selectCal(Map<String, Object> map);
	public List<CalendarDetailCommand> selectDetailCal(Map<String, Object> map);
	
	//일정상세
	public void insertDetail(CalendarDetailCommand calendarDetail);
	public List<CalendarDetailCommand> selectDetailList(Map<String, Object> map);
	public CalendarDetailCommand selectDetail(Integer sd_num);
	public void updateDetail(CalendarDetailCommand calendarDetail);
	public void deleteDetail(Integer sd_num);
}
