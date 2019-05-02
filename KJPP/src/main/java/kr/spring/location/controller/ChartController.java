package kr.spring.location.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.location.domain.ChartCommand;
import kr.spring.location.service.ChartService;

@Controller
public class ChartController {
	
	@Resource
	private ChartService chartService;
	
	@RequestMapping(value="/data/chart.do")
	public String form(){
		
		return "chart";
				
	}
	
	//여행 스타일
	@RequestMapping("/data/styleChart.do")
	@ResponseBody//ajax 사용시 명시 필요!!!
	public Map<String, Object> style() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChartCommand> list = chartService.style();
		
		System.out.println("@@@@@@@@@@@" + list.iterator().next().getCountstyle());
				
		map.put("styleList", list);
		
		return map;
	}
	
	//여행 타입
	@RequestMapping("/data/typeChart.do")
	@ResponseBody //ajax 사용시 명시 필요!!!
	public Map<String, Object> type() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChartCommand> list = chartService.type();
		
		System.out.println("@@@@@@@@@@@" + list.iterator().next().getCount());
				
		map.put("typeList", list);
		
		return map;
	}
	
	//여행 시기
	@RequestMapping("/data/monthChart.do")
	@ResponseBody //ajax 사용시 명시 필요!!!
	public Map<String, Object> month() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChartCommand> list = chartService.month();
		
		System.out.println("@@@@@@@@@@@" + list.iterator().next().getCountmonth());
				
		map.put("monthList", list);
		
		return map;
	}
	
	
}
