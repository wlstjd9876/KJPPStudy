package kr.spring.calendar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.calendar.domain.CalendarDetailCommand;
import kr.spring.calendar.service.CalendarService;;

@Controller
public class CalendarAjaxController {
	@Resource
	private CalendarService calendarService;
	
	@RequestMapping("/calendar/scheduleAll.do")
	@ResponseBody
	public Map<String, Object> getSchedule(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CalendarDetailCommand> list = null;
		list = calendarService.selectDetailList(map);
		
		CalendarDetailCommand detail = new CalendarDetailCommand();
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		mapJson.put("day", detail.getSd_day());
		mapJson.put("starttime", detail.getSd_starttime());
		mapJson.put("endtime", detail.getSd_endtime());
		return mapJson;
	}
	@RequestMapping("/calendar/eventAll.do")
	@ResponseBody
	public Map<String, Object> getCal(@RequestParam("email") String email){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		
		List<CalendarCommand> list = null;
		list = calendarService.selectCal(map);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}
	@RequestMapping("/calendar/eventdetail.do")
	@ResponseBody
	public Map<String, Object> getDetailCal(@RequestParam("s_num") String s_num) throws NumberFormatException, ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s_num", s_num);
		
		List<CalendarDetailCommand> list = null;
		list = calendarService.selectDetailCal(map);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		mapJson.put("color", calendarService.selectCalendar(Integer.parseInt(s_num)).getS_color());
		return mapJson;
	}
}
