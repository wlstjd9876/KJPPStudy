package kr.spring.calendar.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.calendar.dao.CalendarMapper;
import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.calendar.domain.CalendarDetailCommand;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService{

	@Resource
	private CalendarMapper calendarMapper;
	
	//老沥
	@Override
	public void insert(CalendarCommand calendar) {
		calendarMapper.insert(calendar);
	}

	@Override
	public List<CalendarCommand> selectList(Map<String, Object> map) {
		return calendarMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return calendarMapper.selectRowCount(map);
	}

	@Override
	public CalendarCommand selectCalendar(Integer s_num) {
		return calendarMapper.selectCalendar(s_num);
	}

	@Override
	public void update(CalendarCommand calendar) {
		calendarMapper.update(calendar);
	}

	@Override
	public void delete(Integer s_num) {
		//老沥 惑技 昏力
		calendarMapper.deleteDetail(s_num);
		//老沥 昏力
		calendarMapper.delete(s_num);
	}

	//老沥惑技
	@Override
	public void insertDetail(CalendarDetailCommand calendarDetail) {
		calendarMapper.insertDetail(calendarDetail);
	}

	@Override
	public List<CalendarDetailCommand> selectDetailList(Map<String, Object> map) {
		return calendarMapper.selectDetailList(map);
	}

	@Override
	public CalendarDetailCommand selectDetail(Integer sd_num) {
		return calendarMapper.selectDetail(sd_num);
	}

	@Override
	public void updateDetail(CalendarDetailCommand calendarDetail) {
		calendarMapper.updateDetail(calendarDetail);
	}

	@Override
	public void deleteDetail(Integer sd_num) {
		calendarMapper.deleteDetail(sd_num);
	}

	@Override
	public List<CalendarCommand> selectCal(Map<String, Object> map) {
		return calendarMapper.selectCal(map);
	}

	@Override
	public List<CalendarDetailCommand> selectDetailCal(Map<String, Object> map) {
		return calendarMapper.selectDetailCal(map);
	}
}
